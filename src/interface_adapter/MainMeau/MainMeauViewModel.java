package interface_adapter.MainMeau;

import interface_adapter.MainMeau.MainMeauState;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class MainMeauViewModel extends ViewModel {
    public static final String TITLE_LABEL = "MainMeau View";
    public static final String MAINMEAU_BUTTON_LABEL = "MainMeau";

    private MainMeauState state = new MainMeauState();

    public MainMeauViewModel() {
        super("MainMeau");
    }

    public void setState(MainMeauState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the MainMeau Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public MainMeauState getState() {
        return state;
    }
}
