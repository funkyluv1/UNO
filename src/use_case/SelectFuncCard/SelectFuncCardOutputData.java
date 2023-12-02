package use_case.SelectFuncCard;

import entities.card.Card;
import entities.card.FunctionalCard;

import java.util.ArrayList;

public class SelectFuncCardOutputData {
    private final ArrayList<FunctionalCard> selectedCards;
    private final Integer button_Index;

    public SelectFuncCardOutputData(ArrayList<FunctionalCard> selectedCards, Integer button_index) {
        this.selectedCards = selectedCards;
        this.button_Index = button_index;
    }

    public ArrayList<FunctionalCard> getSelectedCards() {
        return selectedCards;
    }

    public Integer getButton_Index() {
        return button_Index;
    }
}