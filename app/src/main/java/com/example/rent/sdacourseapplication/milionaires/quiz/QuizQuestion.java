package com.example.rent.sdacourseapplication.milionaires.quiz;

import java.util.List;

/**
 * Created by RENT on 2017-02-25.
 */

public class QuizQuestion {

    private int id;
    private String question;
    private List<SingleAnswer> answers;

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public List<SingleAnswer> getAnswers() {
        return answers;
    }
}
