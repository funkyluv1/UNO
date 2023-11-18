package use_case.SelectCard;

import entities.player.*;
import entities.card.*;

public class SelectCardInputData{
    private final Player player;
    private final Card selectedCardNew;
    private final Object selectedCardOld;

    public SelectCardInputData (Player player, Card cardNew, Object cardOld) {
        this.player = player;
        selectedCardNew = cardNew;
        selectedCardOld = cardOld;
    }

    public Player getPlayer() {
        return player;
    }

    public Card getSelectedCardNew() {
        return selectedCardNew;
    }

    public Object getSelectedCardOld() {
        return selectedCardOld;
    }
}
