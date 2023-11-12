package interface_adapter.preTurn;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PreTurnViewModel extends ViewModel {
    private PreTurnState state = new PreTurnState();

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public PreTurnViewModel() {super("preTurn");}

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public PreTurnState getState() {
        return state;
    }
}
