package interface_adapter.SelectCard;

import interface_adapter.SelectCard.SelectCardState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SelectCardViewModel {
    private SelectCardState state;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }


    public SelectCardState getSelectCardState() {
        return state;
    }

    public void setSelectCardState(SelectCardState selectCardState) {
        this.state = selectCardState;
    }
}
