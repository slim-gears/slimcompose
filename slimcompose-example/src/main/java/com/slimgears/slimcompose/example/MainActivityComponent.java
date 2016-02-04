// Copyright 2015 Denis Itskovich
// Refer to LICENSE.txt for license details
package com.slimgears.slimcompose.example;

import com.slimgears.slimcompose.activity.ActivityComponentBase;
import com.slimgears.slimcompose.injection.PerActivity;

import dagger.Component;

/**
 * Created by ditskovi on 1/26/2016.
 *
 */
@Component(dependencies = AppComponent.class, modules = MainActivityModule.class)
@PerActivity
public interface MainActivityComponent extends ActivityComponentBase {
}
