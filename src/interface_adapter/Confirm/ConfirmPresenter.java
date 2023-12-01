package interface_adapter.Confirm;

import interface_adapter.Initialized.BottomPanelState;
import interface_adapter.Initialized.BottomPanelViewModel;
import interface_adapter.Initialized.CardButtonPanelState;
import interface_adapter.Initialized.CardButtonPanelViewModel;
import interface_adapter.ViewManagerModel;
import use_case.Confirm.ConfirmOutputDataBoundary;
import use_case.SelectCard.SelectCardOutputData;
import use_case.SelectCard.SelectCardOutputDataBoundary;

public class ConfirmPresenter implements ConfirmOutputDataBoundary {

    private final BottomPanelViewModel bottomPanelViewModel;
    private ViewManagerModel viewManagerModel;
    private final CardButtonPanelViewModel cardButtonPanelViewModel;

    public ConfirmPresenter(ViewManagerModel viewManagerModel, BottomPanelViewModel bottomPanelViewModel,
                            CardButtonPanelViewModel cardButtonPanelViewModel){
        this.viewManagerModel = viewManagerModel;
        this.bottomPanelViewModel = bottomPanelViewModel;
        this.cardButtonPanelViewModel = cardButtonPanelViewModel;
    }


    @Override
    public void prepareSuccessView() {
        BottomPanelState bottomPanelState = bottomPanelViewModel.getState();
        bottomPanelState.setConfirmButtonEnabled(false);
        bottomPanelViewModel.setState(bottomPanelState);
        bottomPanelViewModel.firePropertyChanged();

        CardButtonPanelState cardButtonPanelState = cardButtonPanelViewModel.getState();
        cardButtonPanelState.setOneCardSelected(true);
        cardButtonPanelViewModel.setState(cardButtonPanelState);
        cardButtonPanelViewModel.firePropertyChanged();

        viewManagerModel.setActiveView("Initialized");
        viewManagerModel.firePropertyChanged();
    }

}
