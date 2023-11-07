package entities.card;

public class FunctionalCard extends Card {

    CardType type;
    /**
     * Constructs a new Card object, with its value attribute set to [value], color
     * attribute set to [color], player attribute initialized to null, isUsable
     * attribute set to false, and type set to [type].
     *
     * @param value the numeric value of the card
     * @param color the color of the card
     */
    public FunctionalCard(int value, String color, CardType type) {
        super(value, color);
        this.type = type;
    }

    // TODO: modify pre-turn, in-turn, post-turn handling methods
    public static void applyFunction(FunctionalCard funcCard) {
        switch (funcCard.type) {
            case PlusTwo:
                // handlePlusFour using helper
                break;
            case PlusFour:
                break;
            case Skip:
                break;
            case Wild:
                break;
            case Bomb:
                break;
            case HotPotato:
                break;
        }
    }
}
