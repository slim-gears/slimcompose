// Copyright 2015 Denis Itskovich
// Refer to LICENSE.txt for license details
package com.slimgears.slimcompose.extensibility;

/**
 * Created by ditskovi on 11/6/2015.
 *
 */
public interface PluginContainer<P extends Plugin> {
    interface Invoker<P extends Plugin> {
        void invoke(P plugin);
    }

    @SuppressWarnings("unchecked")
    void addPlugins(P... plugins);
    void forEachPlugin(Invoker<P> invoker);
}
