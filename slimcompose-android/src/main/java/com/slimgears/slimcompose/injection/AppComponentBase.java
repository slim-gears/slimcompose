package com.slimgears.slimcompose.injection;

import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.ConnectivityManager;

import com.slimgears.slimbus.EventBus;
import com.slimgears.slimcompose.services.ActivityResultDispatcher;
import com.slimgears.slimcompose.services.ErrorHandler;

/**
 * Created by ditskovi on 10/23/2015.
 *
 */
public interface AppComponentBase {
    SharedPreferences preferences();
    Context context();
    Resources resources();
    ErrorHandler errorHandler();
    ActivityResultDispatcher activityResultDispatcher();
    NotificationManager notificationManager();
    ConnectivityManager connectivityManager();
    EventBus eventBus();
}
