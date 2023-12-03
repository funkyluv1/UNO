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

    private final BottomPanelViewModel bottomPanelViewModel;

    public NextTurnPresenter(PlayerPanelViewModel playerPanelViewModel, CardButtonPanelViewModel cardButtonPanelViewModel, ViewManagerModel viewManagerModel,
                             FunCardButtonPanelViewModel funCardButtonPanelViewModel, BottomPanelViewModel bottomPanelViewModel){
        this.playerPanelViewModel = playerPanelViewModel;
        this.funCardButtonPanelViewModel = funCardButtonPanelViewModel;
        this.cardButtonPanelViewModel = cardButtonPanelViewModel;
        this.viewManagerModel = viewManagerModel;
        this.bottomPanelViewModel = bottomPanelViewModel;
    }

    public void prepare_view(NextTurnOutputData nextTurnOutputData){
        CardButtonPanelState cardButtonPanelState = cardButtonPanelViewModel.getState();
        BottomPanelState bottomPanelState = bottomPanelViewModel.getState();
        PlayerPanelState playerPanelState = playerPanelViewModel.getState();
        FunCardButtonPanelState funCardButtonPanelState = funCardButtonPanelViewModel.getState();

        if (nextTurnOutputData.getPlayer_index() == 3){
            //change color
        }
        playerPanelState.setCurrent_player_index(nextTurnOutputData.getPlayer_index());
        this.playerPanelViewModel.setState(playerPanelState);
        this.playerPanelViewModel.firePropertyChanged();

        funCardButtonPanelState.set_cards(nextTurnOutputData.getFunctionalCards(),nextTurnOutputData.getPlayerplayablefuncards(), 0);
        funCardButtonPanelState.setAllButtonDisable(false);


        this.funCardButtonPanelViewModel.setState(funCardButtonPanelState);
        this.funCardButtonPanelViewModel.firePropertyChanged();

        cardButtonPanelState.set_cards(nextTurnOutputData.getnumcards(), nextTurnOutputData.getPlayerplayablenumcards(), 0);
        cardButtonPanelState.setOneCardSelected(false);
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

        bottomPanelState.setConfirmButtonEnabled(false);
        bottomPanelState.setNextButtonEnabled(false);
        bottomPanelViewModel.setState(bottomPanelState);
        this.bottomPanelViewModel.firePropertyChanged();
    }

}
