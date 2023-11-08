package entities.card;

public class WildCard extends Card{
    /**
     * Constructs a new WildCard object.
     *
     * @param value the numeric value of the card
     */
    public WildCard(int value) {
        super(value, "any");
    }
}
