package com.example.rent.sdacourseapplication.MVP;


import nucleus.presenter.Presenter;

/**
 * Created by RENT on 2017-03-04.
 */
public class MvpPresenter extends Presenter<MvpActivity> {

    private LongRunningTask longRunningTask = new LongRunningTask();

    public void executeLongRunningTask() {
        new Thread() {
            @Override
            public void run() {
                String result = longRunningTask.execute();
                getView().setTextOnUiThread(result);
            }
        }.start();
    }

}
