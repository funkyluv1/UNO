package entities.card;

public class NumberCardFactory extends CardFactory {
    private NumberCard numberCard;

    public NumberCardFactory(int value, String color) {
        this.numberCard = new NumberCard(value, color);
    }

    @Override
    public NumberCard createCard() {
        return this.numberCard;
    }
}
