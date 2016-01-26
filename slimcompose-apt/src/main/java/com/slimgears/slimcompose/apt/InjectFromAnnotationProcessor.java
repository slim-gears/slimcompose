package com.slimgears.slimcompose.apt;

import com.google.common.collect.Iterables;
import com.slimgears.slimapt.AnnotationProcessorBase;
import com.slimgears.slimapt.TypeUtils;
import com.slimgears.slimcompose.injection.DependencyInjector;
import com.slimgears.slimcompose.injection.InjectFrom;
import com.slimgears.slimcompose.injection.PerInjection;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.TypeElement;

import dagger.Component;

@SupportedAnnotationTypes({"com.slimgears.slimcompose.injection.InjectFrom"})
public class InjectFromAnnotationProcessor extends AnnotationProcessorBase {
    @Override
    protected boolean processType(TypeElement typeElement) throws IOException {
        InjectFrom annotation = typeElement.getAnnotation(InjectFrom.class);
        Collection<TypeName> dependencies = TypeUtils.getTypesFromAnnotation(annotation, InjectFrom::value);
        TypeSpec injectorInterface = TypeSpec
                .interfaceBuilder(typeElement.getSimpleName().toString() + "_" + "Injector")
                .addAnnotation(PerInjection.class)
                .addAnnotation(AnnotationSpec
                        .builder(Component.class)
                        .addMember("dependencies", buildDependenciesCode(dependencies))
                        .build())
                .addSuperinterface(ParameterizedTypeName.get(ClassName.get(DependencyInjector.class), TypeName.get(typeElement.asType())))
                .build();
        String packageName = TypeUtils.packageName(typeElement.getQualifiedName().toString());

        writeType(packageName, injectorInterface);

        return true;
    }

    private CodeBlock buildDependenciesCode(Iterable<TypeName> dependencies) {
        TypeName[] depArray = Iterables.toArray(dependencies, TypeName.class);
        return CodeBlock
                .builder()
                .add(
                        String.format("{%s}", String.join(", ", repeat("$T.class", depArray.length))),
                        depArray)
                .build();
    }

    private static <T> Iterable<T> repeat(T element, int times) {
        return () -> new Iterator<T>() {
            private int count = times;

            @Override
            public boolean hasNext() {
                return count > 0;
            }

            @Override
            public T next() {
                --count;
                return element;
            }
        };
    }
}
