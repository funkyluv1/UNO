package interface_adapter.Undo;

import interface_adapter.Initialized.GetCardPanelState;
import interface_adapter.Initialized.GetCardPanelViewModel;
import interface_adapter.ViewManagerModel;
import use_case.Undo.UndoOutputData;
import use_case.Undo.UndoOutputDataBoundary;

public class UndoPresenter implements UndoOutputDataBoundary {
    final private GetCardPanelViewModel getCardPanelViewModel;
    private ViewManagerModel viewManagerModel;

    public UndoPresenter(ViewManagerModel viewManagerModel, GetCardPanelViewModel getCardPanelViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.getCardPanelViewModel = getCardPanelViewModel;
    }

    @Override
    public void prepareUndoView(UndoOutputData undoOutputData) {
        GetCardPanelState state = getCardPanelViewModel.getState();
        state.setUndoEnabled(false);
        getCardPanelViewModel.setState(state);
        viewManagerModel.setActiveView(getCardPanelViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
