package com.example.demo1.Model;

import javafx.beans.property.BooleanProperty;

import java.util.ArrayList;

public class Box {
    private double myTop, myLeft;
    private static double width = 100;
    private static double height = 100;

    public Box(double x, double y) {
        myLeft = x;
        myTop = y;
    }

    public double getMyTop() {
        return myTop;
    }

    public double getMyLeft() {
        return myLeft;
    }

    public double getWidth() {
        return width;
    }

    public void setMyTop( double myTop ) {
        this.myTop = myTop;
    }

    public void setMyLeft( double myLeft ) {
        this.myLeft = myLeft;
    }

    public double getHeight() {
        return height;
    }

    public double distanceTo(double mx, double my) {
        return Math.hypot( myLeft - mx, myTop - my );
    }
}
