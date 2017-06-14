package com.musicode.todo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public abstract class SingleFragmentActivity extends AppCompatActivity {

    protected static int LAYOUT_ID = R.layout.single_fragment_activity;

    protected abstract Fragment createFragment();

    protected void readIntent(Intent intent) {

    }

    protected void initDrawer(Bundle savedInstanceState) {

    }

    protected void initToolbar(Bundle savedInstanceState) {

    }

    protected void initPresenter(Bundle savedInstanceState, Fragment fragment) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT_ID);

        readIntent(getIntent());
        initDrawer(savedInstanceState);
        initToolbar(savedInstanceState);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = createFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }

        initPresenter(savedInstanceState, fragment);

    }

}
