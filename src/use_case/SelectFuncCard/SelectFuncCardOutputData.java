package use_case.SelectFuncCard;

import entities.card.Card;
import entities.card.FunctionalCard;

import java.util.ArrayList;

public class SelectFuncCardOutputData {
    private final ArrayList<FunctionalCard> selectedCards;
    private final ArrayList<Integer> selectedIndices;

    public SelectFuncCardOutputData(ArrayList<FunctionalCard> selectedCards, ArrayList<Integer> selectedIndices) {
        this.selectedCards = selectedCards;
        this.selectedIndices = selectedIndices;
    }

    public ArrayList<FunctionalCard> getSelectedCards() {
        return selectedCards;
    }

    public ArrayList<Integer> getSelectedIndices() {
        return selectedIndices;
    }
}