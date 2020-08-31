package edu.emp.gl.tp2;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;


public class Controller {

    public Label timeLabel;

    public void startButton(ActionEvent actionEvent) {
        new Thread(() -> {
            while (true) {
                Platform.runLater(() -> {
                    String timeString = Horloge1Hz.getCurrentTime();
                    int index = timeString.lastIndexOf(".");
                    timeLabel.setText(timeString.substring(0,index+2));

                });
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
