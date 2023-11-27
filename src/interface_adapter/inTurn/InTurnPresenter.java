package interface_adapter.inTurn;

import use_case.SelectCard.SelectCardOutputData;
import use_case.SelectCard.SelectCardOutputDataBoundary;
import use_case.Undo.UndoOutputDataBoundary;

public class InTurnPresenter implements SelectCardOutputDataBoundary, UndoOutputDataBoundary {
    @Override
    public void prepareSelectCardView(SelectCardOutputData selectCardOutputData) {
        // TODO: implement me
    }

    @Override
    public void prepareUndoView() {
        // TODO: implement me
    }
}
