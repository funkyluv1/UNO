package use_case.GetCard;

import entities.NumberCardsDeck.NumberCardsDeck;
import entities.card.NumberCard;

public interface GetCardDataAccessInterface {

    void recordGetCard(int index, NumberCard numberCard);

}
