package interface_adapter.Initiation;

import use_case.initiation.InitiationOutputDataBoundary;

public class InitiationPresenterData implements InitiationOutputDataBoundary {
    private InitiationViewModel initiationViewModel;
    public Game game;


    @Override

    public InitiationPresenterData(InitiationViewModel initiationViewModel){
        this.initiationViewModel = initiationViewModel;
    }

    public void prepareNewGameView(InitiationOutputData initiationOutputData) {
        InitiationState initiationState = initiationViewModel.getState();
        initiationState.set_game(game); //Siwei: the parameter is not used
        this.initiationViewModel.set_State(initiationState);
        initiationViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(initiationViewModel.getViewName());
        viewManagerModel.firePropertyChanged();


    }
}