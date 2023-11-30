package use_case.SelectCard;

import entities.player.*;
import entities.card.*;

public class SelectCardInputData{
    private final String player;
    private final Card selectedCard;
    private final int button_index;

    public SelectCardInputData (String player, Card cardNew, int button_index) {
        this.player = player;
        selectedCard = cardNew;
        this.button_index = button_index;
    }

    public String getPlayer() {
        return player;
    }

    public Card getSelectedCardNew() {
        return selectedCard;
    }

    public int getButton_index(){
        return button_index;
    }

}