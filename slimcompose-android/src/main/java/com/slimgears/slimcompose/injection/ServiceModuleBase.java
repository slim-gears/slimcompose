// Copyright 2015 Denis Itskovich
// Refer to LICENSE.txt for license details
package com.slimgears.slimcompose.injection;

import android.app.Service;

import com.slimgears.slimcompose.extensibility.DefaultPluginContainer;
import com.slimgears.slimcompose.extensibility.PluginContainer;
import com.slimgears.slimcompose.extensibility.ServicePlugin;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ditskovi on 1/3/2016.
 *
 */
@Module
public class ServiceModuleBase {
    private final Service mService;

    public ServiceModuleBase(Service service) {
        mService = service;
    }

    @Provides @PerService
    public Service provideService() {
        return mService;
    }

    @Provides @PerService
    public PluginContainer<ServicePlugin> providePluginContainer() {
        PluginContainer<ServicePlugin> pluginContainer = new DefaultPluginContainer<>();
        addPlugins(pluginContainer);
        return pluginContainer;
    }

    protected void addPlugins(PluginContainer<ServicePlugin> pluginContainer) {

    }
}
