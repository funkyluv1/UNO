package entities.card;

public class NumberCard extends Card {
    public NumberCard(int value, String color) {
        super(value, color);
    }
    public String getString(){return String.valueOf(this.getValue()) + this.getColor().charAt(0);}
}
