package edu.emp.gl.tp2;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashSet;
import java.util.Set;

public class Horloge1HzObservablePCSupport extends Horloge1Hz {



    private PropertyChangeSupport support;

    @Override
    protected void oneSecondElapsed() {
        if (support != null) {

            support.firePropertyChange("Time", "", getCurrentTime());

        }
    }

    public void subscribe(PropertyChangeListener pcl) {
        support.addPropertyChangeListener("Time",pcl);
    }

    public void remove(PropertyChangeListener pcl) {
        support.removePropertyChangeListener("Time",pcl);
    }

    private Horloge1HzObservablePCSupport() {
        support = new PropertyChangeSupport(this);
    }

    public static Horloge1HzObservablePCSupport getInstance() {
        return Horloge1HzObservablePCSupport.horloge1HzObservablePCSupportHolder.INSTANCE;
    }

    private static class horloge1HzObservablePCSupportHolder {
        private static final Horloge1HzObservablePCSupport INSTANCE = new Horloge1HzObservablePCSupport();
    }

}
