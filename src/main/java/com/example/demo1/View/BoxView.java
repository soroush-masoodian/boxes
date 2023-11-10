package com.example.demo1.View;

import com.example.demo1.Controller.AppController;
import com.example.demo1.InteractionModel.InteractionModel;
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
import java.util.ListIterator;

public class BoxView extends StackPane implements Subscriber {
    final String DEFAULT_SQUARE_COLOR = "#32a8a6";
    final String SELECTED_SQUARE_COLOR = "#fc461d";
    final String SQUARE_BORDER_COLOR = "#000000";
    final String SELECTED_SQUARE_BORDER_COLOR = "#ffff00";
    final double BORDER_WIDTH = 5;
    final double CANVAS_WIDTH = 800;
    final double CANVAS_HEIGHT = 800;
    Canvas canvas;
    GraphicsContext graphicsContext;
    InteractionModel iModel;

    public BoxView() {
        canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        graphicsContext = canvas.getGraphicsContext2D();

        this.getChildren().add( canvas );
        this.setStyle( "-fx-background-color: #022305" );
    }

    public void setIModel(InteractionModel newIModel) { iModel = newIModel; }

    public void setupEvents( AppController controller ) {
        this.setOnKeyPressed( event -> controller.handleEvent( event ));
        this.setOnKeyReleased( event -> controller.handleKeyReleased( event ) );
    }

    public void draw( ArrayList<Box> boxes ) {
        graphicsContext.clearRect( 0, 0, canvas.getWidth(), canvas.getHeight() );
        ListIterator<Box> boxesIterator = boxes.listIterator();
        while (boxesIterator.hasNext()) {
            Box box = boxesIterator.next();
            int currentIdx = boxesIterator.nextIndex() - 1;
            if ( currentIdx == iModel.getCursorPos()) {
                graphicsContext.setFill( Paint.valueOf( SELECTED_SQUARE_COLOR ) );
            }
            else {
                graphicsContext.setFill( Paint.valueOf( DEFAULT_SQUARE_COLOR ) );
            }
            graphicsContext.fillRect( box.getMyLeft(), box.getMyTop(), box.getWidth(), box.getHeight() );

            if ( iModel.getSelectedBoxes().contains( box ) ) {
                graphicsContext.setStroke( Paint.valueOf( SELECTED_SQUARE_BORDER_COLOR ) );
            }
            else {
                graphicsContext.setStroke( Paint.valueOf( SQUARE_BORDER_COLOR ) );
            }
            graphicsContext.setLineWidth( BORDER_WIDTH );
            graphicsContext.strokeRect( box.getMyLeft(), box.getMyTop(), box.getWidth(), box.getHeight() );
        }
    }

    public void modelChanged( ArrayList<Box> boxes ) {
        draw( boxes );
    }
}
