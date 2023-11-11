package entities.card;

import entities.player.Player;

/**
 * Card is the abstract base class for all types of playing cards in this UNO game.
 * This class contains essential attributes for any card, which include:
 * - id: the id of a card; id is unique for each type of card
 * - value: the numeric value of a card
 * - color: the color of a card
 * - player: the current holder of a card
 * - isUsable: whether the card can be used by a player during this round
 *
 * @author Cynthia Luo
 */
public abstract class Card {
    private String id = "";
    private final int value;
    private final String color;
    public boolean isUsable;

    /**
     * Constructs a new Card object.
     *
     * @param value the numeric value of the card
     * @param color the color of the card
     */
    public Card(int value, String color) {
        this.value = value;
        this.color = color;
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
     * Change the id of the current card.
     *
     * @param id new id
     */
    void changeID(String id) {
        this.id = id;
    }

}
