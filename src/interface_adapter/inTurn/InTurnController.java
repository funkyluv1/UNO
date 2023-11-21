package interface_adapter.inTurn;

import use_case.PreTurn.PreTurnInputDataBoundary;
import use_case.inTurn.InTurnInputData;
import use_case.inTurn.InTurnInputDataBoundary;
import use_case.inTurn.InTurnInteractor;

public class InTurnController {


    final InTurnInputDataBoundary inTurnInteractor;
    public InTurnController(InTurnInputDataBoundary inTurnInteractor) {
        this.inTurnInteractor = inTurnInteractor;
    }

    public void execute(InTurnInputData inputData) {
        inTurnInteractor.execute(inputData);
    }

}
