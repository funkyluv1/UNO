package entities.player;

import entities.card.FunctionalCard;
import entities.card.NumberCard;
import entities.player.Player;

import java.util.ArrayList;

public interface PlayerFactory {
    public Player create(String playerName, ArrayList<NumberCard> numcards, ArrayList<FunctionalCard> functionalCards);
}