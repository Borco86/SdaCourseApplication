package com.example.rent.sdacourseapplication.MVP;

/**
 * Created by RENT on 2017-03-04.
 */

public class LongRunningTask {
    public String execute(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "Udało się!";
    }
}
