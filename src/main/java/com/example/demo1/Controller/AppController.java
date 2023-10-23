package com.example.demo1.Controller;

import com.example.demo1.Model.Box;
import com.example.demo1.Model.BoxModel;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;

/**
 *
 */
public class AppController {
    BoxModel model;
    KeyCombination controlC = new KeyCodeCombination( KeyCode.C, KeyCombination.CONTROL_ANY );

    public void setModel(BoxModel model) {
        this.model = model;
    }

    public void handleEvent( KeyEvent event ) {
        if (!event.equals( KeyEvent.KEY_PRESSED ))
            return;

        if (controlC.match( event )) {
            model.addBox(  );
        }

    }
}
