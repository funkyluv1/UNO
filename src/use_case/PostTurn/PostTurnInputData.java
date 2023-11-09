package use_case.PostTurn;

import entities.NumberCardsDeck.NumberCardsDeck;
import entities.card.FunctionalCard;
import entities.card.NumberCard;

import java.util.ArrayList;

public class PostTurnInputData {
    final private ArrayList<FunctionalCard> functionalCards;
    final private ArrayList<NumberCard> numberCards;
    final private NumberCardsDeck numberCardsDeck;

    public PostTurnInputData(ArrayList<FunctionalCard> functionalCards, ArrayList<NumberCard> numberCards, NumberCardsDeck numberCardsDeck) {
        this.functionalCards = functionalCards;
        this.numberCardsDeck = numberCardsDeck;
        this.numberCards = numberCards;
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
}
