package use_case.drawcards;

import entities.NumberCardsDeck;

import java.util.ArrayList;

public interface DrawCardsDataAccessInterface {
    public NumberCardsDeck createNumberCardsDeck();
    public void reshuffleNumberCardsDeck(NumberCardsDeck numberCardsDeck);
    ArrayList<String> drawNumberCards(NumberCardsDeck numberCardsDeck, int drawNumber);
}
