package interface_adapter.NextTurn;

import entities.card.FunctionalCard;
import entities.card.NumberCard;
import interface_adapter.Initialized.*;
import interface_adapter.ViewManagerModel;
import use_case.NextTurn.NextTurnOutputData;
import use_case.NextTurn.NextTurnOutputDataBoundary;

import java.util.ArrayList;

public class NextTurnPresenter implements NextTurnOutputDataBoundary {
    private final PlayerPanelViewModel playerPanelViewModel;

    private final CardButtonPanelViewModel cardButtonPanelViewModel;

    //还有functional card的viewmodel
    private final ViewManagerModel viewManagerModel;

    private final FunCardButtonPanelViewModel funCardButtonPanelViewModel;

    public NextTurnPresenter(PlayerPanelViewModel playerPanelViewModel, CardButtonPanelViewModel cardButtonPanelViewModel, ViewManagerModel viewManagerModel,
                             FunCardButtonPanelViewModel funCardButtonPanelViewModel){
        this.playerPanelViewModel = playerPanelViewModel;
        this.funCardButtonPanelViewModel = funCardButtonPanelViewModel;
        this.cardButtonPanelViewModel = cardButtonPanelViewModel;
        this.viewManagerModel = viewManagerModel;

    }

    public void prepare_view(NextTurnOutputData nextTurnOutputData){
        CardButtonPanelState cardButtonPanelState = cardButtonPanelViewModel.getState();
        PlayerPanelState playerPanelState = playerPanelViewModel.getState();
        FunCardButtonPanelState funCardButtonPanelState = funCardButtonPanelViewModel.getState();
        if (nextTurnOutputData.getPlayer_index() == 3){
            //change color
        }
        funCardButtonPanelState.set_cards(nextTurnOutputData.getFunctionalCards(),nextTurnOutputData.getPlayerplayablefuncards(), 0);
        cardButtonPanelState.set_cards(nextTurnOutputData.getnumcards(), nextTurnOutputData.getPlayerplayablenumcards(), 0);
        playerPanelState.setCurrent_player_index(nextTurnOutputData.getPlayer_index());
        this.playerPanelViewModel.setState(playerPanelState);
        this.playerPanelViewModel.firePropertyChanged();

        this.funCardButtonPanelViewModel.setState(funCardButtonPanelState);
        this.funCardButtonPanelViewModel.firePropertyChanged();

        this.cardButtonPanelViewModel.setState(cardButtonPanelState);
        this.cardButtonPanelViewModel.firePropertyChanged();


    }

}
