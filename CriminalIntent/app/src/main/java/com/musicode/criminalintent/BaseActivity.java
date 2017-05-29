package com.musicode.criminalintent;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    public boolean hasFragment(int containerViewId) {
        return getSupportFragmentManager().findFragmentById(containerViewId) != null;
    }

    public void addFragment(int containerViewId, Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(containerViewId, fragment)
                .commit();
    }
}
