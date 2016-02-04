// Copyright 2015 Denis Itskovich
// Refer to LICENSE.txt for license details
package com.slimgears.slimcompose.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.slimgears.slimbus.EventBus;
import com.slimgears.slimcompose.extensibility.PluginContainer;
import com.slimgears.slimcompose.injection.DependencyInjector;
import com.slimgears.slimcompose.injection.HasComponent;
import com.slimgears.slimprefs.PreferenceBinding;
import com.slimgears.slimprefs.PreferenceInjector;

import javax.inject.Inject;

/**
 * Created by ditskovi on 1/28/2016.
 *
 */
public abstract class AbstractService<C extends ServiceComponentBase, S extends AbstractService<C, S>>
        extends Service implements HasComponent<C> {
    private C mComponent;

    private EventBus.Subscription mSubscription;
    private PreferenceBinding mPreferenceBinding;

    @Inject PluginContainer<ServicePlugin> mPluginContainer;
    @Inject PreferenceInjector mPreferenceInjector;
    @Inject EventBus mEventBus;

    private IBinder mBinder = new Binder() {
        S getService() {
            //noinspection unchecked
            return (S)AbstractService.this;
        }
    };

    protected abstract C createComponent();
    protected abstract DependencyInjector<S> createInjector(C component);

    @Override
    public C getComponent() {
        return mComponent;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mComponent = createComponent();
        //noinspection unchecked
        createInjector(mComponent).inject((S)this);
        mPreferenceBinding = mPreferenceInjector.bind(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mSubscription = mEventBus.subscribe(this);
        mPluginContainer.forEachPlugin(p -> p.onBind(this, intent));
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        mPluginContainer.forEachPlugin(p -> p.onUnbind(this));
        mSubscription.unsubscribe();
        mPreferenceBinding.unbind();
        super.onDestroy();
    }
}
