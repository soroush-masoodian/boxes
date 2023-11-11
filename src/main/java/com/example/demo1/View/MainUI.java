package com.example.demo1.View;

import com.example.demo1.Controller.AppController;
import com.example.demo1.InteractionModel.InteractionModel;
import com.example.demo1.Model.BoxModel;
import com.example.demo1.PublishSubscribe;
import javafx.geometry.Pos;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MainUI extends VBox {
    public MainUI() {
        BoxModel model = new BoxModel();
        BoxView view = new BoxView();
        AppController controller = new AppController();
        InteractionModel iModel = new InteractionModel();
        StatusBarView statusBarView = new StatusBarView();
        PublishSubscribe pubSub = new PublishSubscribe();

        view.setupEvents( controller );
        view.setIModel( iModel );

        pubSub.addSubscriber( statusBarView );

        controller.setModel( model );
        controller.setIModel( iModel );
        controller.setPublishSubscribe( pubSub );

        model.addSubscriber( view );

        iModel.setModel( model );

        this.getChildren().add( view );
        this.getChildren().add( statusBarView );

        this.onKeyPressedProperty().bind( view.onKeyPressedProperty() );
    }
}
