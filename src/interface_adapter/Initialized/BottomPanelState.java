package interface_adapter.Initialized;

import entities.card.FunctionalCard;
import entities.card.NumberCard;

import java.util.ArrayList;
import java.util.Map;

public class BottomPanelState {
        private boolean confirmButtonEnabled = false;
        private boolean nextButtonEnabled = false;

        public BottomPanelState(BottomPanelState copy) {
            confirmButtonEnabled = copy.confirmButtonEnabled;
            nextButtonEnabled = copy.nextButtonEnabled;
        }

        // Because of the previous copy constructor, the default constructor must be explicit.
        public BottomPanelState() {}

        public void setConfirmButtonEnabled(boolean flag){this.confirmButtonEnabled = flag;}
        public boolean getConfirmButtonEnabled(){return this.confirmButtonEnabled;}

        public void setNextButtonEnabled(boolean flag){this.nextButtonEnabled = flag;}
        public boolean getNextButtonEnabled(){return this.nextButtonEnabled;}
}
