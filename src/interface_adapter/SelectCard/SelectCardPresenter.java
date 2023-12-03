package interface_adapter.SelectCard;

import interface_adapter.Initialized.*;
import interface_adapter.ViewManagerModel;
import use_case.SelectCard.SelectCardOutputData;
import use_case.SelectCard.SelectCardOutputDataBoundary;
import view.BottomPanel;
import view.CardButtonPanel;

public class SelectCardPresenter implements SelectCardOutputDataBoundary {
    private final CardButtonPanelViewModel cardButtonPanelViewModel;
    private ViewManagerModel viewManagerModel;
    private final GetCardPanelViewModel getCardPanelViewModel;
    private final BottomPanelViewModel bottomPanelViewModel;

    public SelectCardPresenter(ViewManagerModel viewManagerModel, CardButtonPanelViewModel cardButtonPanelViewModel,
                               GetCardPanelViewModel getCardPanelViewModel, BottomPanelViewModel bottomPanelViewModel){
        this.viewManagerModel = viewManagerModel;
        this.cardButtonPanelViewModel = cardButtonPanelViewModel;
        this.bottomPanelViewModel = bottomPanelViewModel;
        this.getCardPanelViewModel = getCardPanelViewModel;
    }

    @Override
    public void prepareSelectCardView(SelectCardOutputData selectCardOutputData) {
        CardButtonPanelState cardButtonPanelState = cardButtonPanelViewModel.getState();
        cardButtonPanelState.setButtonindexHighlighted(selectCardOutputData.getButton_index());

        cardButtonPanelState.setOneCardSelected(true);
        //Player is not allowed to LeftShift/Rightshift after a card is selected.
        cardButtonPanelState.setLeftButtonEnabled(false);
        cardButtonPanelState.setRightButtonEnabled(false);
//        cardButtonPanelState.setButton1enabled(false);
//        cardButtonPanelState.setButton2enabled(false);
//        cardButtonPanelState.setButton3enabled(false);

        cardButtonPanelViewModel.setState(cardButtonPanelState);
        cardButtonPanelViewModel.firePropertyChanged();

        GetCardPanelState getCardPanelState = getCardPanelViewModel.getState();
        getCardPanelState.setUndoEnabled(true);
        getCardPanelViewModel.setState(getCardPanelState);
        getCardPanelViewModel.firePropertyChanged();

        BottomPanelState bottomPanelState = bottomPanelViewModel.getState();
        bottomPanelState.setConfirmButtonEnabled(true);
        bottomPanelViewModel.setState(bottomPanelState);
        bottomPanelViewModel.firePropertyChanged();

        viewManagerModel.setActiveView("Initialized");
        viewManagerModel.firePropertyChanged();
    }
}
