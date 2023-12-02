package interface_adapter.RightShift;

import interface_adapter.Initialized.CardButtonPanelState;
import interface_adapter.Initialized.CardButtonPanelViewModel;
import interface_adapter.Initialized.FunCardButtonPanelState;
import interface_adapter.Initialized.FunCardButtonPanelViewModel;
import interface_adapter.ViewManagerModel;
import use_case.RightShift.RightShiftOutputData;
import use_case.RightShift.RightShiftOutputDataBoundary;

public class RightShiftPresenter implements RightShiftOutputDataBoundary {
    private final CardButtonPanelViewModel cardButtonPanelViewModel;
    private final ViewManagerModel viewManagerModel;
    private final FunCardButtonPanelViewModel funCardButtonPanelViewModel;

    public RightShiftPresenter(ViewManagerModel viewManagerModel, CardButtonPanelViewModel cardButtonPanelViewModel,
                               FunCardButtonPanelViewModel funCardButtonPanelViewModel){
        this.viewManagerModel = viewManagerModel;
        this.cardButtonPanelViewModel = cardButtonPanelViewModel;
        this.funCardButtonPanelViewModel = funCardButtonPanelViewModel;
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

    @Override
    public void prepareSuccessView(RightShiftOutputData rightShiftOutputData, boolean flag_for_func) {
        FunCardButtonPanelState funCardButtonPanelState = funCardButtonPanelViewModel.getState();

        funCardButtonPanelState.setRightButtonEnabled(rightShiftOutputData.getRightShiftActive());
        funCardButtonPanelState.setLeftButtonEnabled(true);
        funCardButtonPanelState.setDisplayNumCardsFirstIndex(funCardButtonPanelState.getdisplayCardsFirstIndex() + 1);

        funCardButtonPanelViewModel.setState(funCardButtonPanelState);
        funCardButtonPanelViewModel.firePropertyChanged();

        viewManagerModel.setActiveView("Initialized");
        viewManagerModel.firePropertyChanged();
    }
}
