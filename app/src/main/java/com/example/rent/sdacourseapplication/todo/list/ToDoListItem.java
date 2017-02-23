package com.example.rent.sdacourseapplication.todo.list;

/**
 * Created by RENT on 2017-02-23.
 */

public class ToDoListItem {

    private String text;
    private boolean isChecked;

    public ToDoListItem(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
