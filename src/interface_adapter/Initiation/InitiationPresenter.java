package interface_adapter.Initiation;

import entities.Game;
import interface_adapter.Initialized.InitializedState;
import interface_adapter.Initialized.InitializedViewModel;
import interface_adapter.ViewManagerModel;
import use_case.initiation.InitiationOutputBoundary;
import use_case.initiation.InitiationOutputData;

public class InitiationPresenter implements InitiationOutputBoundary {
    private final InitiationViewModel initiationViewModel;

    private final InitializedViewModel initializedViewModel;

    private ViewManagerModel viewManagerModel;

    public InitiationPresenter(ViewManagerModel viewManagerModel,InitiationViewModel initiationViewModel){
        this.viewManagerModel = viewManagerModel;
        this.initiationViewModel = initiationViewModel;
    }

    @Override
    public void prepareNewGameView(InitiationOutputData initiationOutputData) {
        // On success, switch to the initialized view.

        InitializedState initializedState = initializedViewModel.getState();
        initializedState.set_game(initiationOutputData.getGame());
        this.initializedViewModel.setState(initializedState);
        this.initializedViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(initializedViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}