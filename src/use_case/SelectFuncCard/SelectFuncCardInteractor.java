package use_case.SelectFuncCard;

import entities.card.Card;
import entities.card.FunctionalCard;

import java.util.ArrayList;

import static use_case.initiation.InitiationInteractor.game;

public class SelectFuncCardInteractor implements SelectFuncCardInputDataBoundary {
    final SelectFuncCardOutputDataBoundary selectCardOutputDataBoundary;

    public SelectFuncCardInteractor(SelectFuncCardOutputDataBoundary selectCardOutputDataBoundary) {
        this.selectCardOutputDataBoundary = selectCardOutputDataBoundary;
    }

    @Override
    public void execute(SelectFuncCardInputData selectCardInputData) {
        FunctionalCard newCard = selectCardInputData.getNewCard();
        ArrayList<FunctionalCard> selectedCards = selectCardInputData.getSelectedCards();
        int newButtonIndex = selectCardInputData.getButton_index();

        selectedCards.add(newCard);
        game.setCurrSelectedFunCard(selectedCards);

        SelectFuncCardOutputData selectCardOutputData = new SelectFuncCardOutputData(selectedCards, newButtonIndex);
        selectCardOutputDataBoundary.prepareSelectCardView(selectCardOutputData);
    }
}