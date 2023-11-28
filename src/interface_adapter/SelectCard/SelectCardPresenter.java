package interface_adapter.SelectCard;

import interface_adapter.ViewManagerModel;
import use_case.SelectCard.SelectCardOutputData;
import use_case.SelectCard.SelectCardOutputDataBoundary;

public class SelectCardPresenter implements SelectCardOutputDataBoundary {
    private final SelectCardViewModel selectCardViewModel;
    private ViewManagerModel viewManagerModel;

    public SelectCardPresenter(ViewManagerModel viewManagerModel, SelectCardViewModel selectCardViewModel){
        this.viewManagerModel = viewManagerModel;
        this.selectCardViewModel = selectCardViewModel;
    }

    @Override
    public void prepareSelectCardView(SelectCardOutputData selectCardOutputData) {
        SelectCardState selectCardState = selectCardViewModel.getSelectCardState();
        selectCardState.setSelectedCard(selectCardOutputData.getSelectedCard());
        selectCardViewModel.setSelectCardState(selectCardState);
        viewManagerModel.firePropertyChanged();
    }
}
