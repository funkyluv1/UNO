package entities.card;

public class FunctionalCardBuilder implements CardBuilder {
    private FunctionalCard functionalCard;

    public FunctionalCardBuilder(int value, String color, String type) {
        this.functionalCard = new FunctionalCard(value, color, type);
    }

    @Override
    public FunctionalCard createCard() {
        return this.functionalCard;
    }
}
