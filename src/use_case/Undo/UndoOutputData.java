package use_case.Undo;

import entities.card.Card;

public class UndoOutputData {

    private final Object unselectedCard;

    public UndoOutputData (Object unselectedCard) {
        this.unselectedCard = unselectedCard;
    }

    public Object getUnselectedCard () {
        return unselectedCard;
    }
}
