package use_case.SelectFuncCard;

import entities.card.Card;
import entities.card.FunctionalCard;

import java.util.ArrayList;

public interface SelectFuncCardDataAccessInterface {
    void recordSelectFuncCard(FunctionalCard newCard, ArrayList<FunctionalCard> selectedCards);
    void recordUnselectFuncCard(FunctionalCard card);
}