package use_case.Undo;

import interface_adapter.Initialized.CardButtonPanelViewModel;
import interface_adapter.ViewManagerModel;

public interface UndoOutputDataBoundary {
    void prepareUndoView(UndoOutputData undoOutputData);
}
