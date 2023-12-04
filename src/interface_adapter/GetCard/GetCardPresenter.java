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
    private BottomPanelViewModel bottomPanelViewModel;

    public GetCardPresenter(ViewManagerModel viewManagerModel,
                            GetCardPanelViewModel getCardPanelViewModel, CardButtonPanelViewModel cardButtonPanelViewModel,
                            BottomPanelViewModel bottomPanelViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.getCardPanelViewModel = getCardPanelViewModel;
        this.cardButtonPanelViewModel = cardButtonPanelViewModel;
        this.bottomPanelViewModel = bottomPanelViewModel;
    }

    @Override
    public void prepareNewGameView(GetCardOutputData getCardOutputData) {
        GetCardPanelState getCardState = getCardPanelViewModel.getState();
        CardButtonPanelState cardButtonPanelState = cardButtonPanelViewModel.getState();
        BottomPanelState bottomPanelState = bottomPanelViewModel.getState();

        NumberCard card = getCardOutputData.getNumberCard();
        if (card.getColor().charAt(0) == getCardState.getTopCard().getColor().charAt(0)){
            ArrayList<NumberCard> playableNumberCards = cardButtonPanelState.get_Highlighted_Number_Cards();
            playableNumberCards.add(card);
            bottomPanelState.setNextButtonEnabled(false);
            bottomPanelState.setConfirmButtonEnabled(false);
            bottomPanelViewModel.setState(bottomPanelState);
            bottomPanelViewModel.firePropertyChanged();
            cardButtonPanelState.set_Highlighted_Number_Cards(playableNumberCards);
            cardButtonPanelState.setOneCardSelected(false);
        }
        if (cardButtonPanelState.get_Number_Cards().size() > 3){
            cardButtonPanelState.setRightButtonEnabled(true);
            cardButtonPanelState.setLeftButtonEnabled(false);
        }
        else {
            cardButtonPanelState.setRightButtonEnabled(false);
            cardButtonPanelState.setLeftButtonEnabled(false);
        }

        cardButtonPanelViewModel.setState(cardButtonPanelState);
        cardButtonPanelViewModel.firePropertyChanged();

        getCardState.setNumberCard(card);
        getCardPanelViewModel.setState(getCardState);
        getCardPanelViewModel.firePropertyChanged();

        viewManagerModel.setActiveView("Initialized");
        viewManagerModel.firePropertyChanged();
    }
}
