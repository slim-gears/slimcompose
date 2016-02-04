package com.slimgears.slimcompose.activity;

import android.app.Fragment;
import android.os.Bundle;

/**
 * Created by omeir1 on 10/16/2015.
 *
 */
public interface FragmentController {
    interface OnFragmentChangedListener {
        void onFragmentChanged(Fragment fragment, int stackIndex);
    }

    void pushFragment(Fragment fragment);
    boolean popFragment();
    void setFragment(Fragment fragment);
    boolean isEmpty();
    void retainLastFragment(Bundle applicationSaveState);
    void restoreLastFragment(Bundle applicationSavedState);

    void addListener(OnFragmentChangedListener listener);
    void removeListener(OnFragmentChangedListener listener);
}
