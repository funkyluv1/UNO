package entities.player;

import entities.card.*;

import java.util.ArrayList;

public class HumanPlayer extends Player {
    private Card selectedCard;

    public HumanPlayer(int userID, String playerName, ArrayList<NumberCard> hand){
        super(userID, playerName, hand);
        selectedCard = null;
    }

    public void setSelectedCard(Card card) {
        // TODO: the selected card must be an usable card;
        //  "highlight" this card after the GUI part is done
        selectedCard = card;
    }

    public Card dealCard() {
        // TODO: implement me, deal the selected card and remove it from the hand
        return null;
    }
}
