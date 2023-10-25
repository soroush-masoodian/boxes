package com.example.demo1.InteractionModel;

import com.example.demo1.Controller.AppController;
import com.example.demo1.Model.Box;
import com.example.demo1.Model.BoxModel;
import com.example.demo1.Subscriber;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 *
 */
public class InteractionModel {
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
        model.notifySubscribers();
    }
}
