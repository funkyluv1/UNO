package interface_adapter.GetCard;

import interface_adapter.Initialized.CardButtonPanelViewModel;
import interface_adapter.Initialized.FunCardButtonPanelViewModel;
import interface_adapter.Initialized.GetCardPanelState;
import interface_adapter.ViewManagerModel;
import use_case.GetCard.GetCardOutputData;
import use_case.GetCard.GetCardOutputDataBoundary;

public class GetCardPresenter implements GetCardOutputDataBoundary {

    private ViewManagerModel viewManagerModel;
    private GetCardViewModel getCardViewModel;

    public GetCardPresenter(ViewManagerModel viewManagerModel, GetCardViewModel getCardViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.getCardViewModel = getCardViewModel;
    }
    @Override
    public void prepareNewGameView(GetCardOutputData getCardOutputData) {
        GetCardState getCardState = getCardViewModel.getState();
        getCardState.setNumberCard(getCardOutputData.getNumberCard());

        getCardViewModel.setState(getCardState);
        viewManagerModel.firePropertyChanged();
    }
}
