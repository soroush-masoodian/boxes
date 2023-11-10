package com.example.demo1.Controller;

import com.example.demo1.InteractionModel.InteractionModel;
import com.example.demo1.Model.Box;
import com.example.demo1.Model.BoxModel;
import com.example.demo1.PublishSubscribe;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.input.*;

import java.security.Key;

public class AppController {
    BoxModel model;
    InteractionModel iModel;
    PublishSubscribe publishSubscribe;
    enum InteractionState { READY, SELECTED, GUIDE_VIEW }
    InteractionState state;
    SimpleIntegerProperty controlHoldTime = new SimpleIntegerProperty(0);

    public void setModel(BoxModel model) {
        this.model = model;
        state = InteractionState.READY;
    }

    public void setIModel(InteractionModel interactionModel) {
        iModel = interactionModel;
    }

    public void setPublishSubscribe(PublishSubscribe pubSub) {
        publishSubscribe = pubSub;
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
        if (iModel.getSelectedBoxes().size() > 0 && iModel.getSelectedBoxes().size() == model.getBoxes().size()) {
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

    public void handleControlDInput() {
        int deletedItems = iModel.getSelectedBoxes().size();
        iModel.getSelectedBoxes().forEach( box -> model.deleteBox( box ) );
        iModel.removeAllSelectedBoxes();
        iModel.setCursorPos( -1 );

        publishSubscribe.addToDeleted( deletedItems );

        state = InteractionState.READY;
    }

    public void handleControlDirectionInput( KeyEvent event ) {
        switch(event.getCode()) {
            case RIGHT -> iModel.moveSelectedBoxesRight();
            case LEFT -> iModel.moveSelectedBoxesLeft();
            case DOWN -> iModel.moveSelectedBoxesDown();
            case UP -> iModel.moveSelectedBoxesUp();
        }
    }

    public void handleCursorMovement( KeyEvent event ) {
        if ( event.getCode().equals( KeyCode.TAB ) ) {
            iModel.moveCursor();
        }
        else if ( event.getCode().equals( KeyCode.UP ) || event.getCode().equals( KeyCode.DOWN ) ) {
            iModel.moveCursorVertically( event.getCode() );
        }
        else if ( event.getCode().equals( KeyCode.RIGHT ) || event.getCode().equals( KeyCode.LEFT ) ) {
            iModel.moveCursorHorizontally( event.getCode() );
        }
    }

    public void countControlPressedTime( KeyEvent event ) {
        System.out.println(controlHoldTime.get());
        controlHoldTime.set( event.isControlDown() ? controlHoldTime.get() + 1 : 0 );
    }

    public void resetControlHoldTime( KeyEvent event ) {
        if (event.getCode().equals( KeyCode.CONTROL ))
            controlHoldTime.set( 0 );
    }

    public void handleKeyReleased( KeyEvent event ) {
        resetControlHoldTime( event );
    }

    public void readyStateEvents( KeyEvent event ) {
        if (event.isControlDown()) {
            countControlPressedTime( event );

            if ( event.getCode().equals( KeyCode.C ) ) {
                model.addBox();

                publishSubscribe.addToCreated();
            }
            else if ( event.getCode().equals( KeyCode.S ) ) {
                handleControlSInput();
            }
            else if ( event.getCode().equals( KeyCode.A ) ) {
                handleControlAInput();
            }
        }
        else {
            handleCursorMovement( event );
        }
    }

    public void selectedStateEvents( KeyEvent event ) {
        if (event.isControlDown()) {
            switch ( event.getCode() ) {
                case S -> handleControlSInput();
                case A -> handleControlAInput();
                case D -> handleControlDInput();
                case U -> iModel.increaseSelectedBoxesDims();
                case J -> iModel.decreaseSelectedBoxesDims();
                case L -> iModel.leftAlignSelectedBoxes();
                case T -> iModel.topAlignSelectedBoxes();
                case B -> iModel.bottomAlignSelectedBoxes();
                case R -> iModel.rightAlignSelectedBoxes();
                case H -> iModel.evenlyDistributeHorizontally();
                case V -> iModel.evenlyDistributeVertically();
                default -> handleControlDirectionInput( event );
            }
        }
        else {
            handleCursorMovement( event );
        }
    }

    public void handleEvent( KeyEvent event ) {
        if ( event.getCode().equals( KeyCode.SPACE ) ) {
            System.out.println(state);
        }

         switch ( state ) {
            case READY -> readyStateEvents( event );
            case SELECTED -> selectedStateEvents( event );
             case GUIDE_VIEW -> {}
        }
    }
}
