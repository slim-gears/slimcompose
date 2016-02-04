// Copyright 2015 Denis Itskovich
// Refer to LICENSE.txt for license details
package com.slimgears.slimcompose.service;

import android.app.Service;

import com.slimgears.slimcompose.app.AppComponentBase;
import com.slimgears.slimcompose.extensibility.PluginContainer;

/**
 * Created by ditskovi on 1/3/2016.
 *
 */
public interface ServiceComponentBase extends AppComponentBase {
    Service service();
    PluginContainer<ServicePlugin> pluginContainer();
}
