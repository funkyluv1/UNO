package entities.player;

import entities.card.*;

import java.util.ArrayList;

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
     * usableCards and funcCards attributes set to an empty ArrayList of Card, and
     * its isInGame attribute set to true.
     *
     * @param userID a unique number assigned to this player, which is used for
     *               identifying this player in the database
     * @param playerName the name of this player to be displayed in the game
     * @param hand the set of cards that this player is dealt with initially
     */
    public AIPlayer(int userID, String playerName, ArrayList<NumberCard> hand){
        super(userID, playerName, hand);
    }

    /**
     * Returns a random Card object to be dealt by this AIPlayer object and removes
     * it from the hand.
     *
     * @return the Card object dealt
     */
    public Card dealCard() {
        int randIndex = (int) Math.floor(Math.random()*(getUsableCards().size()));
        Card dealtCard = getUsableCards().get(randIndex);
        getUsableCards().remove(randIndex);
        if (dealtCard instanceof NumberCard) {
            getNumberCards().remove(dealtCard);
        }
        else {
            getFuncCards().remove(dealtCard);
        }
        return dealtCard;
    }
}
