package com.musicode.criminalintent;

import android.support.v4.app.Fragment;

public class BaseFragment extends Fragment {

    public final boolean isActive() {
        return isAdded();
    }

}
