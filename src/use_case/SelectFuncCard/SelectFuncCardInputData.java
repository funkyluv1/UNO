package use_case.SelectFuncCard;

import entities.card.Card;
import entities.card.FunctionalCard;

import java.util.ArrayList;

public class SelectFuncCardInputData {
    private final FunctionalCard newCard;
    private final ArrayList<FunctionalCard> selectedCards;
    private final int button_index;

    public SelectFuncCardInputData(FunctionalCard cardNew, ArrayList<FunctionalCard> cardsOld, int button_index) {
        newCard = cardNew;
        selectedCards = cardsOld;
        this.button_index = button_index;
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

}