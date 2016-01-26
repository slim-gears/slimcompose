// Copyright 2015 Denis Itskovich
// Refer to LICENSE.txt for license details
package com.slimgears.slimcompose.extensibility;

/**
 * Created by ditskovi on 1/3/2016.
 *
 */
public interface Plugin<TTarget, TBindArgs> {
    void onBind(TTarget target, TBindArgs args);
    void onUnbind(TTarget target);
}
