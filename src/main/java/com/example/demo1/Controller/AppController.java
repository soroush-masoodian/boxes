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

    public void setModel(BoxModel model) {
        this.model = model;
    }

    public void setIModel(InteractionModel interactionModel) {
        iModel = interactionModel;
    }

    public void handleEvent( KeyEvent event ) {
        if (event.getCode().equals( KeyCode.C ) && event.isControlDown()) {
            model.addBox();
        }
        else if (event.getCode().equals( KeyCode.S ) && event.isControlDown()) {
            if (iModel.getSelectionConfirmed()) {
                iModel.cancelSelection();
            }
            else {
                iModel.confirmSelection();
            }
            System.out.println(iModel.getSelectionConfirmed());
        }
        else if (event.getCode().equals( KeyCode.TAB )) {
            iModel.moveCursor();
        }
    }
}
