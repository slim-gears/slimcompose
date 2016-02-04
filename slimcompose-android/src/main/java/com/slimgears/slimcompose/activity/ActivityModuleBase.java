package com.slimgears.slimcompose.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.view.LayoutInflater;

import com.slimgears.slimcompose.extensibility.DefaultPluginContainer;
import com.slimgears.slimcompose.extensibility.PluginContainer;
import com.slimgears.slimcompose.injection.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ditskovi on 10/23/2015.
 *
 */
@Module
public abstract class ActivityModuleBase {
    private final Activity mActivity;

    protected ActivityModuleBase(Activity activity) {
        this.mActivity = activity;
    }

    @Provides @PerActivity
    public Activity provideActivity() {
        return mActivity;
    }

    @Provides @PerActivity
    public FragmentManager provideFragmentManager() {
        return mActivity.getFragmentManager();
    }

    @Provides @PerActivity
    public LayoutInflater provideLayoutInflater() {
        return LayoutInflater.from(mActivity);
    }

    @Provides @PerActivity
    public PluginContainer<ActivityPlugin> providePluginContainer() {
        PluginContainer<ActivityPlugin> pluginContainer = new DefaultPluginContainer<>();
        addPlugins(pluginContainer);
        return pluginContainer;
    }

    protected void addPlugins(PluginContainer<ActivityPlugin> pluginContainer) {

    }
}
