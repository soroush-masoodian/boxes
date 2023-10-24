package com.example.demo1.View;

import com.example.demo1.Controller.AppController;
import com.example.demo1.Model.Box;
import com.example.demo1.Subscriber;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;

public class BoxView extends StackPane implements Subscriber {
    final double CANVAS_WIDTH = 800;
    final double CANVAS_HEIGHT = 800;
    Canvas canvas;
    GraphicsContext graphicsContext;

    public BoxView() {
        canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        graphicsContext = canvas.getGraphicsContext2D();

        this.getChildren().add( canvas );
        this.setStyle( "-fx-background-color: #022305FF" );
    }

    public void setupEvents( AppController controller ) {
        this.setOnKeyPressed( event -> controller.handleEvent( event ));
    }

    public void draw( ArrayList<Box> boxes ) {
        graphicsContext.clearRect( 0, 0, canvas.getWidth(), canvas.getHeight() );
        boxes.forEach( box -> {
            graphicsContext.setFill( Paint.valueOf( "#32a8a6" ) );
            graphicsContext.fillRect( box.getMyLeft(), box.getMyTop(), box.getWidth(), box.getHeight() );

            graphicsContext.setFill( Paint.valueOf( "#000000" ) );
            graphicsContext.strokeRect( box.getMyLeft(), box.getMyTop(), box.getWidth(), box.getHeight() );
        }
        );
    }

    public void modelChanged( ArrayList<Box> boxes ) {
        draw( boxes );
    }
}
