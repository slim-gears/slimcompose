// Copyright 2015 Denis Itskovich
// Refer to LICENSE.txt for license details
package com.slimgears.slimcompose.example;

import android.app.Application;

import com.slimgears.slimcompose.injection.AndroidServiceModule;
import com.slimgears.slimcompose.injection.HasComponent;

/**
 * Created by ditskovi on 1/26/2016.
 *
 */
public class App extends Application implements HasComponent<AppComponent> {
    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .androidServiceModule(new AndroidServiceModule())
                .build();
    }

    @Override
    public AppComponent getComponent() {
        return mAppComponent;
    }
}
