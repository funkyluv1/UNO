package interface_adapter.postTurn;

import interface_adapter.Initiation.InitiationViewModel;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PostTurnViewModel extends ViewModel {
    private PostTurnState state = new PostTurnState();

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public PostTurnViewModel() {super("postTurn");}

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public PostTurnState getState() {
        return state;
    }

    public void setState(PostTurnState state) {
        this.state = state;
    }
}
