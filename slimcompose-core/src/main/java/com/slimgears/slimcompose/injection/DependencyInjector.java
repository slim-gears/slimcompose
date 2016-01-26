// Copyright 2015 Denis Itskovich
// Refer to LICENSE.txt for license details
package com.slimgears.slimcompose.injection;

/**
 * Created by ditskovi on 1/25/2016.
 *
 */
public interface DependencyInjector<T> {
    void inject(T target);
}
