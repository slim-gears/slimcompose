// Copyright 2015 Denis Itskovich
// Refer to LICENSE.txt for license details
package com.slimgears.slimcompose.services;

import android.content.Intent;

/**
 * Created by ditskovi on 11/7/2015.
 *
 */
public interface IncomingIntentDispatcher {
    interface OnIncomingIntentListener {
        void onIncomingIntent(Intent intent);
    }

    interface Filter {
        boolean match(Intent intent);
    }

    interface FilterBuilder {
        FilterBuilder actions(String action, String... otherActions);
        FilterBuilder categories(String category, String... otherCategories);
        FilterBuilder types(String type, String... otherTypes);
        FilterBuilder packages(String pkg, String... otherPackages);
        Filter build();
    }

    void dispatchIntent(Intent intent);
    void subscirbe(Filter filter, OnIncomingIntentListener listener);
    void unsubscribe(OnIncomingIntentListener listener);
    FilterBuilder filterBuilder();
}
