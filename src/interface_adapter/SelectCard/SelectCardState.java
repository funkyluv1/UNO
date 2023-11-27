package interface_adapter.SelectCard;

import entities.card.NumberCard;

public class SelectCardState {
    private NumberCard selectedCard = null;


    public NumberCard getSelectedCard() {
        return selectedCard;
    }

    public void setSelectedCard(NumberCard selectedCard) {
        this.selectedCard = selectedCard;
    }

}
