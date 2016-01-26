// Copyright 2015 Denis Itskovich
// Refer to LICENSE.txt for license details
package com.slimgears.slimcompose.injection;

import android.app.Service;

import com.slimgears.slimcompose.extensibility.PluginContainer;
import com.slimgears.slimcompose.extensibility.ServicePlugin;

/**
 * Created by ditskovi on 1/3/2016.
 *
 */
public interface ServiceComponentBase extends AppComponentBase {
    Service service();
    PluginContainer<ServicePlugin> pluginContainer();
}
