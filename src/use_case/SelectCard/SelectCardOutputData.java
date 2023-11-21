package use_case.SelectCard;

import entities.card.Card;
import entities.player.Player;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class SelectCardOutputData {

    private final Player player;
    private final HashMap<Card, Boolean> playerCards;

    public SelectCardOutputData (Player player, HashMap<Card, Boolean> playerCards) {
        this.player = player;
        this.playerCards = playerCards;
    }

    public Player getPlayer() {
        return player;
    }
    public HashMap<Card, Boolean> getPlayerCards() {
        return playerCards;
    }
}
