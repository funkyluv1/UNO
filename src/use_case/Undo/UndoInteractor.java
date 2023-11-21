package use_case.Undo;

import static use_case.initiation.InitiationInteractor.game;

public class UndoInteractor implements UndoInputBoundary {

    final UndoOutputBoundary undoOutputDataBoundary;

    public UndoInteractor (UndoOutputBoundary undoOutputDataBoundary) {
        this.undoOutputDataBoundary = undoOutputDataBoundary;
    }
    @Override
    public void execute() {
        UndoOutputData undoOutputData = new UndoOutputData(game.getCurrSelectedCard());
        game.setCurrSelectedCard(null);
        undoOutputDataBoundary.prepareUndoView(undoOutputData);
    }
}
