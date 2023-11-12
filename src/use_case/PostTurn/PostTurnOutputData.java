package use_case.PostTurn;

import entities.card.FunctionalCard;
import entities.card.NumberCard;

import java.util.ArrayList;

public class PostTurnOutputData {
    final private ArrayList<FunctionalCard> functionalCards;
    final private ArrayList<NumberCard> numberCards;
    final private String currentPlayer; //TODO: decide if we need to return the current player

    public PostTurnOutputData(ArrayList<NumberCard> numCards, ArrayList<FunctionalCard> funcCards, String currentPlayer) {
        this.numberCards = numCards;
        this.functionalCards = funcCards;
        this.currentPlayer = currentPlayer;
    }

    public ArrayList<NumberCard> getNumberCards() {
        return numberCards;
    }

    public ArrayList<FunctionalCard> getFunctionalCards() {
        return functionalCards;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }
}
