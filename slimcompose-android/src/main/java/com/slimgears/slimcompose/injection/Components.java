// Copyright 2015 Denis Itskovich
// Refer to LICENSE.txt for license details
package com.slimgears.slimcompose.injection;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.app.Service;

import com.slimgears.slimcompose.activity.ActivityComponentBase;
import com.slimgears.slimcompose.app.AppComponentBase;
import com.slimgears.slimcompose.service.ServiceComponentBase;

/**
 * Created by ditskovi on 1/25/2016.
 *
 */
public class Components {
    public static <C> C getComponent(Object hasComponent, Class<C> componentClass) {
        if (!(hasComponent instanceof HasComponent)) {
            throw new RuntimeException(String.format("%1s must implement HasComponent<%2s>", hasComponent.getClass().getSimpleName(), componentClass.getSimpleName()));
        }

        return componentClass.cast(((HasComponent)hasComponent).getComponent());
    }

    public static <C extends ActivityComponentBase> C getActivityComponent(Activity activity, Class<C> componentClass) {
        return getComponent(activity, componentClass);
    }

    public static <C extends ActivityComponentBase> C getActivityComponent(Fragment fragment, Class<C> componentClass) {
        return getActivityComponent(fragment.getActivity(), componentClass);
    }

    public static <C extends AppComponentBase> C getAppComponent(Application app, Class<C> componentClass) {
        return getComponent(app, componentClass);
    }

    public static <C extends AppComponentBase> C getAppComponent(Activity activity, Class<C> componentClass) {
        return getAppComponent(activity.getApplication(), componentClass);
    }

    public static <C extends AppComponentBase> C getAppComponent(Fragment fragment, Class<C> componentClass) {
        return getAppComponent(fragment.getActivity().getApplication(), componentClass);
    }

    public static <C extends ServiceComponentBase> C getServiceComponent(Service service, Class<C> componentClass) {
        return getComponent(service, componentClass);
    }
}
