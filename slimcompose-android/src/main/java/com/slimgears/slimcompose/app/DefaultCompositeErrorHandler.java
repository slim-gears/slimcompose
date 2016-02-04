// Copyright 2015 Denis Itskovich
// Refer to LICENSE.txt for license details
package com.slimgears.slimcompose.app;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ditskovi on 11/24/2015.
 *
 */
public class DefaultCompositeErrorHandler implements CompositeErrorHandler {
    private List<ErrorHandler> mHandlers = new LinkedList<>();

    @Override
    public void add(ErrorHandler handler) {
        mHandlers.add(handler);
    }

    @Override
    public void remove(ErrorHandler handler) {
        mHandlers.remove(handler);
    }

    @Override
    public void onError(Throwable e) {
        for (ErrorHandler handler : mHandlers) {
            handler.onError(e);
        }
    }
}
