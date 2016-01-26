// Copyright 2015 Denis Itskovich
// Refer to LICENSE.txt for license details
package com.slimgears.slimcompose.injection;

import android.app.NotificationManager;
import android.content.Context;
import android.net.ConnectivityManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ditskovi on 1/4/2016.
 *
 */
@Module
public class AndroidServiceModule {
    @Provides @Singleton
    public NotificationManager provideNotificationManager(Context context) {
        return getSystemService(context, NotificationManager.class, Context.NOTIFICATION_SERVICE);
    }

    @Provides @Singleton
    public ConnectivityManager provideConnectivityManager(Context context) {
        return getSystemService(context, ConnectivityManager.class, Context.CONNECTIVITY_SERVICE);
    }

    private static <T> T getSystemService(Context context, Class<T> serviceClass, String id) {
        return serviceClass.cast(context.getSystemService(id));
    }
}
