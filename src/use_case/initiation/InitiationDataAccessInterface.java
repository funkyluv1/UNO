package use_case.initiation;

import entities.NumberCardsDeck.NumberCardsDeck;
import entities.card.FunctionalCard;
import entities.card.NumberCard;

import java.util.ArrayList;

public interface InitiationDataAccessInterface {
    void initiate(NumberCardsDeck numberCardsDeck, InitiationInputData initiationInputData);
    void savePlayerwithCards(String playerName, ArrayList<NumberCard> numberCards, ArrayList<FunctionalCard>functionalCards, int displayFirstCardIndex);
}
