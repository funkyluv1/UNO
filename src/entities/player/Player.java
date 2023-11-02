package entities.player;

import entities.card.*;

import java.util.ArrayList;

/**
 * Player is the abstract base class for all player classes in this UNO game, which
 * include HumanPlayer and AIPlayer.
 * This class contains essential attributes for any player, which include:
 * - userID: a unique number assigned to this player, which is used for identifying
 *           this player in the database
 * - playerName: the name of this player to be displayed in the game
 * - numCards: the set of number cards that this player is currently holding
 * - funcCards: the set of functional cards that this player is currently holding
 * - usableCards: the set of cards in this player's hand that are actually usable in
 *                the current round
 * - isInGame: the boolean variable that determines whether the player is still playing
 *
 * @author Cynthia Luo
 */
public abstract class Player {
    private final int userID;
    public String playerName;
    private ArrayList<NumberCard> numCards;
    private ArrayList<Card> funcCards;
    private ArrayList<Card> usableCards;
    private boolean isInGame;


    /**
     * Constructs a new Player object, with its userID attribute set to [userID],
     * playerName attribute set to [playerName], its numCards attribute set to [hand],
     * usableCards and funcCards attributes set to an empty ArrayList of Card, and
     * its isInGame attribute set to true.
     *
     * @param userID a unique number assigned to this player, which is used for
     *               identifying this player in the database
     * @param playerName the name of this player to be displayed in the game
     * @param hand the set of cards that this player is dealt with initially
     */
    public Player(int userID, String playerName, ArrayList<NumberCard> hand) {
        this.userID = userID;
        this.playerName = playerName;
        this.numCards = new ArrayList<>(hand);
        this.funcCards = new ArrayList<>();
        this.usableCards = new ArrayList<>();
        this.isInGame = true;
    }

    /**
     * Returns the userID attribute of this Player object.
     *
     * @return the userID attribute of this Player object
     */
    public int getUserID() {
        return userID;
    }

    /**
     * Returns the numCards attribute of this Player object.
     *
     * @return the numCards attribute of this Player object
     */
    public ArrayList<NumberCard> getNumberCards() {
        return numCards;
    }

    /**
     * Returns the funcCards attribute of this Player object.
     *
     * @return the funcCards attribute of this Player object
     */
    public ArrayList<Card> getFuncCards() {
        return funcCards;
    }

    /**
     * Returns the usableCards attribute of this Player object.
     *
     * @return the usableCards attribute of this Player object
     */
    public ArrayList<Card> getUsableCards() {
        return usableCards;
    }


    /**
     * Updates the cards that are usable during this round.
     */
    public void updateUsableCards() {
        // TODO: implement me when you begin implementing pre-turn
    }

    /**
     * Returns the isInGame attribute of this Player object.
     *
     * @return the isInGame attribute of this Player object
     */
    public boolean isPlaying() {
        return isInGame;
    }

    /**
     * Sets the isInGame attribute of this Player object to false.
     */
    public void quit() {
        isInGame = false;
    }

    /**
     * Returns the Card object dealt by this Player object and removes it from the
     * hand; HumanPlayer and AIPlayer have different implementations for this method.
     *
     * @return the Card object dealt
     */
    public abstract Card dealCard();
}
