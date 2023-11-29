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

    public ConfirmPresenter(ViewManagerModel viewManagerModel, BottomPanelViewModel bottomPanelViewModel){
        this.viewManagerModel = viewManagerModel;
        this.bottomPanelViewModel = bottomPanelViewModel;
    }

    @Override
    public void prepareSuccessView() {
        BottomPanelState bottomPanelState = bottomPanelViewModel.getState();

        bottomPanelState.setConfirmButtonEnabled(false);
        //TODO: disable CardButtonPanel's buttons; need an interface

        bottomPanelViewModel.setState(bottomPanelState);
        bottomPanelViewModel.firePropertyChanged();
    }

}
