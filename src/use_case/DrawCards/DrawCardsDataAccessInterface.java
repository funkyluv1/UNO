package use_case.DrawCards;

import entities.NumberCardsDeck.NumberCardsDeck;
import entities.card.NumberCard;

import java.util.ArrayList;

public interface DrawCardsDataAccessInterface {
    public NumberCardsDeck createNumberCardsDeck();
    public void reshuffleNumberCardsDeck(NumberCardsDeck numberCardsDeck);
    ArrayList<NumberCard> drawNumberCards(NumberCardsDeck numberCardsDeck, int drawNumber);
}
