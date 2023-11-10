package entities.card;
public abstract class CardFactory {
    private Card card;
    public Card createCard() {
        return card;
    };
}
