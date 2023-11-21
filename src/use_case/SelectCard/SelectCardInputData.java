package use_case.SelectCard;

import entities.player.*;
import entities.card.*;

import java.util.ArrayList;

public class SelectCardInputData{
    private final Player player;
    private final Card selectedCard;

    public SelectCardInputData (Player player, Card cardNew) {
        this.player = player;
        selectedCard = cardNew;
    }

    public Player getPlayer() {
        return player;
    }

    public Card getSelectedCardNew() {
        return selectedCard;
    }

}
