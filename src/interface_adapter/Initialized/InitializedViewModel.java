package interface_adapter.Initialized;

import entities.Game;
import interface_adapter.ViewModel;
import interface_adapter.Initiation.InitiationState;
import view.*;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Objects;

public class InitializedViewModel extends ViewModel implements PropertyChangeListener{
    public final String TITLE_LABEL = "Initialized View";

    private ArrayList<JPanel> panels = new ArrayList<>();
    private JPanel cardButtonPanel;
    private JPanel playpanel;
    private JPanel bottomPanel;
    private InitializedState state = new InitializedState();

    private JPanel playerPanel;
    private JPanel getCardPanel;
    private JPanel funCardButtonPanel;

    public InitializedViewModel(CardButtonPanel cardButtonPanel, BottomPanel bottomPanel, PlayerPanel playerPanel,
                                GetCardPanel getCardPanel, FunCardButtonPanel funCardButtonPanel) {

        super("Initialized");
        panels.add(playerPanel);
        panels.add(getCardPanel);
        panels.add(cardButtonPanel);
        panels.add(funCardButtonPanel);
        panels.add(bottomPanel);


        this.cardButtonPanel = cardButtonPanel;
        this.cardButtonPanel.addPropertyChangeListener(this);
        this.bottomPanel = bottomPanel;
        this.bottomPanel.addPropertyChangeListener(this);
        this.playerPanel = playerPanel;
        this.playerPanel.addPropertyChangeListener(this);
        this.getCardPanel = getCardPanel;
        this.getCardPanel.addPropertyChangeListener(this);
        this.funCardButtonPanel = funCardButtonPanel;
        this.funCardButtonPanel.addPropertyChangeListener(this);
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
        Panel panel = (Panel) evt.getNewValue();
        if (panel.getId() == 1){
            panels.remove(this.playerPanel);
            panels.add((JPanel) panel);
            this.playerPanel = (JPanel) panel;
        } else if (panel.getId() == 2){
            panels.remove(this.getCardPanel);
            panels.add((JPanel) panel);
            this.getCardPanel = (JPanel) panel;
        }else if (panel.getId() == 3){
            panels.remove(this.cardButtonPanel);
            panels.add((JPanel) panel);
            this.cardButtonPanel = (JPanel) panel;
        }else if (panel.getId() == 4){
            panels.remove(this.funCardButtonPanel);
            panels.add((JPanel) panel);
            this.funCardButtonPanel = (JPanel) panel;
        } else {
            panels.remove(this.bottomPanel);
            panels.add((JPanel) panel);
            this.bottomPanel = (JPanel) panel;
        }
        this.firePropertyChanged();
    }
}
