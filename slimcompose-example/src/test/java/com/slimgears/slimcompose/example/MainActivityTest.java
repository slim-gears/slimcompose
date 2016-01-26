// Copyright 2015 Denis Itskovich
// Refer to LICENSE.txt for license details
package com.slimgears.slimcompose.example;

import com.slimgears.slimcompose.injection.Components;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

/**
 * Created by ditskovi on 1/26/2016.
 * 
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(sdk = 16, constants = BuildConfig.class)
public class MainActivityTest {
    @Test
    public void createDestroyActivity_shouldNotCrash() {
        ActivityController<MainActivity> controller = Robolectric
                .buildActivity(MainActivity.class)
                .create()
                .start()
                .resume()
                .visible();

        MainActivity activity = controller.get();
        Components.getActivityComponent(activity, MainActivityComponent.class)
                .eventBus()
                .publish(new SayHelloEvent("Slim Compose"));

        Assert.assertEquals("Hello, World from Slim Compose", activity.mHelloText.getText());

        controller
                .pause()
                .stop()
                .destroy();
    }
}
