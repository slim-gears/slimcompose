// Copyright 2015 Denis Itskovich
// Refer to LICENSE.txt for license details
package com.slimgears.slimcompose.app;

/**
 * Created by ditskovi on 11/24/2015.
 *
 */
public interface CompositeErrorHandler extends ErrorHandler {
    void add(ErrorHandler handler);
    void remove(ErrorHandler handler);
}
