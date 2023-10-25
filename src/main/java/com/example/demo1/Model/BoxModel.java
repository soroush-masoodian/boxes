package com.example.demo1.Model;

import com.example.demo1.Subscriber;

import java.util.ArrayList;

public class BoxModel {
    final double FIRST_BOX_POS_X = 100;
    final double FIRST_BOX_POS_Y = 100;
    final double ADD_TO_LAST_BOX_DIMS = 50;
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
            Box newBox = new Box( lastBox.getMyLeft() + ADD_TO_LAST_BOX_DIMS, lastBox.getMyTop() + ADD_TO_LAST_BOX_DIMS );
            boxes.add( newBox );
        }
        else {
            boxes.add( new Box( FIRST_BOX_POS_X, FIRST_BOX_POS_Y ) );
        }
        notifySubscribers();
    }

    public void deleteBox(Box box) {
        boxes.remove( box );
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
