package com.musicode.nerdlauncher;

import android.support.v4.app.Fragment;
import android.os.Bundle;

public abstract class SingleFragmentActivity extends BaseActivity {

    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_fragment);

        if (!hasFragment(R.id.fragment_container)) {
            addFragment(R.id.fragment_container, createFragment());
        }

    }
}
