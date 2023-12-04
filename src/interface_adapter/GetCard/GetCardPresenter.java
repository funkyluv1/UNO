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
        CardButtonPanelState cardButtonPanelState = cardButtonPanelViewModel.getState();

        NumberCard card = getCardOutputData.getNumberCard();
        ArrayList<NumberCard> numberCards = cardButtonPanelState.get_Number_Cards();
        numberCards.add(card);
        cardButtonPanelState.setPlayerNumCards(numberCards);
        if (card.getColor().charAt(0) == getCardState.getTopCard().getColor().charAt(0)){
            ArrayList<NumberCard> playableNumberCards = cardButtonPanelState.get_Highlighted_Number_Cards();
            playableNumberCards.add(card);
            cardButtonPanelState.set_Highlighted_Number_Cards(playableNumberCards);
            cardButtonPanelState.setOneCardSelected(false);
            cardButtonPanelViewModel.setState(cardButtonPanelState);
            cardButtonPanelViewModel.firePropertyChanged();
        }
        getCardState.setNumberCard(card);
        getCardPanelViewModel.setState(getCardState);
        getCardPanelViewModel.firePropertyChanged();

        viewManagerModel.setActiveView("Initialized");
        viewManagerModel.firePropertyChanged();
    }
}
