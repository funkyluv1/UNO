package entities.card;

public class NumberCardBuilder implements CardBuilder {
    private NumberCard numberCard;

    public NumberCardBuilder(int value, String color) {
        this.numberCard = new NumberCard(value, color);
    }

    @Override
    public NumberCard createCard() {
        return this.numberCard;
    }
}
