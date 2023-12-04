package interface_adapter.GetCard;

import entities.card.NumberCard;
import interface_adapter.Initialized.CardButtonPanelViewModel;
import interface_adapter.Initialized.FunCardButtonPanelViewModel;
import interface_adapter.Initialized.GetCardPanelState;
import interface_adapter.Initialized.GetCardPanelViewModel;
import interface_adapter.ViewManagerModel;
import use_case.GetCard.GetCardOutputData;
import use_case.GetCard.GetCardOutputDataBoundary;

import java.util.ArrayList;

public class GetCardPresenter implements GetCardOutputDataBoundary {

    private ViewManagerModel viewManagerModel;
    private GetCardPanelViewModel getCardPanelViewModel;

    public GetCardPresenter(ViewManagerModel viewManagerModel, GetCardPanelViewModel getCardPanelViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.getCardPanelViewModel = getCardPanelViewModel;
    }

    @Override
    public void prepareNewGameView(GetCardOutputData getCardOutputData) {
        GetCardPanelState getCardState = getCardPanelViewModel.getState();
        NumberCard card = getCardOutputData.getNumberCard();
        getCardState.setNumberCard(card);
        getCardPanelViewModel.setState(getCardState);

        viewManagerModel.setActiveView("Initialized");
        viewManagerModel.firePropertyChanged();
    }
}
