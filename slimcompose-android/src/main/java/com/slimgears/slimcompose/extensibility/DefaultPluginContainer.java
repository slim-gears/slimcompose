// Copyright 2015 Denis Itskovich
// Refer to LICENSE.txt for license details
package com.slimgears.slimcompose.extensibility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ditskovi on 11/7/2015.
 *
 */
public class DefaultPluginContainer<P extends Plugin> implements PluginContainer<P> {
    private final List<P> plugins = new ArrayList<>();

    @SafeVarargs
    @Override
    public final void addPlugins(P... plugins) {
        this.plugins.addAll(Arrays.asList(plugins));
    }

    @Override
    public void forEachPlugin(Invoker<P> invoker) {
        for (P plugin : plugins) {
            invoker.invoke(plugin);
        }
    }
}
