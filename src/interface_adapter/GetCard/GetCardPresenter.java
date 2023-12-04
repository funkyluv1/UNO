package interface_adapter.GetCard;

import entities.card.NumberCard;
import interface_adapter.Initialized.*;
import interface_adapter.ViewManagerModel;
import use_case.GetCard.GetCardOutputData;
import use_case.GetCard.GetCardOutputDataBoundary;

import java.util.ArrayList;

public class GetCardPresenter implements GetCardOutputDataBoundary {

    private ViewManagerModel viewManagerModel;
    private GetCardPanelViewModel getCardPanelViewModel;
    private CardButtonPanelViewModel cardButtonPanelViewModel;

    public GetCardPresenter(ViewManagerModel viewManagerModel,
                            GetCardPanelViewModel getCardPanelViewModel, CardButtonPanelViewModel cardButtonPanelViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.getCardPanelViewModel = getCardPanelViewModel;
        this.cardButtonPanelViewModel = cardButtonPanelViewModel;
    }

    @Override
    public void prepareNewGameView(GetCardOutputData getCardOutputData) {
        GetCardPanelState getCardState = getCardPanelViewModel.getState();
        NumberCard card = getCardOutputData.getNumberCard();
        getCardState.setNumberCard(card);
        if (card.getColor().charAt(0) == getCardState.getTopCard().getColor().charAt(0)){
            CardButtonPanelState cardButtonPanelState = cardButtonPanelViewModel.getState();
            cardButtonPanelState.setOneCardSelected(false);
            cardButtonPanelViewModel.setState(cardButtonPanelState);
            cardButtonPanelViewModel.firePropertyChanged();
        }
        getCardPanelViewModel.setState(getCardState);
        getCardPanelViewModel.firePropertyChanged();

        viewManagerModel.setActiveView("Initialized");
        viewManagerModel.firePropertyChanged();
    }
}
