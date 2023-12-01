package interface_adapter.RightShift;

import interface_adapter.Initialized.CardButtonPanelState;
import interface_adapter.Initialized.CardButtonPanelViewModel;
import interface_adapter.ViewManagerModel;
import use_case.LeftShift.LeftShiftOutputData;
import use_case.LeftShift.LeftShiftOutputDataBoundary;
import use_case.RightShift.RightShiftOutputData;
import use_case.RightShift.RightShiftOutputDataBoundary;

public class LeftShiftPresenter implements LeftShiftOutputDataBoundary {
    private final CardButtonPanelViewModel cardButtonPanelViewModel;
    private final ViewManagerModel viewManagerModel;

    public LeftShiftPresenter(ViewManagerModel viewManagerModel, CardButtonPanelViewModel cardButtonPanelViewModel){
        this.viewManagerModel = viewManagerModel;
        this.cardButtonPanelViewModel = cardButtonPanelViewModel;
    }

    @Override
    public void prepareSuccessView(LeftShiftOutputData leftShiftOutputData) {
        CardButtonPanelState cardButtonPanelState = cardButtonPanelViewModel.getState();

        cardButtonPanelState.setLeftButtonEnabled(leftShiftOutputData.getLeftShiftActive());
        cardButtonPanelState.setRightButtonEnabled(true);
        cardButtonPanelState.setDisplayNumCardsFirstIndex(cardButtonPanelState.getdisplayCardsFirstIndex() - 1);

        cardButtonPanelViewModel.setState(cardButtonPanelState);
        cardButtonPanelViewModel.firePropertyChanged();

        viewManagerModel.setActiveView("Initialized");
        viewManagerModel.firePropertyChanged();
    }
}
