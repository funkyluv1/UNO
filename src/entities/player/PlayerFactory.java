package entities.player;

import entities.card.Card;
import entities.card.NumberCard;

import java.util.ArrayList;

public interface PlayerFactory {
    public Player create(String playerName, ArrayList<Card> hand);
}
