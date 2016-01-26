// Copyright 2015 Denis Itskovich
// Refer to LICENSE.txt for license details
package com.slimgears.slimcompose.services;

import android.content.Intent;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ditskovi on 11/6/2015.
 *
 */
public class DefaultActivityResultDispatcher implements ActivityResultDispatcher {
    private static int requestCodes = 0;
    private final Map<Integer, OnActivityResultListener> listenerMap = new HashMap<>();

    class DefaultSubscription implements PendingResult {
        private final int requestCode;

        DefaultSubscription(int requestCode) {
            this.requestCode = requestCode;
        }

        @Override
        public int getRequestCode() {
            return requestCode;
        }
    }

    public DefaultActivityResultDispatcher() {

    }

    @Override
    public boolean dispatchResult(int requestCode, int resultCode, Intent data) {
        if (listenerMap.containsKey(requestCode)) {
            OnActivityResultListener listener = listenerMap.remove(requestCode);
            return listener.onActivityResult(resultCode, data);
        }
        return false;
    }

    @Override
    public PendingResult listenForResult(OnActivityResultListener listener) {
        int requestCode = requestCodes++;
        listenerMap.put(requestCode, listener);
        return new DefaultSubscription(requestCode);
    }
}
