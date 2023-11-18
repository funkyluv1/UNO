package use_case.Undo;

import static use_case.initiation.InitiationInteractor.game;

public class UndoInteractor implements UndoInputDataBoundary {

    final UndoOutputDataBoundary undoOutputDataBoundary;

    public UndoInteractor (UndoOutputDataBoundary undoOutputDataBoundary) {
        this.undoOutputDataBoundary = undoOutputDataBoundary;
    }
    @Override
    public void execute() {
        game.setCurrSelectedCard(null);
        undoOutputDataBoundary.prepareUndoView();
    }
}
