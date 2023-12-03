package interface_adapter.LeftShift;

import interface_adapter.Initialized.CardButtonPanelState;
import interface_adapter.Initialized.CardButtonPanelViewModel;
import interface_adapter.Initialized.FunCardButtonPanelState;
import interface_adapter.Initialized.FunCardButtonPanelViewModel;
import interface_adapter.ViewManagerModel;
import use_case.LeftShift.LeftShiftOutputData;
import use_case.LeftShift.LeftShiftOutputDataBoundary;
import use_case.RightShift.RightShiftOutputData;
import use_case.RightShift.RightShiftOutputDataBoundary;

public class LeftShiftPresenter implements LeftShiftOutputDataBoundary {
    private final CardButtonPanelViewModel cardButtonPanelViewModel;
    private final ViewManagerModel viewManagerModel;
    private final  FunCardButtonPanelViewModel funCardButtonPanelViewModel;
    private ViewManagerModel viewManagerModel1;

    public LeftShiftPresenter(ViewManagerModel viewManagerModel, CardButtonPanelViewModel cardButtonPanelViewModel,
                              FunCardButtonPanelViewModel funCardButtonPanelViewModel){
        this.viewManagerModel = viewManagerModel;
        this.cardButtonPanelViewModel = cardButtonPanelViewModel;
        this.funCardButtonPanelViewModel = funCardButtonPanelViewModel;
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

    @Override
    public void prepareSuccessView(LeftShiftOutputData leftShiftOutputData, boolean boolean_indicating_funcCards) {
        FunCardButtonPanelState funCardButtonPanelState = funCardButtonPanelViewModel.getState();

        funCardButtonPanelState.setLeftButtonEnabled(leftShiftOutputData.getLeftShiftActive());
        funCardButtonPanelState.setRightButtonEnabled(true);
        funCardButtonPanelState.setDisplayNumCardsFirstIndex(funCardButtonPanelState.getdisplayCardsFirstIndex() - 1);

        funCardButtonPanelViewModel.setState(funCardButtonPanelState);
        funCardButtonPanelViewModel.firePropertyChanged();

        viewManagerModel.setActiveView("Initialized");
        viewManagerModel.firePropertyChanged();
    }
}
