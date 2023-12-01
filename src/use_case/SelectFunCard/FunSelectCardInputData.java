package use_case.SelectFunCard;

import entities.card.Card;

public class FunSelectCardInputData{
    private final Card selectedCard;
    private final int button_index;

    public FunSelectCardInputData (Card cardNew, int button_index) {
        selectedCard = cardNew;
        this.button_index = button_index;
    }

    public Card getSelectedCardNew() {
        return selectedCard;
    }

    public int getButton_index(){
        return button_index;
    }

}