package use_case.PostTurn;

import entities.card.FunctionalCard;
import entities.card.NumberCard;

import java.util.ArrayList;

public class PostTurnOutputData {
    final private ArrayList<FunctionalCard> functionalCards;
    final private ArrayList<NumberCard> numberCards;

    public PostTurnOutputData(ArrayList<NumberCard> numCards, ArrayList<FunctionalCard> funcCards) {
        this.numberCards = numCards;
        this.functionalCards = funcCards;
    }

    public ArrayList<NumberCard> getNumberCards() {
        return numberCards;
    }

    public ArrayList<FunctionalCard> getFunctionalCards() {
        return functionalCards;
    }
}
