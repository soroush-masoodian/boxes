package com.example.demo1.View;

import com.example.demo1.Model.Box;
import com.example.demo1.StatusBarSubscriber;
import com.example.demo1.Subscriber;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import java.util.ArrayList;

public class StatusBarView extends HBox implements StatusBarSubscriber {
    Label createdLabel, deletedLabel, currentLabel;

    public StatusBarView() {
        createdLabel = new Label( "Created: " + 0 );
        deletedLabel = new Label( "Deleted: " + 0 );
        currentLabel = new Label( "Current: " + 0 );

        createdLabel.setStyle( "-fx-font-size: 15" );
        deletedLabel.setStyle( "-fx-font-size: 15" );
        currentLabel.setStyle( "-fx-font-size: 15" );

        this.setStyle( "-fx-spacing: 15; -fx-padding: 5" );
        this.getChildren().addAll( createdLabel, deletedLabel, currentLabel );
    }

    public void modelChanged( int created, int deleted, int current ) {
        createdLabel.setText( "Created: " + created );
        deletedLabel.setText( "Deleted: " + deleted );
        currentLabel.setText( "Current: " + current );
    }
}
