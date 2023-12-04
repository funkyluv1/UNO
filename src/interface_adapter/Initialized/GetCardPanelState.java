package interface_adapter.Initialized;

import entities.card.NumberCard;

import static use_case.initiation.InitiationInteractor.game;

public class GetCardPanelState {
    private boolean undoEnabled = false;
    private boolean getCardEnabled = true;

    private NumberCard topCard = game.getTopCard();

    private NumberCard numberCard = null;

    public GetCardPanelState(GetCardPanelState copy){
        this.undoEnabled = copy.undoEnabled;
        this.getCardEnabled = copy.getCardEnabled;
        this.topCard = copy.topCard;
    }

    public GetCardPanelState(){}

    public void setUndoEnabled(boolean undoEnabled) {
        this.undoEnabled = undoEnabled;
    }

    public boolean isUndoEnabled() {
        return undoEnabled;
    }

    public void setGetCardEnabled(boolean getCardEnabled) {
        this.getCardEnabled = getCardEnabled;
    }

    public boolean isGetCardEnabled() {
        return getCardEnabled;
    }

    public void setTopCard(NumberCard topCard) {
        this.topCard = topCard;
    }

    public NumberCard getTopCard() {
        return topCard;
    }

    public NumberCard getNumberCard() {return this.numberCard;}

    public void setNumberCard(NumberCard numberCard) {this.numberCard = numberCard;}
}
