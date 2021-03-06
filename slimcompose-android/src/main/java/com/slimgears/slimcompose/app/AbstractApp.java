// Copyright 2015 Denis Itskovich
// Refer to LICENSE.txt for license details
package com.slimgears.slimcompose.app;

import android.app.Application;

import com.slimgears.slimbus.EventBus;
import com.slimgears.slimcompose.injection.HasComponent;
import com.slimgears.slimprefs.PreferenceBinding;

/**
 * Created by ditskovi on 1/26/2016.
 *
 */
public abstract class AbstractApp<C extends AppComponentBase> extends Application implements HasComponent<C> {
    private final Thread.UncaughtExceptionHandler mPrevExceptionHandler;
    private C mComponent;
    private EventBus.Subscription mSubscription;
    private PreferenceBinding mPreferenceBinding;

    protected AbstractApp() {
        mPrevExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this::onUncaughtException);
    }

    protected void onUncaughtException(Thread thread, Throwable exception) {
        mComponent.errorHandler().onError(exception);
        mPrevExceptionHandler.uncaughtException(thread, exception);
    }

    protected abstract C createComponent();

    @Override
    public void onCreate() {
        mComponent = createComponent();
        super.onCreate();
        mSubscription = getComponent().eventBus().subscribe(this);
        mPreferenceBinding = getComponent().preferenceInjector().bind(this);
    }

    @Override
    public void onTerminate() {
        mSubscription.unsubscribe();
        mPreferenceBinding.unbind();
        super.onTerminate();
    }

    @Override
    public C getComponent() {
        return mComponent;
    }
}
