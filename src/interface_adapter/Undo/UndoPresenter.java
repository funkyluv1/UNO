package interface_adapter.Undo;

import interface_adapter.Initialized.CardButtonPanelState;
import interface_adapter.Initialized.CardButtonPanelViewModel;
import interface_adapter.Initialized.GetCardPanelState;
import interface_adapter.Initialized.GetCardPanelViewModel;
import interface_adapter.ViewManagerModel;
import use_case.Undo.UndoOutputData;
import use_case.Undo.UndoOutputDataBoundary;
import view.CardButtonPanel;

public class UndoPresenter implements UndoOutputDataBoundary {
    final private GetCardPanelViewModel getCardPanelViewModel;
    final private CardButtonPanelViewModel cardButtonPanelViewModel;
    private ViewManagerModel viewManagerModel;

    public UndoPresenter(ViewManagerModel viewManagerModel, GetCardPanelViewModel getCardPanelViewModel,
                         CardButtonPanelViewModel cardButtonPanelViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.getCardPanelViewModel = getCardPanelViewModel;
        this.cardButtonPanelViewModel = cardButtonPanelViewModel;
    }

    @Override
    public void prepareUndoView(UndoOutputData undoOutputData) {
        GetCardPanelState state = getCardPanelViewModel.getState();
        state.setUndoEnabled(false);
        getCardPanelViewModel.setState(state);
        getCardPanelViewModel.firePropertyChanged();

        CardButtonPanelState cardButtonPanelState = cardButtonPanelViewModel.getState();
        cardButtonPanelState.setOneCardSelected(false);
        cardButtonPanelViewModel.setState(cardButtonPanelState);
        cardButtonPanelViewModel.firePropertyChanged();

        viewManagerModel.setActiveView("Initialized");
        viewManagerModel.firePropertyChanged();
    }
}