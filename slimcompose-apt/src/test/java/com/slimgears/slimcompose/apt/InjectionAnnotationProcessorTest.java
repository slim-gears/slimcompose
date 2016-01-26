package com.slimgears.slimcompose.apt;// Copyright 2015 Denis Itskovich
// Refer to LICENSE.txt for license details

import com.slimgears.slimapt.AnnotationProcessingTestBase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by ditskovi on 1/25/2016.
 *
 */
@RunWith(JUnit4.class)
public class InjectionAnnotationProcessorTest extends AnnotationProcessingTestBase {
    @Test
    public void injectorShouldBeGenerated_forClassesAnnotatedWith_injectFrom() {
        testAnnotationProcessing(
                processedWith(new InjectFromAnnotationProcessor()),
                inputFiles("InjectionTarget.java"),
                expectedFiles("InjectionTarget_Injector.java"));
    }
}
