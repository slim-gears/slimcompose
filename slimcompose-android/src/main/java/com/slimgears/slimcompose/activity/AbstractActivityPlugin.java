// Copyright 2015 Denis Itskovich
// Refer to LICENSE.txt for license details
package com.slimgears.slimcompose.activity;

import android.app.Activity;
import android.os.Bundle;

import com.slimgears.slimcompose.extensibility.AbstractPlugin;
import com.slimgears.slimcompose.injection.Components;

/**
 * Created by ditskovi on 11/6/2015.
 *
 */
public abstract class AbstractActivityPlugin<TComponent extends ActivityComponentBase>
        extends AbstractPlugin<Activity, Bundle, TComponent> implements ActivityPlugin {

    protected AbstractActivityPlugin(Class<TComponent> componentClass) {
        super(componentClass);
    }

    @Override
    protected TComponent getComponent(Activity activity, Class<TComponent> componentClass) {
        return Components.getActivityComponent(activity, componentClass);
    }

    @Override
    public void onResume() {}

    @Override
    public void onPause() {}
}
