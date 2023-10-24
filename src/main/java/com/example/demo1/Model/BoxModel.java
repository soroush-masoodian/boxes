package com.example.demo1.Model;

import com.example.demo1.Subscriber;

import java.util.ArrayList;

public class BoxModel {
    final double firstBoxPosX = 100;
    final double firstBoxPosY = 100;
    final double addToLastBoxDims = 50;
    ArrayList<Box> boxes;
    ArrayList<Subscriber> subscribers;

    public BoxModel() {
        boxes = new ArrayList<>();
        subscribers = new ArrayList<>();
    }

    public ArrayList<Box> getBoxes() {
        return boxes;
    }

    public void addBox() {
        if (boxes.size() > 0) {
            Box lastBox = boxes.get( boxes.size() - 1 );
            Box newBox = new Box( lastBox.getMyLeft() + addToLastBoxDims, lastBox.getMyTop() + addToLastBoxDims );
            boxes.add( newBox );
        }
        else {
            boxes.add( new Box( firstBoxPosX, firstBoxPosY ) );
        }
        notifySubscribers();
    }

    public void addSubscriber( Subscriber subscriber ) {
        this.subscribers.add( subscriber );
        notifySubscribers();
    }

    public void notifySubscribers() {
        this.subscribers.forEach( subscriber -> subscriber.modelChanged( this.boxes ) );
    }
}
