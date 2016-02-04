package com.slimgears.slimcompose.app;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import javax.inject.Inject;

/**
 * Created by ditskovi on 10/31/2015.
 *
 */
public class ToastErrorHandler implements ErrorHandler {
    private final Context mContext;

    @Inject
    public ToastErrorHandler(Context context) {
        mContext = context;
    }

    @Override
    public void onError(Throwable e) {
        Toast.makeText(mContext, "Error occurred: " + e.getMessage(), Toast.LENGTH_LONG).show();
        Log.e("DEFAULT", "Error occurred: " + e.getMessage(), e);
    }
}
