package com.example.demo1.Model;

import javafx.beans.property.BooleanProperty;

import java.util.ArrayList;

public class Box {
    private double myTop, myLeft;
    private double width;
    private double height;

    public Box(double x, double y) {
        myLeft = x;
        myTop = y;
        width = 100;
        height = 100;
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

    public void setWidth( double newWidth ) {
        width = newWidth;
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

    public void setHeight( double newHeight ) {
        height = newHeight;
    }
}
