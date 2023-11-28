package use_case.SelectCard;

import entities.card.Card;

public class SelectCardOutputData {
    private final Object selectedCard;
    private final int button_index;

    public SelectCardOutputData (Object selectedCard, int button_index) {
        this.selectedCard = selectedCard;
        this.button_index = button_index;

    }

    public Card getSelectedCard() {
        return (Card) selectedCard;
    }

    public int getButton_index(){
        return button_index;
    }
}