package com.example.rent.sdacourseapplication.milionaires.quiz;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rent.sdacourseapplication.R;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class MillionairesActivity extends AppCompatActivity implements View.OnClickListener {

    private QuizContainer quizContainer;
    private SingleAnswer firstAnswer;
    private SingleAnswer secondAnswer;
    private SingleAnswer thirdAnswer;
    private SingleAnswer fourthAnswer;

    private static final String INDEX_KEY = "index_key";
    private int currentQuestionIndex = 0;
    private QuizQuestion quizQuestion;
    private boolean wasClicked;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_milionaires);

        //if(currentQuestionIndex<=quizContainer.getQuestionsCount()) {
            currentQuestionIndex = getIntent().getIntExtra(INDEX_KEY, 0);
        //}else{
        //    Toast.makeText(MillionairesActivity.this, "Koniec putań", Toast.LENGTH_SHORT).show();
        //}

        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        ValueAnimator objectAnimator = ObjectAnimator.ofInt(0, 100);
        objectAnimator.setDuration(10000);
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                progressBar.setProgress((Integer) animation.getAnimatedValue());
            }
        });
        objectAnimator.start();
        //ObjectAnimator.ofInt(progressBar, "progress", 0, 100);
        String json = null;

        try {
            json = loadQuizJson();
            quizContainer = new Gson().fromJson(json, QuizContainer.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        TextView question = (TextView) findViewById(R.id.question);
        TextView answer1 = (TextView) findViewById(R.id.answer_one);
        TextView answer2 = (TextView) findViewById(R.id.answer_two);
        TextView answer3 = (TextView) findViewById(R.id.answer_three);
        TextView answer4 = (TextView) findViewById(R.id.answer_four);

        quizQuestion = quizContainer.getQuestions().get(currentQuestionIndex);
        question.setText(quizQuestion.getQuestion());

        firstAnswer = quizContainer.getQuestions().get(currentQuestionIndex).getAnswers().get(0);
        answer1.setText(firstAnswer.getText());
        answer1.setTag(firstAnswer.isCorrect());

        secondAnswer = quizContainer.getQuestions().get(currentQuestionIndex).getAnswers().get(1);
        answer2.setText(secondAnswer.getText());
        answer2.setTag(secondAnswer.isCorrect());

        thirdAnswer = quizContainer.getQuestions().get(currentQuestionIndex).getAnswers().get(2);
        answer3.setText(thirdAnswer.getText());
        answer3.setTag(thirdAnswer.isCorrect());

        fourthAnswer = quizContainer.getQuestions().get(currentQuestionIndex).getAnswers().get(3);
        answer4.setText(fourthAnswer.getText());
        answer4.setTag(fourthAnswer.isCorrect());

        answer1.setOnClickListener(this);
        answer2.setOnClickListener(this);
        answer3.setOnClickListener(this);
        answer4.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        if (!wasClicked) {
            if ((Boolean) v.getTag()) {
                Toast.makeText(v.getContext(), "Odpowiedź prawidłowa", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(v.getContext(), "Zła odpowiedź", Toast.LENGTH_SHORT).show();
            }
//        MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.);
//        mediaPlayer.start();
            v.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(MillionairesActivity.this, MillionairesActivity.class);
                    intent.putExtra(INDEX_KEY, ++currentQuestionIndex);
                    startActivity(intent);
                }
            }, 3000);
            wasClicked = true;
        }
    }

    public String loadQuizJson() throws IOException {
        StringBuffer sb = new StringBuffer();
        InputStream json = getAssets().open("quiz_data.json");
        BufferedReader br = new BufferedReader(new InputStreamReader(json, "UTF-8"));
        String str;
        while ((str = br.readLine()) != null) {
            sb.append(str);
        }
        br.close();
        return sb.toString();
    }


}
