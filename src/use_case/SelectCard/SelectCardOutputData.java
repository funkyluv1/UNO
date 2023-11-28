package use_case.SelectCard;

import entities.card.NumberCard;

public class SelectCardOutputData {
    private final Object selectedCard;

    public SelectCardOutputData (Object selectedCard) {
        this.selectedCard = selectedCard;
    }

    public NumberCard getSelectedCard() {
        return (NumberCard) selectedCard;
    }
}