package entities.card;

public class FunctionalCardFactory extends CardFactory {
    private FunctionalCard functionalCard;
    private String colorCard;
    private String typeCard;
    private int valueCard;

    public FunctionalCardFactory(int value, String color, String type) {
        colorCard = color;
        valueCard = value;
        typeCard = type;
        this.functionalCard = new FunctionalCard(value, color, type);
    }

    @Override
    public FunctionalCard createCard() {
        if (typeCard.charAt(0) == '+'){
            if (typeCard.charAt(1) == '2'){
                return new PlusTwoCard();
            } else {return new PlusFourCard();}
        } else {return new SkipCard();}
    }
}
