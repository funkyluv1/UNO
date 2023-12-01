package use_case.PreTurn;

import entities.NumberCardsDeck.NumberCardsDeck;
import entities.card.FunctionalCard;
import entities.card.NumberCard;

import java.util.ArrayList;

public class PreTurnInputData {
    final private int currentPlayerIndex;
    private int plusN = 0;
    public PreTurnInputData(int currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
    }
    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

}
