package edu.emp.gl.tp2;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.util.Duration;
import org.controlsfx.control.NotificationPos;
import org.controlsfx.control.NotificationsV2;

public class Notification implements OnTimeListener{

    private static final NotificationsV2 notification = NotificationsV2.create()
            .title("Time Elapsed")
            .text("time setting changed")
            .hideAfter(Duration.seconds(60))  //default duration
            .position(Pos.BOTTOM_RIGHT);

    private NotificationPos notificationPos;

    private int elapsedTime = 0;
    public int duration;
    private final Horloge1HzObservable horloge1HzObservable = Horloge1HzObservable.getInstance();

    public Notification(int duration) {
        this.duration = duration;
    }

    public void showNotification() {
        notificationPos = notification.show();
        horloge1HzObservable.subscribe(this);
    }

    private void hideNotification() {

        NotificationsV2.NotificationPopupHandler instance = NotificationsV2.NotificationPopupHandler.getInstance();
        Platform.runLater(() -> instance.hide(notificationPos.popup,notificationPos.pos ));
    }

    @Override
    public void on1HzElapsed(String time) {
        if (++elapsedTime >= 3) {
            hideNotification();
            horloge1HzObservable.remove(this);
        }
    }
}
