package entities.player;

import entities.Game;
import entities.card.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Player is the abstract base class for all player classes in this UNO game, which
 * include HumanPlayer.
 * This class contains essential attributes for any player, which include:
 * - userID: a unique number assigned to this player, which is used for identifying
 *           this player in the database
 * - playerName: the name of this player to be displayed in the game
 * - numCards: the set of number cards that this player is currently holding
 * - funcCards: the set of functional cards that this player is currently holding
 * - isInGame: the boolean variable that determines whether the player is still playing
 *
 * @author Cynthia Luo
 */
public abstract class Player {
    public String playerName;
    private ArrayList<NumberCard> numCards;
    private ArrayList<FunctionalCard> funcCards;

    private int displayFirstCardIndex;
    private boolean isInGame;



    /**
     * Constructs a new Player object, with its userID attribute set to [userID],
     * playerName attribute set to [playerName], its numCards attribute set to [hand],
     * funcCards attributes set to an empty ArrayList of Card, and its isInGame
     * attribute set to true.
     *

     * @param playerName the name of this player to be displayed in the game
     * @param hand the set of cards that this player is dealt with initially
     */
    public Player(String playerName, ArrayList<NumberCard> hand, int displayFirstCardIndex) {
        this.playerName = playerName;
        this.numCards = new ArrayList<>(hand);
        this.funcCards = new ArrayList<>();
        this.displayFirstCardIndex = displayFirstCardIndex;
        this.isInGame = true;
    }

    public String getPlayerName(){return this.playerName;}

    /**
     * Returns the numCards attribute of this Player object.
     *
     * @return the numCards attribute of this Player object
     */
    public ArrayList<NumberCard> getNumberCards() {
        return numCards;
    }

    /**
     * Sets the numCards attribute of this player object to [newNumCards].
     *
     * @param newNumCards the new hand of number cards that this player will hold.
     */
    public void setNumCards (ArrayList<NumberCard> newNumCards) {
        numCards = newNumCards;
    }

    /**
     * Returns the funcCards attribute of this Player object.
     *
     * @return the funcCards attribute of this Player object
     */
    public ArrayList<FunctionalCard> getFuncCards() {
        return funcCards;
    }

    /**
     * Sets the funcCards attribute of this player object to [newFuncCards].
     *
     * @param newFuncCards the new hand of functional cards that this player will
     *                     hold.
     */
    public void setFuncCards (ArrayList<FunctionalCard> newFuncCards) {
        funcCards = newFuncCards;
    }

    /**
     * Update the availability of each card in the player's hand.
     *
     * @param topCard the most recently played number card.
     * @param funcCards the list of functional cards played by the last player.
     */
    public void setDisplayFirstCardIndex(int displayFirstCardIndex) {
        displayFirstCardIndex = displayFirstCardIndex;
    }
    public int getDisplayFirstCardIndex() {
        return displayFirstCardIndex;
    }
    public void updateUsableCards(Card topCard, ArrayList<Card> funcCards) {
        // TODO: Implement me, update isUsable of each card
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
     * hand; HumanPlayer and have different implementations for this method.
     *
     * @param topCard the most recently placed card in the game
     * @return the Card object dealt
     */
    public abstract Card dealCard(Card topCard);

    protected List<Card> getUsableCards(Card topCard) {
        return null;
    }
}
