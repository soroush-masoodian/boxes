package com.example.demo1.Controller;

import com.example.demo1.InteractionModel.InteractionModel;
import com.example.demo1.Model.Box;
import com.example.demo1.Model.BoxModel;
import javafx.scene.input.*;

import java.security.Key;

/**
 *
 */
public class AppController {
    BoxModel model;
    InteractionModel iModel;
    KeyCombination controlC = new KeyCodeCombination( KeyCode.C, KeyCombination.CONTROL_ANY );

    public void setModel(BoxModel model) {
        this.model = model;
    }

    public void setIModel(InteractionModel interactionModel) {
        iModel = interactionModel;
    }

    public void handleEvent( KeyEvent event ) {

        if (controlC.match( event )) {
            model.addBox();
        }
        if (event.getCode().equals( KeyCode.TAB )) {
            iModel.moveCursor();
        }

    }
}
