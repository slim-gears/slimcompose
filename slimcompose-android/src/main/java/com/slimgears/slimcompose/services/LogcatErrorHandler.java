// Copyright 2015 Denis Itskovich
// Refer to LICENSE.txt for license details
package com.slimgears.slimcompose.services;

import android.util.Log;

/**
 * Created by ditskovi on 11/24/2015.
 *
 */
public class LogcatErrorHandler implements ErrorHandler {
    @Override
    public void onError(Throwable e) {
        Log.e("Error", e.getMessage(), e);
    }
}
