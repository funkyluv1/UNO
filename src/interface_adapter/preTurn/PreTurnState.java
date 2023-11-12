package interface_adapter.preTurn;

import entities.card.FunctionalCard;
import entities.card.NumberCard;
import interface_adapter.preTurn.PreTurnState;

import java.util.ArrayList;

public class PreTurnState {
    private ArrayList<NumberCard> numberCards;

    public PreTurnState(PreTurnState copy) {
        numberCards = copy.numberCards;
    }

    public PreTurnState(){}

    public ArrayList<NumberCard> getNumberCards() {
        return numberCards;
    }

    public void setNumberCards(ArrayList<NumberCard> numberCards) {
        this.numberCards = numberCards;
    }
}
