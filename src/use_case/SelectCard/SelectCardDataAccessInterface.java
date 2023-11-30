package use_case.SelectCard;

import entities.card.Card;

public interface SelectCardDataAccessInterface {
    void recordSelectCard(Card card);
    void recordUnselectCard(Card card);
}