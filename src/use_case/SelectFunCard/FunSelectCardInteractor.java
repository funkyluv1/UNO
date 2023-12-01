package use_case.SelectFunCard;

import entities.card.Card;
import use_case.SelectCard.SelectCardOutputData;
import use_case.SelectCard.SelectCardOutputDataBoundary;

import static use_case.initiation.InitiationInteractor.game;

public class FunSelectCardInteractor implements FunSelectCardInputDataBoundary {
    final SelectCardOutputDataBoundary selectCardOutputDataBoundary;

    public FunSelectCardInteractor(SelectCardOutputDataBoundary selectCardOutputDataBoundary) {
        this.selectCardOutputDataBoundary = selectCardOutputDataBoundary;
    }

    @Override
    public void execute(FunSelectCardInputData selectCardInputData) {
        Card selectedCard = selectCardInputData.getSelectedCardNew();
        game.setCurrSelectedNumberCard(selectedCard);
        SelectCardOutputData selectCardOutputData = new SelectCardOutputData(selectedCard, selectCardInputData.getButton_index());
        selectCardOutputDataBoundary.prepareSelectCardView(selectCardOutputData);
    }
}