package interface_adapter.Undo;

import interface_adapter.GetCard.GetCardViewModel;
import interface_adapter.Initialized.*;
import interface_adapter.ViewManagerModel;
import use_case.GetCard.GetCardOutputData;
import use_case.GetCard.GetCardOutputDataBoundary;
import use_case.Undo.UndoOutputData;
import use_case.Undo.UndoOutputDataBoundary;

public class UndoPresenter implements UndoOutputDataBoundary {

    private ViewManagerModel viewManagerModel;
    private GetCardPanelViewModel getCardPanelViewModel;
    private CardButtonPanelViewModel cardButtonPanelViewModel;
    private FunCardButtonPanelViewModel funCardButtonPanelViewModel;
    private BottomPanelViewModel bottomPanelViewModel;

    public UndoPresenter(ViewManagerModel viewManagerModel, GetCardPanelViewModel getCardPanelViewModel,
                            CardButtonPanelViewModel cardButtonPanelViewModel,
                         FunCardButtonPanelViewModel funCardButtonPanelViewModel, BottomPanelViewModel bottomPanelViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.getCardPanelViewModel = getCardPanelViewModel;
        this.funCardButtonPanelViewModel = funCardButtonPanelViewModel;
        this.cardButtonPanelViewModel = cardButtonPanelViewModel;
        this.bottomPanelViewModel = bottomPanelViewModel;
    }
    @Override
    public void prepareUndoView(UndoOutputData undoOutputData) {
        GetCardPanelState getCardPanelState = getCardPanelViewModel.getState();
        getCardPanelState.setUndoEnabled(false);
        getCardPanelViewModel.setState(getCardPanelState);
        getCardPanelViewModel.firePropertyChanged();

        CardButtonPanelState cardButtonPanelState = cardButtonPanelViewModel.getState();
        cardButtonPanelState.setOneCardSelected(false);
        if (cardButtonPanelState.getdisplayCardsFirstIndex() > 0){
            cardButtonPanelState.setLeftButtonEnabled(true);
        } else {cardButtonPanelState.setLeftButtonEnabled(false);}
        if (cardButtonPanelState.getdisplayCardsFirstIndex() + 3 >= cardButtonPanelState.get_Number_Cards().size()){
            cardButtonPanelState.setRightButtonEnabled(false);}
        else {cardButtonPanelState.setRightButtonEnabled(true);}
        cardButtonPanelViewModel.setState(cardButtonPanelState);
        cardButtonPanelViewModel.firePropertyChanged();

        FunCardButtonPanelState funCardButtonPanelState = funCardButtonPanelViewModel.getState();
        funCardButtonPanelState.setAllButtonDisable(false);
        funCardButtonPanelViewModel.setState(funCardButtonPanelState);
        funCardButtonPanelViewModel.firePropertyChanged();

        BottomPanelState bottomPanelState = bottomPanelViewModel.getState();
        bottomPanelState.setNextButtonEnabled(false);
        bottomPanelViewModel.setState(bottomPanelState);
        bottomPanelViewModel.firePropertyChanged();

        viewManagerModel.setActiveView("Initialized");
        viewManagerModel.firePropertyChanged();
    }
}
