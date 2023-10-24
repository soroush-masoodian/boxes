package com.example.demo1.Model;


public class Box {
    private double myTop, myLeft;
    private static double width = 100;
    private static double height = 100;
    private

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

    public double getHeight() {
        return height;
    }
}
