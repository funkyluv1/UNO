package interface_adapter.Confirm;

import interface_adapter.Initialized.*;
import interface_adapter.ViewManagerModel;
import use_case.Confirm.ConfirmOutputDataBoundary;
import use_case.SelectCard.SelectCardOutputData;
import use_case.SelectCard.SelectCardOutputDataBoundary;

public class ConfirmPresenter implements ConfirmOutputDataBoundary {

    private final BottomPanelViewModel bottomPanelViewModel;
    private ViewManagerModel viewManagerModel;
    private final CardButtonPanelViewModel cardButtonPanelViewModel;
    private final FunCardButtonPanelViewModel funCardButtonPanelViewModel;

    public ConfirmPresenter(ViewManagerModel viewManagerModel, BottomPanelViewModel bottomPanelViewModel,
                            CardButtonPanelViewModel cardButtonPanelViewModel, FunCardButtonPanelViewModel funCardButtonPanelViewModel){
        this.viewManagerModel = viewManagerModel;
        this.bottomPanelViewModel = bottomPanelViewModel;
        this.cardButtonPanelViewModel = cardButtonPanelViewModel;
        this.funCardButtonPanelViewModel = funCardButtonPanelViewModel;
    }

    @Override
    public void prepareSuccessView() {
        BottomPanelState bottomPanelState = bottomPanelViewModel.getState();
        bottomPanelState.setConfirmButtonEnabled(false);
        bottomPanelState.setNextButtonEnabled(true);
        bottomPanelViewModel.setState(bottomPanelState);
        bottomPanelViewModel.firePropertyChanged();

        CardButtonPanelState cardButtonPanelState = cardButtonPanelViewModel.getState();
        cardButtonPanelState.setOneCardSelected(true);
        cardButtonPanelViewModel.setState(cardButtonPanelState);
        cardButtonPanelViewModel.firePropertyChanged();

        FunCardButtonPanelState funCardButtonPanelState = funCardButtonPanelViewModel.getState();
        funCardButtonPanelState.setAllButtonDisable(false);
        funCardButtonPanelViewModel.setState(funCardButtonPanelState);
        funCardButtonPanelViewModel.firePropertyChanged();

        viewManagerModel.setActiveView("Initialized");
        viewManagerModel.firePropertyChanged();
    }

}
