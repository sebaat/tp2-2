/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.emp.gl.tp2;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Horloge1Hz {

    public Horloge1Hz() {
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                oneSecondElapsed();
            }

        }, 0, 1000);
    }

    protected abstract void oneSecondElapsed();

    public static String getCurrentTime() {
        return LocalTime.now().format(DateTimeFormatter.ISO_TIME);
    }
}
