package com.musicode.todo.taskform;

import com.musicode.todo.BasePresenter;
import com.musicode.todo.BaseView;

public interface TaskFormContract {

    interface View extends BaseView<Presenter> {

        void setTitle(String title);

        void setContent(String content);

        // 显示表单验证的错误信息
        void showError(int resId);

        void finish();

        boolean isActive();

    }

    interface Presenter extends BasePresenter {

        void saveTask(String title, String content);

    }

}
