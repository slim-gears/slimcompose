// Copyright 2015 Denis Itskovich
// Refer to LICENSE.txt for license details
package com.slimgears.slimcompose.services;

import android.content.Intent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by ditskovi on 11/7/2015.
 *
 */
public class DefaultIncomingIntentDispatcher implements IncomingIntentDispatcher {
    private final List<Subscription> subscriptions = new ArrayList<>();
    private final Map<OnIncomingIntentListener, List<Subscription>> subscriptionByListener = new HashMap<>();

    class Subscription {
        private final Filter filter;
        private final OnIncomingIntentListener listener;

        Subscription(Filter filter, OnIncomingIntentListener listener) {
            this.filter = filter;
            this.listener = listener;
        }

        void dispatch(Intent intent) {
            if (filter.match(intent)) listener.onIncomingIntent(intent);
        }
    }

    class DefaultFilter implements FilterBuilder, Filter {
        private final Set<String> actions = new HashSet<>();
        private final Set<String> categories = new HashSet<>();
        private final Set<String> types = new HashSet<>();
        private final Set<String> packages = new HashSet<>();

        @Override
        public boolean match(Intent intent) {
            return
                    (packages.isEmpty() || packages.contains(intent.getPackage())) &&
                    (actions.isEmpty() || actions.contains(intent.getAction())) &&
                    (types.isEmpty() || types.contains(intent.getType())) &&
                    (categories.isEmpty() || categories.containsAll(intent.getCategories()));
        }

        @Override
        public FilterBuilder actions(String action, String... otherActions) {
            actions.add(action);
            actions.addAll(Arrays.asList(otherActions));
            return this;
        }

        @Override
        public FilterBuilder categories(String category, String... otherCategories) {
            categories.add(category);
            categories.addAll(Arrays.asList(otherCategories));
            return this;
        }

        @Override
        public FilterBuilder types(String type, String... otherTypes) {
            types.add(type);
            types.addAll(Arrays.asList(otherTypes));
            return this;
        }

        @Override
        public FilterBuilder packages(String pkg, String... otherPackages) {
            packages.add(pkg);
            packages.addAll(Arrays.asList(otherPackages));
            return this;
        }

        @Override
        public Filter build() {
            return this;
        }
    }

    @Override
    public void dispatchIntent(Intent intent) {
        for (Subscription subscription : subscriptions) {
            subscription.dispatch(intent);
        }
    }

    @Override
    public void subscirbe(Filter filter, OnIncomingIntentListener listener) {
        Subscription subscription = new Subscription(filter, listener);
        if (subscriptionByListener.containsKey(listener)) {
            subscriptionByListener.get(listener).add(subscription);
        } else {
            subscriptionByListener.put(listener, new ArrayList<>(Collections.singletonList(subscription)));
        }

        subscriptions.add(subscription);
    }

    @Override
    public void unsubscribe(OnIncomingIntentListener listener) {
        if (subscriptionByListener.containsKey(listener)) {
            subscriptions.removeAll(subscriptionByListener.get(listener));
            subscriptionByListener.remove(listener);
        }
    }

    @Override
    public FilterBuilder filterBuilder() {
        return new DefaultFilter();
    }
}
