package entities.player;

import entities.Game;
import entities.card.*;

import java.util.ArrayList;
import java.util.List;

/**
 * AIPlayer is the concrete class for all instances of AI players and a subclass of
 * Player. Any action of an AI player is controlled by the program itself.
 *
 * @author Cynthia Luo
 */
public class AIPlayer extends Player {

    /**
     * Constructs a new AIPlayer object, with its userID attribute set to [userID],
     * playerName attribute set to [playerName], its numCards attribute set to [hand],
     * funcCards attributes set to an empty ArrayList of Card, and its isInGame
     * attribute set to true.
     *
     * @param playerName the name of this player to be displayed in the game
     * @param hand the set of cards that this player is dealt with initially
     */
    public AIPlayer(String playerName, ArrayList<NumberCard> hand, int displayFirstCardIndex){
        super(playerName, hand, displayFirstCardIndex);
    }

    /**
     * Returns a random Card object to be dealt by this AIPlayer object and removes
     * it from the hand.
     *
     * @return the Card object dealt
     */
    @Override
    public Card dealCard(Card topCard) {
        List<Card> usableCards = super.getUsableCards(topCard);
        if (usableCards.isEmpty()) {
            // TODO: implement draw card action
            return null;
        }
        else {
            int randIndex = (int) Math.floor(Math.random()*usableCards.size());
            Card dealtCard = usableCards.get(randIndex);
            if (dealtCard instanceof NumberCard)
                getNumberCards().remove(dealtCard);
            else
                getFuncCards().remove(dealtCard);
            return dealtCard;
        }
    }
//
//    @Override
//    public void preTurn(Game game) {
//        // TODO: implement me
//    }
//
//    @Override
//    public void inTurn(Game game) {
//        // TODO: implement me
//    }
//
//    @Override
//    public void postTurn(Game game) {
//        // TODO: implement me
//    }
}