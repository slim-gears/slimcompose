// Copyright 2015 Denis Itskovich
// Refer to LICENSE.txt for license details
package com.slimgears.slimcompose.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.slimgears.slimbus.EventBus;
import com.slimgears.slimcompose.extensibility.PluginContainer;
import com.slimgears.slimcompose.injection.DependencyInjector;
import com.slimgears.slimcompose.injection.HasComponent;
import com.slimgears.slimprefs.PreferenceBinding;
import com.slimgears.slimprefs.PreferenceInjector;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by ditskovi on 1/27/2016.
 *
 */
public abstract class AbstractActivity<C extends ActivityComponentBase, A extends AbstractActivity<C, A>> extends AppCompatActivity implements HasComponent<C> {
    private C mComponent;
    private EventBus.Subscription mSubscription;
    private PreferenceBinding mPreferenceBinding;

    @Inject EventBus mEventBus;
    @Inject PluginContainer<ActivityPlugin> mPluginContainer;
    @Inject ActivityResultDispatcher mActivityResultDispatcher;
    @Inject PreferenceInjector mPreferenceInjector;

    protected abstract C createComponent();
    protected abstract DependencyInjector<A> createInjector(C component);
    protected abstract void onSetContentView();

    @Override
    public void onCreate(Bundle savedInstance) {
        mComponent = createComponent();
        //noinspection unchecked
        createInjector(mComponent).inject((A)this);

        super.onCreate(savedInstance);
        onSetContentView();

        ButterKnife.bind(this);
        mPreferenceBinding = mPreferenceInjector.bind(this);
        mPluginContainer.forEachPlugin(p -> p.onBind(this, savedInstance));
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mSubscription = mEventBus.subscribe(this);
        mPluginContainer.forEachPlugin(ActivityPlugin::onResume);
    }

    @Override
    public void onPause() {
        mPluginContainer.forEachPlugin(ActivityPlugin::onPause);
        mSubscription.unsubscribe();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mPluginContainer.forEachPlugin(p -> p.onUnbind(this));
        mPreferenceBinding.unbind();
        super.onDestroy();
    }

    @Override
    public C getComponent() {
        return mComponent;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mActivityResultDispatcher.dispatchResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
