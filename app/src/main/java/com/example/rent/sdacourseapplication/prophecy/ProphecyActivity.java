package com.example.rent.sdacourseapplication.prophecy;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Color;
import android.hardware.SensorManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.rent.sdacourseapplication.R;
import com.squareup.seismic.ShakeDetector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ProphecyActivity extends AppCompatActivity implements ShakeDetector.Listener {

    private Random random = new Random();
    private FrameLayout layer;
    //    List<String> prophecyList = new ArrayList<>();
//    prophecyList.add("Twoja stara gryzie meble");
//    prophecyList.add("Hokus pokus Twoja stara to ford focus");
//    prophecyList.add("Wygrałeś 1mln $; zadzwoń pod: 0700-72-772");\
    private List<String> prophecyList = Arrays.asList("Twoja stara gryzie meble", "Hokus pokus Twoja stara to ford focus", "Wygrałeś 1mln $; zadzwoń pod: 0700-72-772");


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prophecy);

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        ShakeDetector sd = new ShakeDetector(this);
        sd.start(sensorManager);

        layer = (FrameLayout) findViewById(R.id.prophecy_container);

        FrameLayout parentLayout = (FrameLayout) findViewById(R.id.activity_prophecy);
        parentLayout.setOnTouchListener(new View.OnTouchListener() {

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    animateCircularReveal((int) event.getX(), (int) event.getY(), layer);
                }
                return true;
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void animateCircularReveal(int x, int y, final FrameLayout layer) {
        if (layer.getVisibility() == View.VISIBLE) {
            Animator circularReveal = ViewAnimationUtils.createCircularReveal(layer, x, y, layer.getHeight(), 0);
            circularReveal.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    layer.setVisibility(View.INVISIBLE);
                }
            });
            circularReveal.start();
        } else {
            Animator circularReveal = ViewAnimationUtils.createCircularReveal(layer, x, y, 0, layer.getHeight());
            int color = Color.argb(255, random.nextInt(255), random.nextInt(255), random.nextInt(255));
            layer.setBackgroundColor(color);
            TextView prophecy = (TextView) findViewById(R.id.prophecy_text);
            prophecy.setText(prophecyList.get(random.nextInt(prophecyList.size())));
            layer.setVisibility(View.VISIBLE);
            circularReveal.start();


        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void hearShake() {

        int color = Color.argb(255, random.nextInt(255), random.nextInt(255), random.nextInt(255));
        layer.setBackgroundColor(color);
        animateCircularReveal(random.nextInt(layer.getWidth()), random.nextInt(layer.getHeight()), layer);
    }
}
