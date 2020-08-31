package edu.emp.gl.tp2;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.Calendar;

public class Controller {

    public Label timeLabel;
    public HBox settingHbox;

    private final Horloge1HzObservable horloge1HzObservable = Horloge1HzObservable.getInstance();
    Calendar currentTime = Calendar.getInstance();
    boolean isFirstStartClick = true;
    private OnTimeListener listener = time -> {
        currentTime.add(Calendar.SECOND, 1);
        showTime();
    };

    public void startButton(ActionEvent actionEvent) {

        if (isFirstStartClick) {
            settingHbox.setDisable(false);
            String time = Horloge1Hz.getCurrentTime();
            int hour   = Integer.parseInt(time.substring(0, 2));
            int minute = Integer.parseInt(time.substring(3, 5));
            int second = Integer.parseInt(time.substring(6, 8));
            currentTime.set(0, 0, 0, hour, minute, second);
            isFirstStartClick = false;

        }
        horloge1HzObservable.subscribe(listener);
        showTime();
    }

    public void changeTime(ActionEvent actionEvent) {

        Button button = (Button) actionEvent.getSource();
        switch (button.getId()) {
            case "plus_hour":
                currentTime.add(Calendar.HOUR, 1);
                break;
            case "minus_hour":
                currentTime.add(Calendar.HOUR, -1);
                break;
            case "plus_minute":
                currentTime.add(Calendar.MINUTE, 1);
                break;
            case "minus_minute":
                currentTime.add(Calendar.MINUTE, -1);
                break;
        }
        showTime();
    }

    private void showTime() {

        String displayedTime = String.valueOf(currentTime.getTime()).substring(11, 19);
        Platform.runLater(() -> timeLabel.setText(displayedTime));
    }

    public void pauseButton(ActionEvent actionEvent) {
        horloge1HzObservable.remove(listener);
    }
}
