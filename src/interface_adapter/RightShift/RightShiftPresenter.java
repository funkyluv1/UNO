package interface_adapter.RightShift;

import interface_adapter.Initialized.CardButtonPanelState;
import interface_adapter.Initialized.CardButtonPanelViewModel;
import interface_adapter.ViewManagerModel;
import use_case.RightShift.RightShiftOutputData;
import use_case.RightShift.RightShiftOutputDataBoundary;

public class RightShiftPresenter implements RightShiftOutputDataBoundary {
    private final CardButtonPanelViewModel cardButtonPanelViewModel;
    private final ViewManagerModel viewManagerModel;

    public RightShiftPresenter(ViewManagerModel viewManagerModel, CardButtonPanelViewModel cardButtonPanelViewModel){
        this.viewManagerModel = viewManagerModel;
        this.cardButtonPanelViewModel = cardButtonPanelViewModel;
    }

    @Override
    public void prepareSuccessView(RightShiftOutputData rightShiftOutputData) {
        CardButtonPanelState cardButtonPanelState = cardButtonPanelViewModel.getState();

        cardButtonPanelState.setRightButtonEnabled(rightShiftOutputData.getRightShiftActive());
        cardButtonPanelState.setLeftButtonEnabled(true);
        cardButtonPanelState.setDisplayNumCardsFirstIndex(cardButtonPanelState.getdisplayCardsFirstIndex() + 1);

        cardButtonPanelViewModel.setState(cardButtonPanelState);
        cardButtonPanelViewModel.firePropertyChanged();

        viewManagerModel.setActiveView("Initialized");
        viewManagerModel.firePropertyChanged();
    }
}
