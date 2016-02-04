package com.slimgears.slimcompose.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.view.LayoutInflater;

import com.slimgears.slimcompose.extensibility.PluginContainer;
import com.slimgears.slimcompose.app.AppComponentBase;

/**
 * Created by ditskovi on 10/23/2015.
 *
 */
public interface ActivityComponentBase extends AppComponentBase {
    Activity activity();
    FragmentManager fragmentManager();
    LayoutInflater layoutInflater();
    PluginContainer<ActivityPlugin> pluginContainer();
}
