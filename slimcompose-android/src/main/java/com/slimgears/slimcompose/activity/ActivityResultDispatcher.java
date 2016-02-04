// Copyright 2015 Denis Itskovich
// Refer to LICENSE.txt for license details
package com.slimgears.slimcompose.activity;

import android.content.Intent;

/**
 * Created by ditskovi on 11/6/2015.
 *
 */
public interface ActivityResultDispatcher {
    interface OnActivityResultListener {
        boolean onActivityResult(int resultCode, Intent data);
    }

    interface PendingResult {
        int getRequestCode();
    }

    boolean dispatchResult(int requestCode, int resultCode, Intent data);
    PendingResult listenForResult(OnActivityResultListener listener);
}
