package com.example.rent.sdacourseapplication.drawing.app;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class SimpleDrawingView extends View {

    private Paint paint;
    private List<CustomPath> paths = new ArrayList<>();

    @ColorInt
    private int currentColor = Color.BLACK;

    public void setCurrentColor(@ColorInt int color) {
        this.currentColor = color;
    }

    public SimpleDrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (CustomPath customPath : paths) {
            paint.setColor(customPath.getColor());
            canvas.drawPath(customPath.getPath(), paint);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                paths.add(new CustomPath(currentColor, new Point(x,y)));
                break;
            }

            case MotionEvent.ACTION_MOVE: {
                paths.get(paths.size() -1).getPath().lineTo(x, y);
                break;
            }

        }
        Log.d("log", "onTouchEvent");
        postInvalidate();
        return true;
    }

    public void clear() {
        paths.clear();
        postInvalidate();
    }


}
