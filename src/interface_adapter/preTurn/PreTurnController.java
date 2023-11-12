package interface_adapter.preTurn;

import use_case.PreTurn.PreTurnInputData;
import use_case.PreTurn.PreTurnInputDataBoundary;

public class PreTurnController {
    final PreTurnInputDataBoundary preTurnInteractor;

    public PreTurnController(PreTurnInputDataBoundary preTurnInteractor) {
        this.preTurnInteractor = preTurnInteractor;
    }

    public void execute(PreTurnInputData inputData){
        preTurnInteractor.execute(inputData);
    }
}
