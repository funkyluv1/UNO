package interface_adapter.Initiation;

import entities.Game;
import interface_adapter.ViewManagerModel;
import use_case.initiation.InitiationOutputBoundary;

public class InitiationPresenter implements InitiationOutputBoundary {
    private InitiationViewModel initiationViewModel;

    private ViewManagerModel viewManagerModel;
    public Game game;

    public InitiationPresenter(ViewManagerModel viewManagerModel,InitiationViewModel initiationViewModel){
        this.viewManagerModel = viewManagerModel;
        this.initiationViewModel = initiationViewModel;
    }

    @Override
    public void prepareNewGameView(InitiationOutputData initiationOutputData) {
        InitiationState initiationState = initiationViewModel.getState();
        initiationState.set_game(game);
        this.initiationViewModel.set_State(initiationState);
        initiationViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(initiationViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}