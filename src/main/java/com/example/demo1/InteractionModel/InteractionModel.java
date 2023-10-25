package com.example.demo1.InteractionModel;

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
    boolean selectionConfirmed;
    BoxModel model;

    public InteractionModel() {
        cursorPos = new SimpleIntegerProperty( -1 );
        selectionConfirmed = false;
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

    public boolean getSelectionConfirmed() {
        return selectionConfirmed;
    }

    public void cancelSelection() {
        selectionConfirmed = false;
        model.notifySubscribers();
    }

    public void confirmSelection() {
        selectionConfirmed = true;
        model.notifySubscribers();
    }
}
