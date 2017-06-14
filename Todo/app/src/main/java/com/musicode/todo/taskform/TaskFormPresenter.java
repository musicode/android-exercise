package com.musicode.todo.taskform;

import com.musicode.todo.model.Task;

import java.util.UUID;

public class TaskFormPresenter implements TaskFormContract.Presenter {

    private UUID mTaskId;
    private TaskFormFragment mView;
    private boolean mShouldLoadDataFromRepo;

    public TaskFormPresenter(UUID taskId, TaskFormFragment view, boolean shouldLoadDataFromRepo) {
        mTaskId = taskId;
        mView = view;
        mShouldLoadDataFromRepo = shouldLoadDataFromRepo;

        mView.setPresenter(this);
    }

    @Override
    public void saveTask(String title, String content) {

    }

}
