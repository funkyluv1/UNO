package interface_adapter.Initiation;

import use_case.initiation.InitiationOutputDataBoundary;
import entities.Game;
import interface_adapter.ViewManagerModel;

public class InitiationPresenter implements InitiationOutputDataBoundary {
    private InitiationViewModel initiationViewModel;

    private ViewManagerModel viewManagerModel;
    public Game game;


    @Override
    public InitiationPresenter(ViewManagerModel viewManagerModel,InitiationViewModel initiationViewModel){
        this.viewManagerModel = viewManagerModel;
        this.initiationViewModel = initiationViewModel;
    }

    @Override
    public void prepareNewGameView(InitiationOutputData initiationOutputData) {
        InitiationState initiationState = initiationViewModel.getState();
        initiationState.set_game(game); //Siwei: the parameter is not used
        this.initiationViewModel.set_State(initiationState);
        initiationViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(initiationViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}