package use_case.GetCard;

import entities.card.NumberCard;

public interface GetCardDataAccessInterface {

    void getCard(String playerName, NumberCard numberCard);
}
