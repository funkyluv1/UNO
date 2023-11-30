package interface_adapter.Undo;

import interface_adapter.Initialized.CardButtonPanelViewModel;
import interface_adapter.ViewManagerModel;
import use_case.Undo.UndoOutputData;
import use_case.Undo.UndoOutputDataBoundary;

public class UndoPresenter implements UndoOutputDataBoundary {
    private CardButtonPanelViewModel cardButtonPanelViewModel;
    private ViewManagerModel viewManagerModel;

    private UndoOutputData undoOutputData;

    public UndoPresenter() {
        this.undoOutputData = undoOutputData;
        this.viewManagerModel = viewManagerModel;
        this.cardButtonPanelViewModel = cardButtonPanelViewModel;
    }

    @Override
    public void prepareUndoView(UndoOutputData undoOutputData) {

    }
}
