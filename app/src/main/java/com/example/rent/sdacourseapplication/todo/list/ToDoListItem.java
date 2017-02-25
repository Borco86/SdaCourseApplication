package com.example.rent.sdacourseapplication.todo.list;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by RENT on 2017-02-23.
 */

public class ToDoListItem implements Parcelable{

    private String text;
    private boolean isChecked;

    public ToDoListItem(String text) {
        this.text = text;
    }

    protected ToDoListItem(Parcel in) {
        text = in.readString();
        isChecked = in.readByte() != 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(text);
        out.writeByte((byte) (isChecked ? 1 : 0));
    }
    public static final Creator<ToDoListItem> CREATOR = new Creator<ToDoListItem>() {
        @Override
        public ToDoListItem createFromParcel(Parcel in) {
            return new ToDoListItem(in);
        }

        @Override
        public ToDoListItem[] newArray(int size) {
            return new ToDoListItem[size];
        }
    };

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
