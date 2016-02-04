package com.slimgears.slimcompose.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;

import com.annimon.stream.Stream;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractFragmentController implements FragmentController {
    private static final String LAST_FRAGMENT = "last_fragment";
    private static final String TAG = AbstractFragmentController.class.getSimpleName();
    private final FragmentManager mFragmentManager;
    private final List<OnFragmentChangedListener> mListeners = new LinkedList<>();

    public AbstractFragmentController(FragmentManager fragmentManager) {
        mFragmentManager = fragmentManager;
        mFragmentManager.addOnBackStackChangedListener(() -> invokeListeners(getCurrentFragment(), mFragmentManager.getBackStackEntryCount()));
    }

    @Override
    public boolean popFragment() {
        if (mFragmentManager.getBackStackEntryCount() <= 1) return false;
        mFragmentManager.popBackStackImmediate();
        return true;
    }

    @Override
    public void retainLastFragment(Bundle applicationSaveState) {
        String currentFragmentTag = getCurrentFragmentTag();
        if (currentFragmentTag != null) {
            Log.d(TAG, "Saving current fragment tag: " + currentFragmentTag);
            applicationSaveState.putString(LAST_FRAGMENT, currentFragmentTag);
        }
    }

    @Override
    public void restoreLastFragment(Bundle applicationSavedState) {
        String lastFragmentTag = applicationSavedState != null ? applicationSavedState.getString(LAST_FRAGMENT) : null;
        if (lastFragmentTag != null) {
            Log.d(TAG, "Found retained fragment, restoring: " + lastFragmentTag);
            setFragment(mFragmentManager.findFragmentByTag(lastFragmentTag));
        }
    }

    @Override
    public void pushFragment(Fragment fragment) {
        String tag = fragment.getClass().getSimpleName();
        mFragmentManager
                .beginTransaction()
                .replace(getContainerViewId(), fragment, tag)
                .addToBackStack(tag)
                .commitAllowingStateLoss();

        mFragmentManager.executePendingTransactions();
    }

    @Override
    public void setFragment(Fragment fragment) {
        if (isEmpty()) {
            pushFragment(fragment);
            return;
        }

        String tag = fragment.getClass().getSimpleName();
        if (tag.equals(getCurrentFragmentTag())) return;

        mFragmentManager
                .beginTransaction()
                .replace(getContainerViewId(), fragment, tag)
                .commitAllowingStateLoss();

        mFragmentManager.executePendingTransactions();
    }

    private void invokeListeners(Fragment fragment, int stackIndex) {
        Stream.of(mListeners).forEach(listener -> listener.onFragmentChanged(fragment, stackIndex));
    }

    @Override
    public boolean isEmpty() {
        return mFragmentManager.getBackStackEntryCount() == 0;
    }

    public void addListener(OnFragmentChangedListener listener) {
        mListeners.add(listener);
    }

    public void removeListener(OnFragmentChangedListener listener) {
        mListeners.remove(listener);
    }

    private String getCurrentFragmentTag() {
        Fragment fragment = getCurrentFragment();
        return fragment != null ? fragment.getClass().getSimpleName() : null;
    }

    private Fragment getCurrentFragment() {
        return mFragmentManager.findFragmentById(getContainerViewId());
    }

    protected abstract int getContainerViewId();
}
