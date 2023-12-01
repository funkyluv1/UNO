package interface_adapter.Initialized;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PlayerPanelViewModel extends ViewModel {
    public final String TITLE_LABEL = "PlayerPanelView";

    private PlayerPanelState state = new PlayerPanelState();
    public PlayerPanelViewModel() {
        super("PlayerPanel");
    }

    public void setState(PlayerPanelState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public PlayerPanelState getState() {
        return state;
    }

}
