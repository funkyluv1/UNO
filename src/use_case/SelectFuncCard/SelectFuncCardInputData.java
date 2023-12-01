package use_case.SelectFuncCard;

import entities.card.Card;
import entities.card.FunctionalCard;

import java.util.ArrayList;

public class SelectFuncCardInputData {
    private final FunctionalCard newCard;
    private final ArrayList<FunctionalCard> selectedCards;
    private final int button_index;
    private final ArrayList<Integer> selectedIndices;

    public SelectFuncCardInputData(FunctionalCard cardNew, ArrayList<FunctionalCard> cardsOld, int button_index, ArrayList<Integer> selectedIndices) {
        newCard = cardNew;
        selectedCards = cardsOld;
        this.button_index = button_index;
        this.selectedIndices = selectedIndices;
    }

    public FunctionalCard getNewCard() {
        return newCard;
    }
    public ArrayList<FunctionalCard> getSelectedCards() {
        return selectedCards;
    }

    public int getButton_index(){
        return button_index;
    }

    public ArrayList<Integer> getSelectedIndices() {
        return selectedIndices;
    }
}