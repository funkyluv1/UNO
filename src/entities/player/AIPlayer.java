package entities.player;

import entities.card.Card;

import java.util.ArrayList;

public class AIPlayer extends Player {

    public AIPlayer(int userID, String playerName, ArrayList<Card> hand){
        super(userID, playerName, hand);
    }

    public Card dealCard() {
        // deal a random card
        return null;
    }
}
