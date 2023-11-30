package interface_adapter.Initialized;

import entities.Game;
import interface_adapter.ViewModel;
import interface_adapter.Initiation.InitiationState;
import view.BottomPanel;
import view.CardButtonPanel;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Objects;

public class InitializedViewModel extends ViewModel implements PropertyChangeListener{
    public final String TITLE_LABEL = "Initialized View";

    private ArrayList<JPanel> panels = new ArrayList<>();
    private CardButtonPanel cardButtonPanel;
    private JPanel playpanel;
    private BottomPanel bottomPanel;
    private InitializedState state = new InitializedState();

    public InitializedViewModel(CardButtonPanel cardButtonPanel, BottomPanel bottomPanel) {

        super("Initialized");
        panels.add(cardButtonPanel);
        panels.add(bottomPanel);
        this.cardButtonPanel = cardButtonPanel;
        this.cardButtonPanel.addPropertyChangeListener(this);
        this.bottomPanel = bottomPanel;
        this.bottomPanel.addPropertyChangeListener(this);
    }

    public void setState(InitializedState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the Initialize Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("panels", null, this.panels);
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
