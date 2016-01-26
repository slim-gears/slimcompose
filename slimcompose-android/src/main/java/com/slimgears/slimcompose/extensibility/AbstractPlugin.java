// Copyright 2015 Denis Itskovich
// Refer to LICENSE.txt for license details
package com.slimgears.slimcompose.extensibility;

import com.slimgears.slimbus.EventBus;
import com.slimgears.slimcompose.injection.AppComponentBase;

/**
 * Created by ditskovi on 1/3/2016.
 *
 */
public abstract class AbstractPlugin<TTarget, TBindData, TComponent extends AppComponentBase> implements Plugin<TTarget, TBindData> {
    private final Class<TComponent> mComponentClass;
    private TComponent mComponent;
    private EventBus.Subscription mSubscription;

    protected AbstractPlugin(Class<TComponent> mComponentClass) {
        this.mComponentClass = mComponentClass;
    }

    @Override
    public void onBind(TTarget target, TBindData bindData) {
        mComponent = getComponent(target, mComponentClass);
        onBind(mComponent);
        mSubscription = mComponent.eventBus().subscribe(this);
    }

    @Override
    public void onUnbind(TTarget target) {
        mSubscription.unsubscribe();
        onUnbind();
    }

    protected TComponent getComponent() {
        return mComponent;
    }

    protected void onUnbind() {}

    protected abstract void onBind(TComponent component);
    protected abstract TComponent getComponent(TTarget target, Class<TComponent> componentClass);
}
