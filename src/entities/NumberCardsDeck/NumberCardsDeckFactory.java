package entities.NumberCardsDeck;

public class NumberCardsDeckFactory {
    public NumberCardsDeck create(String id, int remainingCards) {
        return new NumberCardsDeck(id, remainingCards);
    }
}
