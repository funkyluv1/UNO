package use_case.PostTurn;

import entities.NumberCardsDeck.NumberCardsDeck;
import entities.card.FunctionalCard;
import entities.card.NumberCard;

import java.util.ArrayList;

public interface PostTurnDataAccessInterface {
    void recordPostTurnChange(ArrayList<FunctionalCard> functionalCards, ArrayList<NumberCard> numberCards, String currentPlayer);
}