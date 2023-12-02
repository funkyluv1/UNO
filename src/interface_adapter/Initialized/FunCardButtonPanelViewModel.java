package interface_adapter.Initialized;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class FunCardButtonPanelViewModel extends ViewModel {
    public final String TITLE_LABEL = "FunCardButtonPanel View";

    private FunCardButtonPanelState state = new FunCardButtonPanelState();

    public FunCardButtonPanelViewModel() {
        super("FunCardButtonPanel");
    }

    public void setState(FunCardButtonPanelState state) {
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

    public FunCardButtonPanelState getState() {
        return state;
    }

}
