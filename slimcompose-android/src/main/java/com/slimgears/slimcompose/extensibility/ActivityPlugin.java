// Copyright 2015 Denis Itskovich
// Refer to LICENSE.txt for license details
package com.slimgears.slimcompose.extensibility;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by ditskovi on 11/6/2015.
 *
 */
public interface ActivityPlugin extends Plugin<Activity, Bundle> {
    void onResume();
    void onPause();
}
