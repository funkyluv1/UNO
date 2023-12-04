package interface_adapter.Confirm;

import entities.card.NumberCard;
import interface_adapter.Initialized.*;
import interface_adapter.ViewManagerModel;
import use_case.Confirm.ConfirmOutputData;
import use_case.Confirm.ConfirmOutputDataBoundary;
import use_case.SelectCard.SelectCardOutputData;
import use_case.SelectCard.SelectCardOutputDataBoundary;

import java.util.ArrayList;

public class ConfirmPresenter implements ConfirmOutputDataBoundary {

    private final BottomPanelViewModel bottomPanelViewModel;
    private ViewManagerModel viewManagerModel;
    private final GetCardPanelViewModel getCardPanelViewModel;
    private final CardButtonPanelViewModel cardButtonPanelViewModel;
    private final FunCardButtonPanelViewModel funCardButtonPanelViewModel;

    public ConfirmPresenter(ViewManagerModel viewManagerModel, BottomPanelViewModel bottomPanelViewModel,
                            CardButtonPanelViewModel cardButtonPanelViewModel, FunCardButtonPanelViewModel funCardButtonPanelViewModel,
                            GetCardPanelViewModel getCardPanelViewModel){
        this.viewManagerModel = viewManagerModel;
        this.bottomPanelViewModel = bottomPanelViewModel;
        this.cardButtonPanelViewModel = cardButtonPanelViewModel;
        this.funCardButtonPanelViewModel = funCardButtonPanelViewModel;
        this.getCardPanelViewModel = getCardPanelViewModel;
    }

    @Override
    public void prepareSuccessView(ConfirmOutputData outputData) {
        BottomPanelState bottomPanelState = bottomPanelViewModel.getState();
        bottomPanelState.setConfirmButtonEnabled(false);
        bottomPanelState.setNextButtonEnabled(true);
        bottomPanelViewModel.setState(bottomPanelState);
        bottomPanelViewModel.firePropertyChanged();

        CardButtonPanelState cardButtonPanelState = cardButtonPanelViewModel.getState();
        cardButtonPanelState.setOneCardSelected(true);
        ArrayList<NumberCard> cards = cardButtonPanelState.getPlayerNumCards();
        cards.remove(outputData.getCard());
        cardButtonPanelState.setPlayerNumCards(cards);
        cardButtonPanelViewModel.setState(cardButtonPanelState);
        cardButtonPanelViewModel.firePropertyChanged();

        FunCardButtonPanelState funCardButtonPanelState = funCardButtonPanelViewModel.getState();
        funCardButtonPanelState.setAllButtonDisable(false);
        funCardButtonPanelViewModel.setState(funCardButtonPanelState);
        funCardButtonPanelViewModel.firePropertyChanged();

        GetCardPanelState getCardPanelState = getCardPanelViewModel.getState();
        getCardPanelState.setUndoEnabled(false);
        getCardPanelState.setGetCardEnabled(false);
        getCardPanelViewModel.setState(getCardPanelState);
        getCardPanelViewModel.firePropertyChanged();

        viewManagerModel.setActiveView("Initialized");
        viewManagerModel.firePropertyChanged();
    }

}
