package entities.player;

import entities.card.*;

import java.util.ArrayList;

public class HumanPlayer extends Player {
    private Card selectedCard;

    public HumanPlayer(int userID, String playerName, ArrayList<NumberCard> hand){
        super(userID, playerName, hand);
        selectedCard = null;
    }

    /**
     * TODO: complete javadoc
     *
     * @param topCard the most recently placed card in the game
     * @return the Card object dealt
     */
    @Override
    public Card dealCard(Card topCard) {
        // TODO: implement me
        return null;
    }

    public void setSelectedCard(Card card) {
        // TODO: the selected card must be an usable card;
        //  "highlight" this card after the GUI part is done
        selectedCard = card;
    }
}
