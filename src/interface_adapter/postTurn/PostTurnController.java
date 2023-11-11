package interface_adapter.postTurn;

import use_case.PostTurn.PostTurnInputData;
import use_case.PostTurn.PostTurnInputDataBoundary;
import use_case.PostTurn.PostTurnInteractor;
import use_case.PostTurn.PostTurnOutputDataBoundary;

public class PostTurnController {
    final PostTurnInputDataBoundary postTurnInteractor;

    public PostTurnController(PostTurnInputDataBoundary postTurnInteractor) {
        this.postTurnInteractor = postTurnInteractor;
    }

    public void execute(PostTurnInputData inputData){
        postTurnInteractor.execute(inputData);
    }
}
