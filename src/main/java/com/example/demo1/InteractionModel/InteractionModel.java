package com.example.demo1.InteractionModel;

import com.example.demo1.Controller.AppController;
import com.example.demo1.Model.Box;
import com.example.demo1.Model.BoxModel;
import com.example.demo1.Subscriber;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import java.security.Key;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 *
 */
public class InteractionModel {
    final int MOVEMENT_SPEED = 15;
    final int INCREASE_DIMS_BY = 5;
    IntegerProperty cursorPos;
    BoxModel model;
    ArrayList<Box> selectedBoxes;

    public InteractionModel() {
        cursorPos = new SimpleIntegerProperty( -1 );
        selectedBoxes = new ArrayList<>();
    }

    public void setModel( BoxModel model ) {
        this.model = model;
    }

    public void moveCursor() {
        if (model.getBoxes().size() == 0) {
            cursorPos.setValue( -1 );
            return;
        }
        else if ( cursorPos.getValue() == -1 ) {
            cursorPos.setValue( 0 );
        }
        else if ( cursorPos.getValue() == model.getBoxes().size() - 1) {
                cursorPos.setValue( 0 );
            }
        else {
            cursorPos.setValue( cursorPos.getValue() + 1 );
        }
        model.notifySubscribers();
    }

    public int getCursorPos() { return cursorPos.getValue(); }

    public void setCursorPos(int pos) {
        cursorPos.setValue( pos );
        model.notifySubscribers();
    }

    public void moveCursorHorizontally( KeyCode code ) {
        try {
            Box currentBox = model.getBoxes().get( getCursorPos() );
            ArrayList<Box> sortedBoxes = model.sortBoxesByLeftValue();
            int adjacentBoxIdx;
            if ( code == KeyCode.LEFT ) {
                adjacentBoxIdx = sortedBoxes.indexOf( currentBox ) - 1;
                Box adjacentBox = sortedBoxes.get( adjacentBoxIdx );
                setCursorPos( model.getBoxes().indexOf( adjacentBox ) );
            }
            else if ( code == KeyCode.RIGHT ) {
                adjacentBoxIdx = sortedBoxes.indexOf( currentBox ) + 1;
                Box adjacentBox = sortedBoxes.get( adjacentBoxIdx );
                setCursorPos( model.getBoxes().indexOf( adjacentBox ) );
            }
        } catch ( Exception e ) { }
    }

    public void moveCursorVertically( KeyCode code ) {
        try {
            Box currentBox = model.getBoxes().get( getCursorPos() );
            ArrayList<Box> sortedBoxes = model.sortBoxesByTopValue();
            int adjacentBoxIdx;
            if ( code == KeyCode.UP ) {
                adjacentBoxIdx = sortedBoxes.indexOf( currentBox ) - 1;
                Box adjacentBox = sortedBoxes.get( adjacentBoxIdx );
                setCursorPos( model.getBoxes().indexOf( adjacentBox ) );
            }
            else if ( code == KeyCode.DOWN ) {
                adjacentBoxIdx = sortedBoxes.indexOf( currentBox ) + 1;
                Box adjacentBox = sortedBoxes.get( adjacentBoxIdx );
                setCursorPos( model.getBoxes().indexOf( adjacentBox ) );
            }
        } catch ( Exception e ) { }
    }

    public ArrayList<Box> getSelectedBoxes() {
        return selectedBoxes;
    }

    public void addToSelectedBoxes(Box box) {
        selectedBoxes.add( box );
        model.notifySubscribers();
    }

    public void removeFromSelectedBoxes(Box box) {
        selectedBoxes.remove( box );
        model.notifySubscribers();
    }

    public void selectAllBoxes() {
        for ( Box box : model.getBoxes() ) {
            addToSelectedBoxes( box );
        }
        model.notifySubscribers();
    }

    public void removeAllSelectedBoxes() {
        selectedBoxes.clear();
        cursorPos.setValue( -1 );
        model.notifySubscribers();
    }

    public void moveSelectedBoxesUp() {
        for ( Box box : selectedBoxes ) {
            box.setMyTop( box.getMyTop() - MOVEMENT_SPEED );
        }
        model.notifySubscribers();
    }

    public void moveSelectedBoxesDown() {
        for ( Box box : selectedBoxes ) {
            box.setMyTop( box.getMyTop() + MOVEMENT_SPEED );
        }
        model.notifySubscribers();
    }

    public void moveSelectedBoxesLeft() {
        for ( Box box : selectedBoxes ) {
            box.setMyLeft( box.getMyLeft() - MOVEMENT_SPEED );
        }
        model.notifySubscribers();
    }

    public void moveSelectedBoxesRight() {
        for ( Box box : selectedBoxes ) {
            box.setMyLeft( box.getMyLeft() + MOVEMENT_SPEED );
        }
        model.notifySubscribers();
    }

    public void increaseSelectedBoxesDims() {
        for ( Box box : selectedBoxes ) {
            box.setWidth( box.getWidth() + INCREASE_DIMS_BY );
            box.setHeight( box.getHeight() + INCREASE_DIMS_BY );
        }
        model.notifySubscribers();
    }

    public void decreaseSelectedBoxesDims() {
        for ( Box box : selectedBoxes ) {
            if (box.getWidth() - INCREASE_DIMS_BY > 0 && box.getHeight() - INCREASE_DIMS_BY > 0) {
                box.setWidth( box.getWidth() - INCREASE_DIMS_BY );
                box.setHeight( box.getHeight() - INCREASE_DIMS_BY );
            }
        }
        model.notifySubscribers();
    }

    public void leftAlignSelectedBoxes() {
        Box box;
        if (cursorPos.getValue() > -1) {
            box = model.getBoxes().get(cursorPos.getValue());
        }
        else {
            box = selectedBoxes.get( 0 );
        }
        selectedBoxes.forEach( sBox -> sBox.setMyLeft( box.getMyLeft() ) );
        model.notifySubscribers();
    }

    public void topAlignSelectedBoxes() {
        Box box;
        if (cursorPos.getValue() > -1) {
            box = model.getBoxes().get(cursorPos.getValue());
        }
        else {
            box = selectedBoxes.get( 0 );
        }
        selectedBoxes.forEach( sBox -> sBox.setMyTop( box.getMyTop() ) );
        model.notifySubscribers();
    }

    public void rightAlignSelectedBoxes() {
        Box box;
        if (cursorPos.getValue() > -1) {
            box = model.getBoxes().get(cursorPos.getValue());
        }
        else {
            box = selectedBoxes.get( 0 );
        }
        selectedBoxes.forEach( sBox -> {
            double newLeft = box.getMyLeft() + box.getWidth() - sBox.getWidth();
            sBox.setMyLeft( newLeft );
        });
        model.notifySubscribers();
    }

    public void bottomAlignSelectedBoxes() {
        Box box;
        if (cursorPos.getValue() > -1) {
            box = model.getBoxes().get(cursorPos.getValue());
        }
        else {
            box = selectedBoxes.get( 0 );
        }
        selectedBoxes.forEach( sBox -> {
            double newTop = box.getMyTop() + box.getHeight() - sBox.getHeight();
            sBox.setMyTop( newTop );
        });
        model.notifySubscribers();
    }
}
