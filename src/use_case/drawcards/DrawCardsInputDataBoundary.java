package use_case.drawcards;

import entities.NumberCardsDeck;
import entities.card.NumberCard;

import java.util.ArrayList;


public interface DrawCardsInputDataBoundary {
    NumberCardsDeck createNumberCardsDeck();
    void reshuffleNumberCardsDeck(DrawCardsInputData drawCardsInputData);
    void drawNumberCards(DrawCardsInputData drawCardsInputData);
}
