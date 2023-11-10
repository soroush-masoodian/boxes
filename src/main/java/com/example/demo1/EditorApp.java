package com.example.demo1;

import com.example.demo1.View.MainUI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class EditorApp extends Application {

    @Override
    public void start( Stage stage ) {
        MainUI root = new MainUI();

        Scene scene = new Scene( root );
        scene.onKeyPressedProperty().bind( root.onKeyPressedProperty() );
        scene.onKeyReleasedProperty().bind( root.onKeyReleasedProperty() );

        stage.setTitle( "Assignment 3" );
        stage.resizableProperty().setValue( false );
        stage.setScene( scene );
        stage.show();
    }

    public static void main( String[] args ) {
        launch();
    }
}

