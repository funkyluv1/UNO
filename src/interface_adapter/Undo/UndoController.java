package interface_adapter.Undo;

import use_case.Undo.UndoInputData;
import use_case.Undo.UndoInputDataBoundary;

public class UndoController {
    final UndoInputDataBoundary undoInteractor;

    public UndoController(UndoInputDataBoundary undoInteractor) {
        this.undoInteractor = undoInteractor;
    }

    public void execute(UndoInputData inputData){
        undoInteractor.execute(inputData);
    }
}