package com.example.demo1.Controller;

import com.example.demo1.InteractionModel.InteractionModel;
import com.example.demo1.Model.Box;
import com.example.demo1.Model.BoxModel;
import javafx.beans.property.IntegerProperty;
import javafx.scene.input.*;

import java.security.Key;

/**
 *
 */
public class AppController {
    BoxModel model;
    InteractionModel iModel;
    enum InteractionState { READY, SELECTED }
    InteractionState state;

    public void setModel(BoxModel model) {
        this.model = model;
        state = InteractionState.READY;
    }

    public void setIModel(InteractionModel interactionModel) {
        iModel = interactionModel;
    }

    public void handleControlSInput() {
        if ( -1 < iModel.getCursorPos() && iModel.getCursorPos() < model.getBoxes().size()) {
            if (!iModel.getSelectedBoxes().contains( model.getBoxes().get(iModel.getCursorPos())) )
                iModel.addToSelectedBoxes( model.getBoxes().get(iModel.getCursorPos()) );
            else
                iModel.removeFromSelectedBoxes( model.getBoxes().get(iModel.getCursorPos()) );
        }

        if (iModel.getSelectedBoxes().size() > 0) {
            state = InteractionState.SELECTED;
        }
        else {
            state = InteractionState.READY;
        }
    }

    public void handleControlAInput() {
        if (iModel.getSelectedBoxes().size() == model.getBoxes().size() && iModel.getSelectedBoxes().size() > 0) {
            iModel.removeAllSelectedBoxes();
        }
        else {
            iModel.removeAllSelectedBoxes();
            iModel.selectAllBoxes();
        }

        if (iModel.getSelectedBoxes().size() > 0) {
            state = InteractionState.SELECTED;
        }
        else {
            state = InteractionState.READY;
        }
    }

    public void readyStateEvents( KeyEvent event ) {
        if ( event.getCode().equals( KeyCode.C ) && event.isControlDown() ) {
            model.addBox();
        }
        else if ( event.getCode().equals( KeyCode.S ) && event.isControlDown() ) {
            handleControlSInput();
        }
        else if( event.getCode().equals( KeyCode.A ) && event.isControlDown()) {
            handleControlAInput();
        }
        else if ( event.getCode().equals( KeyCode.TAB ) ) {
            iModel.moveCursor();
        }
    }

    public void selectedStateEvents( KeyEvent event ) {
        if ( event.getCode().equals( KeyCode.S ) && event.isControlDown() ) {
            handleControlSInput();
        }
        else if( event.getCode().equals( KeyCode.A ) && event.isControlDown()) {
            handleControlAInput();
        }
        else if ( event.getCode().equals( KeyCode.TAB ) ) {
            iModel.moveCursor();
        }
    }

    public void handleEvent( KeyEvent event ) {
        if ( event.getCode().equals( KeyCode.SPACE ) ) {
            System.out.println(state);
        }

         switch ( state ) {
            case READY -> readyStateEvents( event );
            case SELECTED -> selectedStateEvents( event );
        }
    }
}
