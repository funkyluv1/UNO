package use_case.Undo;

import entities.card.Card;

public interface UndoDataAccessInterface {
    void recordUnselectCard(Card card);
}
