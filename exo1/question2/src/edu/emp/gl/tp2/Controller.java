package edu.emp.gl.tp2;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;

public class Controller {

    public Label timeLabel;

    public void startButton(ActionEvent actionEvent) {

        showTime();
        Horloge1HzObservable.getInstance().subscribe(time -> {
            Platform.runLater(this::showTime);
        });
    }

    private void showTime() {
        String timeString = Horloge1Hz.getCurrentTime();
        int index = timeString.lastIndexOf(".");
        timeLabel.setText(timeString.substring(0, index));
    }
}
