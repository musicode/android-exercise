package com.musicode.todo.model;

import java.io.Serializable;

public class Task implements Serializable {

    private String mTitle;
    private String mContent;

    public Task() {
        mTitle = "";
        mContent = "";
    }

    public Task(String title, String content) {
        mTitle = title;
        mContent = content;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }
}
