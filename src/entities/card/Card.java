package entities.card;

import entities.player.Player;

/**
 * Card is the abstract base class for all types of playing cards in this UNO game.
 * This class contains essential attributes for any card, which include:
 * - value: the numeric value of a card
 * - color: the color of a card
 * - player: the current holder of a card
 * - isUsable: whether the card can be used by a player during this round
 *
 * @author Cynthia Luo
 */
public abstract class Card {
    private final int value;
    private final String color;
    private Player player;
    public boolean isUsable;

    /**
     * Constructs a new Card object, with its value attribute set to [value], color
     * attribute set to [color], player attribute initialized to null, and isUsable
     * attribute set to false.
     *
     * @param value the numeric value of the card
     * @param color the color of the card
     */
    public Card(int value, String color) {
        this.value = value;
        this.color = color;
        player = null;
        isUsable = false;
    }

    /**
     * Returns the value attribute of this Card object.
     *
     * @return the value attribute of this Card object
     */
    public int getValue() {
        return value;
    }

    /**
     * Returns the color attribute of this Card object.
     *
     * @return the color attribute of this Card object
     */
    public String getColor() {
        return color;
    }

    /**
     * Returns the player attribute of this Card object.
     *
     * @return the player attribute of this Card object
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Sets the player attribute of this Card object to [player].
     *
     * @param player the player who will be holding this card
     */
    public void setPlayer(Player player) {
        this.player = player;
    }
}
