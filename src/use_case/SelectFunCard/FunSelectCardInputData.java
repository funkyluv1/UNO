package use_case.SelectFunCard;

import entities.card.Card;

public class FunSelectCardInputData {
    private Card selectedCardNew;
    private int buttonIndex;

    public FunSelectCardInputData(Card selectedCardNew, int buttonIndex) {
        this.selectedCardNew = selectedCardNew;
        this.buttonIndex = buttonIndex;
    }
    public Card getSelectedCardNew() {
        return selectedCardNew;
    }

    public int getButtonIndex() {
        return buttonIndex;
    }
}
