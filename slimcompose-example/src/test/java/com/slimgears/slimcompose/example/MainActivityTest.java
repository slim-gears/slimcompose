// Copyright 2015 Denis Itskovich
// Refer to LICENSE.txt for license details
package com.slimgears.slimcompose.example;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.slimgears.slimcompose.injection.Components;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import java.util.Date;

/**
 * Created by ditskovi on 1/26/2016.
 * 
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(sdk = 16, constants = BuildConfig.class)
public class MainActivityTest {
    private ActivityController<MainActivity> mController;

    @Test
    public void createDestroyActivity_shouldNotCrash() {
        MainActivity activity = createActivity();

        Components.getActivityComponent(activity, MainActivityComponent.class)
                .eventBus()
                .publish(new SayHelloEvent("Slim Compose"));

        Assert.assertEquals("Hello, World from Slim Compose", activity.mHelloText.getText());
    }

    @Test
    public void preferencesBindingUnbinding() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(RuntimeEnvironment.application);
        preferences.edit().putInt("MainActivity.mRunCount", 5).apply();

        MainActivity activity = createActivity();
        Assert.assertEquals(6, activity.mRunCount);
        Assert.assertTrue(preferences.contains("MainActivity.mFirstRunDate"));
        Date date = activity.mFirstRunDate;

        destroyActivity();
        Assert.assertEquals(6, preferences.getInt("MainActivity.mRunCount", 0));

        activity = createActivity();
        Assert.assertEquals(date, activity.mFirstRunDate);
        destroyActivity();
    }

    private MainActivity createActivity() {
        mController = Robolectric
                .buildActivity(MainActivity.class)
                .create()
                .start()
                .resume()
                .visible();
        return mController.get();
    }

    private void destroyActivity() {
        mController
                .pause()
                .stop()
                .destroy();
        mController = null;
    }
}
