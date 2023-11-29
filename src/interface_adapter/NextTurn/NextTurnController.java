package interface_adapter.NextTurn;

import use_case.NextTurn.NextTurnInputData;
import use_case.NextTurn.NextTurnInputDataBoundary;

public class NextTurnController {
    int next_player_index;
    final NextTurnInputDataBoundary nextTurnInteractor;

    public NextTurnController(NextTurnInputDataBoundary nextTurnInteractor){
        this.nextTurnInteractor = nextTurnInteractor;
    }

    public void execute(int next_player_index){
        NextTurnInputData nextTurnInputData = new NextTurnInputData(next_player_index);
        nextTurnInteractor.execute(nextTurnInputData);
    }
}
