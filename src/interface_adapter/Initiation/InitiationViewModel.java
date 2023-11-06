package interface_adapter.Initiation;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
public class InitiationViewModel extends ViewModel {
    // this view model displays 4 text fields, each for a player name
    public static final String TITLE_LABEL = "Initiation View";
    public static final String INITIATION_BUTTON_LABEL = "Initialize";

    private InitiationState state = new InitiationState();

    public InitiationViewModel() {
        super("initiation");
    }

    public void setState(InitiationState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the Initiation Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public InitiationState getState() {
        return state;
    }
}
