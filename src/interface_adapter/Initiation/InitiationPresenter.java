package interface_adapter.Initiation;

import entities.Game;
import interface_adapter.Initialized.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.preTurn.PreTurnController;
import use_case.PreTurn.PreTurnInputData;
import use_case.PreTurn.PreTurnInputDataBoundary;
import use_case.initiation.InitiationOutputDataBoundary;
import use_case.initiation.InitiationOutputData;
import view.BottomPanel;
import view.FunCardButtonPanel;
import view.GetCardPanel;

import java.util.ArrayList;

import static use_case.initiation.InitiationInteractor.game;

public class InitiationPresenter implements InitiationOutputDataBoundary {
    private final PlayerPanelViewModel playerPanelViewModel;
    private final GetCardPanelViewModel getCardPanelViewModel;
    private final BottomPanelViewModel bottomPanelViewModel;
    private final CardButtonPanelViewModel cardButtonPanelViewModel;
    private final FunCardButtonPanelViewModel funCardButtonPanelViewModel;


    //TODO: missing one Panel for FunctionalCards buttons
    private final ViewManagerModel viewManagerModel;
    private final InitializedViewModel initializedViewModel;

    public InitiationPresenter(ViewManagerModel viewManagerModel,
                               CardButtonPanelViewModel cardButtonPanelViewModel, InitializedViewModel initializedViewModel,
                               GetCardPanelViewModel getCardPanelViewModel, BottomPanelViewModel bottomPanelViewModel,
                               PlayerPanelViewModel playerPanelViewModel, FunCardButtonPanelViewModel funCardButtonPanelViewModel){
        this.viewManagerModel = viewManagerModel;
        this.cardButtonPanelViewModel = cardButtonPanelViewModel;
        this.initializedViewModel = initializedViewModel;
        this.getCardPanelViewModel = getCardPanelViewModel;
        this.playerPanelViewModel = playerPanelViewModel;
        this.bottomPanelViewModel = bottomPanelViewModel;
        this.funCardButtonPanelViewModel = funCardButtonPanelViewModel;
    }

    @Override
    public void prepareNewGameView(InitiationOutputData initiationOutputData) {
        // On success, switch to the initialized view.
        PlayerPanelState playerPanelState = playerPanelViewModel.getState();
        playerPanelState.setPlayer(initiationOutputData.getPlayerNames());
        this.playerPanelViewModel.setState(playerPanelState);
        this.playerPanelViewModel.firePropertyChanged();

        GetCardPanelState getCardPanelState = getCardPanelViewModel.getState();
        getCardPanelState.setTopCard(game.getTopCard());
        this.getCardPanelViewModel.firePropertyChanged();

        CardButtonPanelState cardButtonPanelState = cardButtonPanelViewModel.getState();
        ArrayList<String> playerNames = initiationOutputData.getPlayerNames();

        cardButtonPanelState.set_players(playerNames);
        cardButtonPanelState.set_cards(initiationOutputData.getPlayerNumCards().get(playerNames.get(0)),
                initiationOutputData.getPlayerPlayableNumCards().get(playerNames.get(0)),
                initiationOutputData.getDisplayNumCardsIndexes().get(playerNames.get(0)));
        cardButtonPanelState.setDisplayNumCardsFirstIndex(0);
//        cardButtonPanelState.setButton3enabled(false);
//        cardButtonPanelState.setButton2enabled(false);
//        cardButtonPanelState.setButton1enabled(false);

        if (cardButtonPanelState.get_Number_Cards().size() > 3){
            cardButtonPanelState.setRightButtonEnabled(true);
            cardButtonPanelState.setLeftButtonEnabled(false);
        }
        else {
            cardButtonPanelState.setRightButtonEnabled(false);
            cardButtonPanelState.setLeftButtonEnabled(false);
        }
        this.cardButtonPanelViewModel.setState(cardButtonPanelState);
        this.cardButtonPanelViewModel.firePropertyChanged();

        FunCardButtonPanelState funCardButtonPanelState = funCardButtonPanelViewModel.getState();
        funCardButtonPanelState.set_cards(initiationOutputData.getPlayerFunCards().get(playerNames.get(0)),
                initiationOutputData.getPlayerPlayableFunCards().get(playerNames.get(0)),
                initiationOutputData.getDisplayNumCardsIndexes().get(playerNames.get(0)));
        funCardButtonPanelState.setDisplayNumCardsFirstIndex(0);
        funCardButtonPanelState.setRightButtonEnabled(true);
        funCardButtonPanelState.setLeftButtonEnabled(false);
        this.funCardButtonPanelViewModel.setState(funCardButtonPanelState);
        this.funCardButtonPanelViewModel.firePropertyChanged();

        BottomPanelState bottomPanelState = bottomPanelViewModel.getState();
        bottomPanelState.setConfirmButtonEnabled(false);
        bottomPanelState.setNextButtonEnabled(false);
        bottomPanelViewModel.setState(bottomPanelState);
        this.bottomPanelViewModel.firePropertyChanged();


        viewManagerModel.setActiveView(initializedViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}