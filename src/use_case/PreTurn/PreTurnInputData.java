package use_case.PreTurn;

import entities.NumberCardsDeck.NumberCardsDeck;
import entities.card.FunctionalCard;
import entities.card.NumberCard;

import java.util.ArrayList;

public class PreTurnInputData {
    final private String currentPlayer;
    final private NumberCardsDeck numberCardsDeck;
    final private boolean isSkipped;
    private int plusN = 0;

    public PreTurnInputData(NumberCardsDeck numberCardsDeck, String currentPlayer, boolean isSkipped, int plusN) {
        this.numberCardsDeck = numberCardsDeck;
        this.currentPlayer = currentPlayer;
        this.isSkipped = isSkipped;
        this.plusN = plusN;
    }

    public NumberCardsDeck getNumberCardsDeck() {
        return numberCardsDeck;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public int getPlusN() {
        return plusN;
    }

    public boolean isSkipped() {
        return isSkipped;
    }
}
