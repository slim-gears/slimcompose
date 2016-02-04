// Copyright 2015 Denis Itskovich
// Refer to LICENSE.txt for license details
package com.slimgears.slimcompose.example;

import com.slimgears.slimcompose.app.AbstractApp;
import com.slimgears.slimcompose.app.AndroidServiceModule;

/**
 * Created by ditskovi on 1/26/2016.
 *
 */
public class App extends AbstractApp<AppComponent> {
    @Override
    protected AppComponent createComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .androidServiceModule(new AndroidServiceModule())
                .build();
    }
}
