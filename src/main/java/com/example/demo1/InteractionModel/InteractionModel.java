package com.example.demo1.InteractionModel;

import com.example.demo1.Model.Box;
import com.example.demo1.Model.BoxModel;
import com.example.demo1.Subscriber;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 *
 */
public class InteractionModel {
    final static Color CURSOR_COLOR = Color.ORANGE;
    IntegerProperty cursorPos;
    BoxModel model;

    public InteractionModel() {
        cursorPos.setValue( null );
    }

    public void setModel( BoxModel model ) {
        this.model = model;
    }

    public void moveCursor() {
        if ( cursorPos.getValue() == null ) {
            cursorPos.setValue( 0 );
        }
        else {
            if ( cursorPos.getValue() == model.getBoxes().size() - 1 ) {
                cursorPos.setValue( 0 );
            }
            else {
                cursorPos.setValue( cursorPos.getValue() + 1 );
            }
        }
        model.notifySubscribers();
    }
}
