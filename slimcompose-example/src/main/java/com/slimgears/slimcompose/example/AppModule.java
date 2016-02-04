// Copyright 2015 Denis Itskovich
// Refer to LICENSE.txt for license details
package com.slimgears.slimcompose.example;

import android.app.Application;

import com.slimgears.slimbus.BusFactory;
import com.slimgears.slimbus.EventBusFactory;
import com.slimgears.slimbus.SlimEventBus;
import com.slimgears.slimcompose.app.AppModuleBase;
import com.slimgears.slimprefs.PreferenceFactory;
import com.slimgears.slimprefs.PreferenceInjectorFactory;

import dagger.Module;

/**
 * Created by ditskovi on 1/26/2016.
 *
 */
@Module
public class AppModule extends AppModuleBase {
    @BusFactory(busClass = SlimEventBus.class)
    interface AppBusFactory extends EventBusFactory {}

    @PreferenceFactory
    interface AppPreferenceFactory extends PreferenceInjectorFactory {}

    public AppModule(Application app) {
        super(app, GeneratedAppModule_AppBusFactory.INSTANCE, GeneratedAppModule_AppPreferenceFactory.INSTANCE);
    }
}
