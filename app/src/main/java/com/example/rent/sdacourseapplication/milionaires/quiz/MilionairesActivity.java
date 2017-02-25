package com.example.rent.sdacourseapplication.milionaires.quiz;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.example.rent.sdacourseapplication.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class MilionairesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_milionaires);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        ValueAnimator objectAnimator = ObjectAnimator.ofInt(0,100);
        objectAnimator.setDuration(10000);
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                progressBar.setProgress((Integer) animation.getAnimatedValue());
            }
        });
        objectAnimator.start();
        //ObjectAnimator.ofInt(progressBar, "prograss", 0, 100);
        String json = null;
        try {
            json = loadQuizJson();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public String loadQuizJson() throws IOException{
        StringBuffer sb = new StringBuffer();
        InputStream json = getAssets().open("quiz_data.json");
        BufferedReader br = new BufferedReader(new InputStreamReader(json, "UTF-8"));
        String str;
        while((str=br.readLine())!=null){
            sb.append(str);
        }
        br.close();
        return sb.toString();
    }
}
