package interface_adapter.Initiation;

import entities.Game;
import interface_adapter.Initialized.InitializedState;
import interface_adapter.Initialized.InitializedViewModel;
import interface_adapter.ViewManagerModel;
import use_case.initiation.InitiationOutputDataBoundary;
import use_case.initiation.InitiationOutputData;

public class InitiationPresenter implements InitiationOutputDataBoundary {
    private final InitiationViewModel initiationViewModel;

    private final InitializedViewModel initializedViewModel;

    private ViewManagerModel viewManagerModel;

    public InitiationPresenter(ViewManagerModel viewManagerModel,InitiationViewModel initiationViewModel, InitializedViewModel initializedViewModel){
        this.viewManagerModel = viewManagerModel;
        this.initiationViewModel = initiationViewModel;
        this.initializedViewModel = initializedViewModel;
    }

    @Override
    public void prepareNewGameView(InitiationOutputData initiationOutputData) {
        // On success, switch to the initialized view.

        InitializedState initializedState = initializedViewModel.getState();
        initializedState.set_players(initiationOutputData.getPlayerNames());
        this.initializedViewModel.setState(initializedState);
        this.initializedViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(initializedViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}