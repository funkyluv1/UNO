package use_case.SelectCard;

import entities.card.Card;
import static use_case.initiation.InitiationInteractor.game;

public class SelectCardInteractor implements SelectCardInputDataBoundary{
    final SelectCardOutputDataBoundary selectCardOutputDataBoundary;

    public SelectCardInteractor(SelectCardOutputDataBoundary selectCardOutputDataBoundary) {
        this.selectCardOutputDataBoundary = selectCardOutputDataBoundary;
    }

    @Override
    public void execute(SelectCardInputData selectCardInputData) {
        Card selectedCard = selectCardInputData.getSelectedCardNew();
        game.setCurrSelectedNumberCard(selectedCard);
        SelectCardOutputData selectCardOutputData = new SelectCardOutputData(selectedCard, selectCardInputData.getButton_index());
        selectCardOutputDataBoundary.prepareSelectCardView(selectCardOutputData);
    }
}