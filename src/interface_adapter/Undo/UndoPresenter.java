package interface_adapter.Undo;

import interface_adapter.Initialized.*;
import interface_adapter.ViewManagerModel;
import use_case.Undo.UndoOutputData;
import use_case.Undo.UndoOutputDataBoundary;
import view.CardButtonPanel;

public class UndoPresenter implements UndoOutputDataBoundary {
    final private GetCardPanelViewModel getCardPanelViewModel;
    final private CardButtonPanelViewModel cardButtonPanelViewModel;
    final private ViewManagerModel viewManagerModel;
    final private BottomPanelViewModel bottomPanelViewModel;

    public UndoPresenter(ViewManagerModel viewManagerModel, GetCardPanelViewModel getCardPanelViewModel,
                         CardButtonPanelViewModel cardButtonPanelViewModel, BottomPanelViewModel bottomPanelViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.getCardPanelViewModel = getCardPanelViewModel;
        this.cardButtonPanelViewModel = cardButtonPanelViewModel;
        this.bottomPanelViewModel = bottomPanelViewModel;
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

        BottomPanelState bottomState = bottomPanelViewModel.getState();
        bottomState.setConfirmButtonEnabled(false);
        bottomPanelViewModel.setState(bottomState);
    }
}