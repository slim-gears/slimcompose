// Copyright 2015 Denis Itskovich
// Refer to LICENSE.txt for license details
package com.slimgears.slimcompose.example;

import com.slimgears.slimcompose.app.AndroidServiceModule;
import com.slimgears.slimcompose.app.AppComponentBase;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ditskovi on 1/26/2016.
 *
 */
@Component(modules = {AppModule.class, AndroidServiceModule.class})
@Singleton
public interface AppComponent extends AppComponentBase {
}
