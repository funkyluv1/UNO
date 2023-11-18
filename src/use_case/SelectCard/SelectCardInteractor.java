package use_case.SelectCard;

import entities.card.Card;

public class SelectCardInteractor implements SelectCardInputDataBoundary{
    final SelectCardOutputDataBoundary selectCardOutputDataBoundary;

    public SelectCardInteractor (SelectCardOutputDataBoundary selectCardOutputDataBoundary) {
        this.selectCardOutputDataBoundary = selectCardOutputDataBoundary;
    }

    @Override
    public void execute(SelectCardInputData selectCardInputData) {
        Card selectedCardNew = selectCardInputData.getSelectedCardNew();
        Object selectedCardOld = selectCardInputData.getSelectedCardOld();
        SelectCardOutputData selectCardOutputData;
        if (selectedCardNew == selectedCardOld) {
            selectCardOutputData = new SelectCardOutputData(null);
        }
        else {
            selectCardOutputData = new SelectCardOutputData(selectedCardNew);
        }
        selectCardOutputDataBoundary.prepareSelectCardView(selectCardOutputData);
    }
}
