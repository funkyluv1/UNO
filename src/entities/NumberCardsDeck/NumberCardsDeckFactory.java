package entities.NumberCardsDeck;

public interface NumberCardsDeckFactory {
    NumberCardsDeck create(String id, int remainingCards);
}
