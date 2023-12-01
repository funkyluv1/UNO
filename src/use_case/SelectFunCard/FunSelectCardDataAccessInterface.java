package use_case.SelectFunCard;

import entities.card.Card;

public interface FunSelectCardDataAccessInterface {
    void recordSelectCard(Card card);
    void recordUnselectCard(Card card);
}