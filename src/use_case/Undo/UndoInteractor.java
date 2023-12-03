package use_case.Undo;

import java.util.ArrayList;

import static use_case.initiation.InitiationInteractor.game;

public class UndoInteractor implements UndoInputDataBoundary {

    final UndoOutputDataBoundary undoOutputDataBoundary;
    final UndoDataAccessInterface undoDataAccessInterface;

    public UndoInteractor(UndoOutputDataBoundary undoOutputDataBoundary, UndoDataAccessInterface undoDataAccessInterface) {
        this.undoOutputDataBoundary = undoOutputDataBoundary;
        this.undoDataAccessInterface = undoDataAccessInterface;
    }

    @Override
    public void execute(UndoInputData undoInputData) {
        UndoOutputData undoOutputData = new UndoOutputData(game.getCurrSelectedNumberCard());
        game.setCurrSelectedNumberCard(null);
        game.setCurrSelectedFunCard(new ArrayList<>());
        undoDataAccessInterface.recordUnselectCard(undoInputData.getSelectedCard());
        undoOutputDataBoundary.prepareUndoView(undoOutputData);
    }

}