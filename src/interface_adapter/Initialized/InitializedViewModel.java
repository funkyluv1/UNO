package interface_adapter.Initialized;

import entities.Game;
import interface_adapter.ViewModel;
import interface_adapter.Initiation.InitiationState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class InitializedViewModel extends ViewModel {
    public final String TITLE_LABEL = "Initialized View";

    private InitializedState state = new InitializedState();

    public InitializedViewModel() {
        super("Initialized");
    }

    public void setState(InitializedState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the Initialize Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public InitializedState getState() {
        return state;
    }

}
