package com.musicode.criminalintent.list;

import android.support.v4.app.Fragment;

import com.musicode.criminalintent.SingleFragmentActivity;

public class ListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return ListFragment.newInstance();
    }

}
