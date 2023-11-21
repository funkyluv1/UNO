package interface_adapter.inTurn;

import entities.card.NumberCard;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class InTurnState {

    private ArrayList<NumberCard> numberCards;

    public InTurnState(InTurnState copy) {
        this.numberCards = copy.numberCards;
    }

    public InTurnState(){}

    public ArrayList<NumberCard> getNumberCards() {
        return numberCards;
    }

    public void setNumberCards(ArrayList<NumberCard> numberCards) {
        this.numberCards = numberCards;
    }

}
