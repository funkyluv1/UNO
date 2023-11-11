package use_case.PreTurn;

import entities.card.FunctionalCard;
import entities.card.NumberCard;

import java.util.ArrayList;

public interface FindPlayableCardsInterface {
    public ArrayList<NumberCard> findPlayableNumberCards(String roundColor, ArrayList<NumberCard> numberCards);
    public ArrayList<FunctionalCard> findPlayableFunctionalCards(String roundColor, ArrayList<FunctionalCard> functionalCards);
}
