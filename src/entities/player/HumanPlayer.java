package entities.player;

import entities.card.Card;

import java.util.ArrayList;

public class HumanPlayer extends Player {
    private Card selectedCard;

    public HumanPlayer(int userID, String playerName, ArrayList<Card> hand){
        super(userID, playerName, hand);
        selectedCard = null;
    }

    public void setSelectedCard(Card card) {
        // selected card should be "highlighted"
        selectedCard = card;
    }

    public Card dealCard() {
        // deal the selected card and remove it from the hand
        return null;
    }
}
