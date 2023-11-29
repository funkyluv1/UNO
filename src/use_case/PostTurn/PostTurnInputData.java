package use_case.PostTurn;

import entities.NumberCardsDeck.NumberCardsDeck;
import entities.card.FunctionalCard;
import entities.card.NumberCard;
import entities.player.Player;

import java.util.ArrayList;

public class PostTurnInputData {
    private final int currPlayerIndex;
    final private ArrayList<FunctionalCard> functionalCards;
    final private ArrayList<NumberCard> numberCards;
    final private String currentPlayer;
    final private NumberCardsDeck numberCardsDeck;

    public PostTurnInputData(int currPlayerIndex, ArrayList<FunctionalCard> functionalCards, ArrayList<NumberCard> numberCards, NumberCardsDeck numberCardsDeck, String currentPlayer) {
        this.currPlayerIndex = currPlayerIndex;
        this.functionalCards = functionalCards;
        this.numberCardsDeck = numberCardsDeck;
        this.numberCards = numberCards;
        this.currentPlayer = currentPlayer;
    }

    public ArrayList<FunctionalCard> getFuncCards() {
        return functionalCards;
    }

    public ArrayList<NumberCard> getNumberCards() {
        return numberCards;
    }

    public NumberCardsDeck getNumberCardsDeck() {
        return numberCardsDeck;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public int getCurrPlayerIndex() {
        return currPlayerIndex;
    }
}
