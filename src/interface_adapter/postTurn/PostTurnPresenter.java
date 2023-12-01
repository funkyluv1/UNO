package interface_adapter.postTurn;

import interface_adapter.ViewManagerModel;
import use_case.PostTurn.PostTurnOutputData;
import use_case.PostTurn.PostTurnOutputDataBoundary;

public class PostTurnPresenter implements PostTurnOutputDataBoundary {
    final private PostTurnViewModel postTurnViewModel;

    private ViewManagerModel viewManagerModel;

    public PostTurnPresenter(ViewManagerModel viewManagerModel, PostTurnViewModel postTurnViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.postTurnViewModel = postTurnViewModel;
    }
    //TODO: call the PreTurn of the next player after viewManagerModel.firePropertyChanged();
    // The next player is represented by the playerIndex; add one after each postTurn
    @Override
    public void preparePostTurnView(PostTurnOutputData outputData) {
        PostTurnState postTurnState = postTurnViewModel.getState();
        postTurnState.setFunctionalCards(outputData.getFunctionalCards());
        postTurnState.setNumberCards(outputData.getNumberCards());
        postTurnViewModel.setState(postTurnState);
        viewManagerModel.setActiveView(postTurnViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
