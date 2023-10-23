package com.example.demo1;

import com.example.demo1.Model.Box;

import java.util.ArrayList;

public interface Subscriber {
    void modelChanged( ArrayList<Box> boxes );
}
