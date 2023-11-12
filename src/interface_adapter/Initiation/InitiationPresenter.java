package interface_adapter.Initiation;

import entities.Game;
import interface_adapter.Initialized.InitializedState;
import interface_adapter.Initialized.InitializedViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.preTurn.PreTurnController;
import use_case.PreTurn.PreTurnInputData;
import use_case.PreTurn.PreTurnInputDataBoundary;
import use_case.initiation.InitiationOutputDataBoundary;
import use_case.initiation.InitiationOutputData;

public class InitiationPresenter implements InitiationOutputDataBoundary {
    //TODO: This instance variable is not used, do we need to keep it?
    private final InitiationViewModel initiationViewModel;

    private final InitializedViewModel initializedViewModel;

    private ViewManagerModel viewManagerModel;

    public InitiationPresenter(ViewManagerModel viewManagerModel,InitiationViewModel initiationViewModel, InitializedViewModel initializedViewModel){
        this.viewManagerModel = viewManagerModel;
        this.initiationViewModel = initiationViewModel;
        this.initializedViewModel = initializedViewModel;
    }

    @Override
    public void prepareNewGameView(InitiationOutputData initiationOutputData, PreTurnInputDataBoundary preTurnInputDataBoundary) {
        // On success, switch to the initialized view.

        InitializedState initializedState = initializedViewModel.getState();
        initializedState.set_players(initiationOutputData.getPlayerNames());
        this.initializedViewModel.setState(initializedState);
        this.initializedViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(initializedViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

        PreTurnController preTurnController = new PreTurnController(preTurnInputDataBoundary);
        preTurnController.execute(new PreTurnInputData(initiationOutputData.getNumberCardsDeck(), 0));
    }
}