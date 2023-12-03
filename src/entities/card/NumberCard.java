package entities.card;

public class NumberCard extends Card {
    public NumberCard(int value, String color) {
        super(value, color);
        String id = "number_card_" + color + "_" + Integer.toString(value);
        super.changeID(Integer.toString(value));
    }
    public String getString(){return String.valueOf(this.getValue()) + Character.toUpperCase(this.getColor().charAt(0));}
}
