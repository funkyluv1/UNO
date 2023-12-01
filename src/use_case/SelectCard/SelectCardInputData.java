package use_case.SelectCard;

import entities.player.*;
import entities.card.*;

public class SelectCardInputData{
    private final Card selectedCard;
    private final int button_index;

    public SelectCardInputData (Card cardNew, int button_index) {
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