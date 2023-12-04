package entities.card;

import entities.player.Player;

public class FunctionalCard extends Card {
    private final String type;
    /**
     * Constructs a new Card object, with its value attribute set to [value], color
     * attribute set to [color], player attribute initialized to null, isUsable
     * attribute set to false, and type set to [type].
     *
     * @param value the numeric value of the card
     * @param color the color of the card
     */
    public FunctionalCard(int value, String color, String type) {
        super(value, color);
        this.type = type;
    }

//    public static void applyFunction(FunctionalCard funcCard) {
//        switch (funcCard.type) {
//            case "PlusTwo":
//                // handlePlusFour using helper
//                break;
//            case "PlusFour":
//                break;
//            case "Skip":
//                break;
//            case "Wild":
//                break;
//            case "Bomb":
//                break;
//            case "HotPotato":
//                break;
//        }
//    }

    public String getType() {
        return type;
    }

    public String getString(){
        String s = String.valueOf(this.getValue());
        return type + s + this.getColor();}
}
