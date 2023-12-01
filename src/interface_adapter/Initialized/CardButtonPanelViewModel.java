package interface_adapter.Initialized;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


import entities.Game;
import interface_adapter.ViewModel;
import interface_adapter.Initiation.InitiationState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

    public class CardButtonPanelViewModel extends ViewModel {
        public final String TITLE_LABEL = "CardButtonPanel View";

        private CardButtonPanelState state = new CardButtonPanelState();

        public CardButtonPanelViewModel() {
            super("CardButtonPanel");
        }

        public void setState(CardButtonPanelState state) {
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

        public CardButtonPanelState getState() {
            return state;
        }

    }

