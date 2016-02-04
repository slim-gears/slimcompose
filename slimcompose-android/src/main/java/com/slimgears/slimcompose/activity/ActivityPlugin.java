// Copyright 2015 Denis Itskovich
// Refer to LICENSE.txt for license details
package com.slimgears.slimcompose.activity;

import android.app.Activity;
import android.os.Bundle;

import com.slimgears.slimcompose.extensibility.Plugin;

/**
 * Created by ditskovi on 11/6/2015.
 *
 */
public interface ActivityPlugin extends Plugin<Activity, Bundle> {
    void onResume();
    void onPause();
}
