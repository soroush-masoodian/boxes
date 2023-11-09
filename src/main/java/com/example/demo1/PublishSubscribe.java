package com.example.demo1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class PublishSubscribe {
    ArrayList<StatusBarSubscriber> subscribers;
    int created, deleted, current;

    public PublishSubscribe() {
        created = 0;
        deleted = 0;
        current = 0;
        subscribers = new ArrayList<>();
    }

    public void addSubscriber(StatusBarSubscriber sub) {
        subscribers.add( sub );
    }

    public void addToCreated(int numCurrentBoxes) {
        created += 1;
        current += 1;
        notifySubscribers(  numCurrentBoxes );
    }

    public void addToDeleted(int numberDeleted, int numCurrentBoxes) {
        deleted += numberDeleted;
        current -= numberDeleted;
        notifySubscribers( numCurrentBoxes );
    }

    public void notifySubscribers(int numBoxes) {
        for (StatusBarSubscriber sub : subscribers) {
            sub.modelChanged( created, deleted, numBoxes );
        }
    }
}
