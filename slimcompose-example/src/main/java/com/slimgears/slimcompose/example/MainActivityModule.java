// Copyright 2015 Denis Itskovich
// Refer to LICENSE.txt for license details
package com.slimgears.slimcompose.example;

import android.app.Activity;

import com.slimgears.slimcompose.activity.ActivityModuleBase;

import dagger.Module;

/**
 * Created by ditskovi on 1/26/2016.
 *
 */
@Module
public class MainActivityModule extends ActivityModuleBase {
    protected MainActivityModule(Activity activity) {
        super(activity);
    }
}
