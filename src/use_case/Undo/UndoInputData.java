package use_case.Undo;

import entities.card.Card;

public class UndoInputData {
    final private Card selectedCard;

    public UndoInputData(Card selectedCard){
        this.selectedCard = selectedCard;
    }

    public Card getSelectedCard() {
        return selectedCard;
    }
}