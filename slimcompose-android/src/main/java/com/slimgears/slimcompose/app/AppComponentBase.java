package com.slimgears.slimcompose.app;

import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.ConnectivityManager;

import com.slimgears.slimbus.EventBus;
import com.slimgears.slimcompose.activity.ActivityResultDispatcher;
import com.slimgears.slimprefs.PreferenceInjector;
import com.slimgears.slimprefs.PreferenceProvider;

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
    PreferenceInjector preferenceInjector();
    PreferenceProvider preferenceProvider();
}
