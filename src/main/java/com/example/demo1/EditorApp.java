package com.example.demo1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class EditorApp extends Application {
    public static final double appWidth = 800;
    public static final double appHeight = 800;

    @Override
    public void start( Stage stage ) {
        HBox root = new HBox();

        Scene scene = new Scene( root , appWidth, appHeight);
        stage.setTitle( "Hello!" );
        stage.resizableProperty().setValue( false );
        stage.setScene( scene );
        stage.show();
    }

    public static void main( String[] args ) {
        launch();
    }
}

