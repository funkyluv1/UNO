package use_case.PreTurn;

import entities.NumberCardsDeck.NumberCardsDeck;
import entities.card.FunctionalCard;
import entities.card.NumberCard;

import java.util.ArrayList;

public class PreTurnInputData {
    final private int currentPlayerIndex;
    final private NumberCardsDeck numberCardsDeck;
    private int plusN = 0;
//TODO: probably need playerIndex
    public PreTurnInputData(NumberCardsDeck numberCardsDeck, int currentPlayerIndex) {
        this.numberCardsDeck = numberCardsDeck;
        this.currentPlayerIndex = currentPlayerIndex;
    }

    public NumberCardsDeck getNumberCardsDeck() {
        return numberCardsDeck;
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

}
