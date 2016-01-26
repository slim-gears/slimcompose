package com.slimgears.slimcompose.injection;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;

import com.slimgears.slimbus.EventBus;
import com.slimgears.slimbus.EventBusFactory;
import com.slimgears.slimcompose.services.ActivityResultDispatcher;
import com.slimgears.slimcompose.services.CompositeErrorHandler;
import com.slimgears.slimcompose.services.DefaultActivityResultDispatcher;
import com.slimgears.slimcompose.services.DefaultCompositeErrorHandler;
import com.slimgears.slimcompose.services.ErrorHandler;
import com.slimgears.slimcompose.services.LogcatErrorHandler;
import com.slimgears.slimcompose.services.ToastErrorHandler;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ditskovi on 10/23/2015.
 *
 */
@Module
public class AppModuleBase {
    private final Application mApp;
    private final EventBusFactory mBusFactory;

    public AppModuleBase(Application app, EventBusFactory busFactory) {
        mApp = app;
        mBusFactory = busFactory;
    }

    @Provides @Singleton
    public Context provideContext() {
        return mApp;
    }

    @Provides @Singleton
    public SharedPreferences providePreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides @Singleton
    public Resources provideResources(Context context) {
        return context.getResources();
    }

    @Provides @Singleton
    public CompositeErrorHandler providerCompositeErrorHandler() {
        CompositeErrorHandler handlers = new DefaultCompositeErrorHandler();
        addErrorHandlers(handlers);
        return handlers;
    }

    @Provides @Singleton
    public ErrorHandler provideErrorHandler(CompositeErrorHandler errorHandler) {
        return errorHandler;
    }

    @Provides @Singleton
    public ActivityResultDispatcher provideActivityResultDispatcher() {
        return new DefaultActivityResultDispatcher();
    }

    @Provides @Singleton
    public EventBus provideEventBus(Context context) {
        return mBusFactory.createEventBus();
    }

    protected void addErrorHandlers(CompositeErrorHandler compositeErrorHandler) {
        compositeErrorHandler.add(new LogcatErrorHandler());
        compositeErrorHandler.add(new ToastErrorHandler(provideContext()));
    }
}
