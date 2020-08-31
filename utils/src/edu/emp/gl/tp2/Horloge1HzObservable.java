package edu.emp.gl.tp2;

import java.util.HashSet;
import java.util.Set;

public class Horloge1HzObservable extends Horloge1Hz {

    private Set<OnTimeListener> listeners = new HashSet<>();

    @Override
    protected void oneSecondElapsed() {
        notifyListener();
    }

    public void subscribe(OnTimeListener listener) {
        listeners.add(listener);
    }

    public void remove(OnTimeListener listener) {
        listeners.remove(listener);
    }

    void notifyListener() {
        for (OnTimeListener listener : listeners) {
            new Thread(() -> {
                listener.on1HzElapsed(getCurrentTime());
            }).start();
        }
    }

    private Horloge1HzObservable() {
    }

    public static Horloge1HzObservable getInstance() {
        return Horloge1HzObservable.horloge1HzObservableHolder.INSTANCE;
    }

    private static class horloge1HzObservableHolder {
        private static final Horloge1HzObservable INSTANCE = new Horloge1HzObservable();
    }

}
