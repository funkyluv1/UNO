package interface_adapter.Undo;

import use_case.Undo.UndoInputData;
import use_case.Undo.UndoInputDataBoundary;

public class UndoController {
    final UndoInputDataBoundary UndoInteractor;

    public UndoController(UndoInputDataBoundary UndoInteractor) {
        this.UndoInteractor = UndoInteractor;
    }

    public void execute(UndoInputData inputData){
        UndoInteractor.execute(inputData);
    }
}