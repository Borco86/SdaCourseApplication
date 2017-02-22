package com.example.rent.sdacourseapplication.drawing.app;

import android.graphics.Path;
import android.graphics.Point;
import android.support.annotation.ColorInt;

/**
 * Created by RENT on 2017-02-18.
 */

public class CustomPath {

    @ColorInt
    private int color;
    private Path path;
    private Point point;

    public CustomPath(int color, Point point) {
        this.color = color;
        this.point = point;
        this.path = new Path();
        this.path.moveTo(point.x, point.y);
    }

    public int getColor() {
        return color;
    }

    public Path getPath() {
        return path;
    }
}
