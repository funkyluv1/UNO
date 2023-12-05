import use_case.NextTurn.NextTurnDataAccessInterface;
import use_case.NextTurn.NextTurnInputDataBoundary;
import use_case.NextTurn.NextTurnOutputDataBoundary;
import use_case.PostTurn.PostTurnInteractor;
import use_case.PreTurn.FindPlayableCardsInterface;
import use_case.PreTurn.PreTurnInteractor;

public class NextTurnInteractor implements NextTurnInputDataBoundary {
    final NextTurnDataAccessInterface fileUserDataAccessObject;
    final NextTurnOutputDataBoundary nextTurn_presenter;
    final FindPlayableCardsInterface findPlayableCardsInterface;
    final PostTurnInteractor postTurnInteractor;
    final PreTurnInteractor preTurnInteractor;

    public NextTurnInteractor(NextTurnDataAccessInterface fileUserDataAccessObject, NextTurnOutputDataBoundary nextTurn_presenter,
                              FindPlayableCardsInterface findPlayableCardsInterface, PostTurnInteractor postTurnInteractor,
                              PreTurnInteractor preTurnInteractor) {
        this.fileUserDataAccessObject = fileUserDataAccessObject;
        this.nextTurn_presenter = nextTurn_presenter;
        this.findPlayableCardsInterface = findPlayableCardsInterface;
        this.postTurnInteractor = postTurnInteractor;
        this.preTurnInteractor = preTurnInteractor;
    }
}