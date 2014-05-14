package com.noveogroup.googoltoone.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import com.noveogroup.googoltoone.R;

public final class FragmentUtils {

    private FragmentUtils() {
        throw new UnsupportedOperationException();
    }

    public static void startFragment(Fragment fragment, FragmentManager fragmentManager) {
        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }
}
