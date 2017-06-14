package com.musicode.todo.taskform;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.musicode.todo.R;

public class TaskFormFragment extends Fragment implements TaskFormContract.View {

    private EditText mTitleText;
    private EditText mContentText;
    private FloatingActionButton mSaveButton;

    private TaskFormContract.Presenter mPresenter;

    public TaskFormFragment() {
        // Required empty public constructor
    }

    public static TaskFormFragment newInstance() {
        return new TaskFormFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_form, container, false);

        mTitleText = (EditText) view.findViewById(R.id.add_task_title);
        mContentText = (EditText) view.findViewById(R.id.add_task_content);

        mSaveButton = (FloatingActionButton) view.findViewById(R.id.save_button);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = mTitleText.getText().toString();
                String content = mContentText.getText().toString();
                mPresenter.saveTask(title, content);
            }
        });

        return view;
    }

    @Override
    public void setPresenter(TaskFormContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void setTitle(String title) {
        mTitleText.setText(title);
    }

    @Override
    public void setContent(String content) {
        mContentText.setText(content);
    }

    @Override
    public void showError(int resId) {
        Snackbar.make(mTitleText, resId, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void finish() {
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

}
