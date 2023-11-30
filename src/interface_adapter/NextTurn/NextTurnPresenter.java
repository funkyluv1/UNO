package interface_adapter.NextTurn;

import entities.card.FunctionalCard;
import entities.card.NumberCard;
import interface_adapter.Initialized.CardButtonPanelState;
import interface_adapter.Initialized.CardButtonPanelViewModel;
import interface_adapter.Initialized.PlayerPanelState;
import interface_adapter.Initialized.PlayerPanelViewModel;
import interface_adapter.ViewManagerModel;
import use_case.NextTurn.NextTurnOutputData;

import java.util.ArrayList;

public class NextTurnPresenter {
    private final PlayerPanelViewModel playerPanelViewModel;

    private final CardButtonPanelViewModel cardButtonPanelViewModel;

    //还有functional card的viewmodel
    private final ViewManagerModel viewManagerModel;

    public NextTurnPresenter(PlayerPanelViewModel playerPanelViewModel, CardButtonPanelViewModel cardButtonPanelViewModel, ViewManagerModel viewManagerModel){
        this.playerPanelViewModel = playerPanelViewModel;
        this.cardButtonPanelViewModel = cardButtonPanelViewModel;
        this.viewManagerModel = viewManagerModel;

    }

    public void prepare_view(NextTurnOutputData nextTurnOutputData){
        CardButtonPanelState cardButtonPanelState = cardButtonPanelViewModel.getState();
        PlayerPanelState playerPanelState = playerPanelViewModel.getState();
        //functional card view的state
//        cardButtonPanelState.set_players(nextTurnOutputData.getName());
        cardButtonPanelState.set_cards(nextTurnOutputData.getPlayerNumberCards(),nextTurnOutputData.getPlayerPlayableNumberCards(),
                nextTurnOutputData.getPlayerPlayableFuncrCards(),nextTurnOutputData.getPlayerPlayableFuncrCards());
        playerPanelState.setCurrent_player_index(nextTurnOutputData.getPlayer_index());
        this.playerPanelViewModel.setState(playerPanelState);
        this.playerPanelViewModel.firePropertyChanged();

        this.cardButtonPanelViewModel.setState(cardButtonPanelState);
        this.cardButtonPanelViewModel.firePropertyChanged();


    }

}
