// Copyright 2015 Denis Itskovich
// Refer to LICENSE.txt for license details
package com.slimgears.slimcompose.service;

import android.app.Service;
import android.content.Intent;

import com.slimgears.slimcompose.extensibility.AbstractPlugin;
import com.slimgears.slimcompose.injection.Components;

/**
 * Created by ditskovi on 11/6/2015.
 *
 */
public abstract class AbstractServicePlugin<TComponent extends ServiceComponentBase>
        extends AbstractPlugin<Service, Intent, TComponent> implements ServicePlugin {

    protected AbstractServicePlugin(Class<TComponent> componentClass) {
        super(componentClass);
    }

    @Override
    protected TComponent getComponent(Service service, Class<TComponent> componentClass) {
        return Components.getServiceComponent(service, componentClass);
    }
}
