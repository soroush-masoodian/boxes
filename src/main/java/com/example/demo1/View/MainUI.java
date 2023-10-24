package com.example.demo1.View;

import com.example.demo1.Controller.AppController;
import com.example.demo1.InteractionModel.InteractionModel;
import com.example.demo1.Model.BoxModel;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.layout.StackPane;

/**
 *
 */
public class MainUI extends StackPane {

    public MainUI() {
        BoxModel model = new BoxModel();
        BoxView view = new BoxView();
        AppController controller = new AppController();
        InteractionModel iModel = new InteractionModel();

        view.setupEvents( controller );
        view.setIModel( iModel );
        controller.setModel( model );
        controller.setIModel( iModel );
        model.addSubscriber( view );
        iModel.setModel( model );

        this.onKeyPressedProperty().bind( view.onKeyPressedProperty() );
        this.getChildren().add( view );
    }
}
