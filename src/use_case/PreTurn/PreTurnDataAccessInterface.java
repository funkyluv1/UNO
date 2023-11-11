package use_case.PreTurn;

import entities.card.FunctionalCard;
import entities.card.NumberCard;

import java.util.ArrayList;

public interface PreTurnDataAccessInterface {
    ArrayList<NumberCard> getNumberCards(String player);
    void recordPreTurnChange(ArrayList<NumberCard> numberCards, String currentPlayer);
    void recordSkip();
}
