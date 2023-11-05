package interface_adapter.Initiation;

import use_case.initiation.InitiationOutputBoundary;

public class InitiationPresenter implements InitiationOutputBoundary {
    private InitiationViewModel initiationViewModel;
    public Game game;


    @Override

    public InitiationPresenter(InitiationViewModel initiationViewModel){
        this.initiationViewModel = initiationViewModel;
    }

    public void prepareNewGameView(InitiationOutputData initiationOutputData) {
        InitiationState initiationState = initiationViewModel.getState();
        initiationState.set_game(game);
        this.initiationViewModel.set_State(initiationState);
        initiationViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(initiationViewModel.getViewName());
        viewManagerModel.firePropertyChanged();


    }
}