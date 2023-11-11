package use_case.PreTurn;

import entities.card.FunctionalCard;
import entities.card.NumberCard;

import java.util.ArrayList;

public class PreTurnOutputData {

    final private ArrayList<NumberCard> numberCards;
    final private String currentPlayer; //TODO: decide if we need to return the current player

    public PreTurnOutputData(ArrayList<NumberCard> numCards, String currentPlayer) {
        this.numberCards = numCards;
        this.currentPlayer = currentPlayer;
    }

    public ArrayList<NumberCard> getNumberCards() {
        return numberCards;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }
}
