package interface_adapter.RightShift;

import interface_adapter.Initialized.CardButtonPanelViewModel;
import interface_adapter.ViewManagerModel;
import use_case.RightShift.RightShiftOutputData;
import use_case.RightShift.RightShiftOutputDataBoundary;

public class RightShiftPresenter implements RightShiftOutputDataBoundary {
    public RightShiftPresenter(ViewManagerModel viewManagerModel, CardButtonPanelViewModel cardButtonPanelViewModel) {
    }

    @Override
    public void prepareSuccessView(RightShiftOutputData rightShiftOutputData) {

    }
}
