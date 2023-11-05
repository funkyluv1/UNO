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

    public static void test(FunctionalCard funcCard) {
        switch (funcCard.type) {
            case PlusFour:
                // handlePlusFour using helper
                break;


        }
    }
}
