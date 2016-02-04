package com.slimgears.slimcompose.app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;

import com.slimgears.slimbus.EventBus;
import com.slimgears.slimbus.EventBusFactory;
import com.slimgears.slimcompose.activity.ActivityResultDispatcher;
import com.slimgears.slimcompose.activity.DefaultActivityResultDispatcher;
import com.slimgears.slimprefs.PreferenceInjector;
import com.slimgears.slimprefs.PreferenceInjectorFactory;
import com.slimgears.slimprefs.PreferenceProvider;
import com.slimgears.slimprefs.SharedPreferenceProvider;

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
    private final PreferenceInjectorFactory mPreferenceInjectorFactory;

    public AppModuleBase(Application app, EventBusFactory busFactory, PreferenceInjectorFactory preferenceInjectorFactory) {
        mApp = app;
        mBusFactory = busFactory;
        mPreferenceInjectorFactory = preferenceInjectorFactory;
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

    @Provides @Singleton
    public PreferenceProvider providePreferenceProvider(Context context) {
        return new SharedPreferenceProvider(context);
    }

    @Provides @Singleton
    public PreferenceInjector providePreferenceInjector(PreferenceProvider preferenceProvider) {
        return mPreferenceInjectorFactory.createInjector(preferenceProvider);
    }

    protected void addErrorHandlers(CompositeErrorHandler compositeErrorHandler) {
        compositeErrorHandler.add(new LogcatErrorHandler());
        compositeErrorHandler.add(new ToastErrorHandler(provideContext()));
    }
}
