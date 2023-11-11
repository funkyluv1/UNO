package entities.card;

public class FunctionalCardFactory extends CardFactory {
    private FunctionalCard functionalCard;

    public FunctionalCardFactory(int value, String color, String type) {
        this.functionalCard = new FunctionalCard(value, color, type);
    }

    @Override
    public FunctionalCard createCard() {
        return this.functionalCard;
    }
}
