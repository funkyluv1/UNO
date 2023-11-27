package interface_adapter.DrawCards;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class DrawCardsViewModel {
    private DrawCardsState state;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }


    public DrawCardsState getDrawCardsState() {
        return state;
    }

    public void setDrawCardsState(DrawCardsState drawCardsState) {
        this.state = drawCardsState;
    }
}
