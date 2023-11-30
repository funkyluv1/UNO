package interface_adapter.Initiation;

import entities.Game;
import interface_adapter.Initialized.CardButtonPanelState;
import interface_adapter.Initialized.CardButtonPanelViewModel;
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
    private final CardButtonPanelViewModel cardButtonPanelViewModel;
    private ViewManagerModel viewManagerModel;
    private final InitializedViewModel initializedViewModel;

    public InitiationPresenter(ViewManagerModel viewManagerModel,InitiationViewModel initiationViewModel,
                               CardButtonPanelViewModel cardButtonPanelViewModel, InitializedViewModel initializedViewModel){
        this.viewManagerModel = viewManagerModel;
        this.initiationViewModel = initiationViewModel;
        this.cardButtonPanelViewModel = cardButtonPanelViewModel;
        this.initializedViewModel = initializedViewModel;
    }

    @Override
    public void prepareNewGameView(InitiationOutputData initiationOutputData) {
        // On success, switch to the initialized view.

        CardButtonPanelState cardButtonPanelState = cardButtonPanelViewModel.getState();
        cardButtonPanelState.set_players(initiationOutputData.getPlayerNames());
        cardButtonPanelState.set_cards(initiationOutputData.getPlayerNumCards(), initiationOutputData.getPlayerPlayableNumCards(),
                initiationOutputData.getPlayerFunCards(), initiationOutputData.getPlayerPlayableFunCards(), initiationOutputData.getDisplayNumCardsIndexes());

        this.cardButtonPanelViewModel.setState(cardButtonPanelState);
        this.cardButtonPanelViewModel.firePropertyChanged();

        //TODO: initiate all panel views

        viewManagerModel.setActiveView(initializedViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}