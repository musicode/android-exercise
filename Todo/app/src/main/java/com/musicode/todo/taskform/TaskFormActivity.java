package com.musicode.todo.taskform;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.musicode.todo.R;
import com.musicode.todo.SingleFragmentActivity;
import com.musicode.todo.model.Task;

import java.io.Serializable;
import java.util.UUID;

public class TaskFormActivity extends SingleFragmentActivity {

    public static final String ARG_TASK_ID = "task_id";
    protected static int LAYOUT_ID = R.layout.activity_task_form;
    private static final String SHOULD_LOAD_DATA_FROM_REPO_KEY = "SHOULD_LOAD_DATA_FROM_REPO_KEY";

    private ActionBar mToolbar;
    private UUID mTaskId;

    private TaskFormContract.Presenter mPresenter;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // 如果没加载过数据，重进还需要加载
        outState.putBoolean(SHOULD_LOAD_DATA_FROM_REPO_KEY, false);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void readIntent(Intent intent) {
        mTaskId = (UUID) intent.getSerializableExtra(ARG_TASK_ID);
    }

    @Override
    protected void initToolbar(Bundle savedInstanceState) {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mToolbar = getSupportActionBar();
        mToolbar.setDisplayHomeAsUpEnabled(true);
        mToolbar.setDisplayShowHomeEnabled(true);

        if (mTaskId != null) {
            mToolbar.setTitle(R.string.add_task);
        }
        else {
            mToolbar.setTitle(R.string.edit_task);
        }
    }

    @Override
    protected void initPresenter(Bundle savedInstanceState, Fragment fragment) {
        boolean shouldLoadDataFromRepo = true;
        if (savedInstanceState != null) {
            shouldLoadDataFromRepo = savedInstanceState.getBoolean(SHOULD_LOAD_DATA_FROM_REPO_KEY);
        }
        mPresenter = new TaskFormPresenter(mTaskId, (TaskFormFragment) fragment, shouldLoadDataFromRepo);
    }

    @Override
    protected Fragment createFragment() {
        return TaskFormFragment.newInstance();
    }

}
