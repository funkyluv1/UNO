package use_case.SelectCard;

import entities.card.Card;
import static use_case.initiation.InitiationInteractor.game;

public class SelectCardInteractor implements SelectCardInputDataBoundary{
    final SelectCardOutputDataBoundary selectCardOutputDataBoundary;
    final SelectCardDataAccessInterface selectCardDataAccessInterface;

    public SelectCardInteractor(SelectCardOutputDataBoundary selectCardOutputDataBoundary, SelectCardDataAccessInterface selectCardDataAccessInterface) {
        this.selectCardOutputDataBoundary = selectCardOutputDataBoundary;
        this.selectCardDataAccessInterface = selectCardDataAccessInterface;
    }

    @Override
    public void execute(SelectCardInputData selectCardInputData) {
        Card selectedCard = selectCardInputData.getSelectedCardNew();
        selectCardDataAccessInterface.recordSelectCard(selectedCard);
        SelectCardOutputData selectCardOutputData = new SelectCardOutputData(selectedCard);
        selectCardOutputDataBoundary.prepareSelectCardView(selectCardOutputData);
    }
}
