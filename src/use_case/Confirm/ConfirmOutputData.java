package use_case.Confirm;

import entities.card.Card;

public class ConfirmOutputData {
    private Card card;
    public ConfirmOutputData(Card card) {
        this.card = card;
    }

    public Card getCard() {
        return card;
    }


}
