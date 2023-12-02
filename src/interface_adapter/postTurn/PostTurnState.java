package interface_adapter.postTurn;

import entities.card.FunctionalCard;
import entities.card.NumberCard;
import interface_adapter.Initiation.InitiationState;

import java.util.ArrayList;

public class PostTurnState {
    private ArrayList<NumberCard> numberCards;
    private ArrayList<FunctionalCard> functionalCards;

    public PostTurnState(PostTurnState copy) {
        numberCards = copy.numberCards;
        functionalCards = copy.functionalCards;
    }

    public PostTurnState(){}

    public ArrayList<FunctionalCard> getFunctionalCards() {
        return functionalCards;
    }

    public void setFunctionalCards(ArrayList<FunctionalCard> functionalCards) {
        this.functionalCards = functionalCards;
    }
    public void setNumberCards(ArrayList<NumberCard> numberCards) {
        this.numberCards = numberCards;
    }
}
