package interface_adapter.Initialized;

import entities.Game;
import interface_adapter.ViewModel;
import interface_adapter.Initiation.InitiationState;
import view.CardButtonPanel;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class InitializedViewModel extends ViewModel implements PropertyChangeListener{
    public final String TITLE_LABEL = "Initialized View";
    private CardButtonPanel cardButtonPanel;
    private JPanel playpanel;
    private InitializedState state = new InitializedState();

    public InitializedViewModel(CardButtonPanel cardButtonPanel) {

        super("Initialized");
        this.cardButtonPanel = cardButtonPanel;
        this.cardButtonPanel.addPropertyChangeListener(this);
        this.cardButtonPanel.addPropertyChangeListener(this);
    }

    public void setState(InitializedState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the Initialize Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("playpanel", null, this.playpanel);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public InitializedState getState() {
        return state;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.playpanel = (JPanel) evt.getNewValue();
        this.firePropertyChanged();
    }
}
