package use_case.SelectCard;

import entities.card.Card;

public class SelectCardOutputData {
    private final Object selectedCard;

    public SelectCardOutputData (Object selectedCard) {
        this.selectedCard = selectedCard;
    }

    public Object getSelectedCard() {
        return selectedCard;
    }
}
