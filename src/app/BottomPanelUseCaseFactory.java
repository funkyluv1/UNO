package app;

import data_access.APIDataAccessObject;
import data_access.FileUserDataAccessObject;
import interface_adapter.Confirm.ConfirmController;
import interface_adapter.Confirm.ConfirmPresenter;
import interface_adapter.Initialized.*;
import interface_adapter.NextTurn.NextTurnController;
import interface_adapter.NextTurn.NextTurnPresenter;
import interface_adapter.ViewManagerModel;
import use_case.Confirm.*;
import use_case.DrawCards.DrawCardsDataAccessInterface;
import use_case.NextTurn.NextTurnDataAccessInterface;
import use_case.NextTurn.NextTurnInputDataBoundary;
import use_case.NextTurn.NextTurnInteractor;
import use_case.NextTurn.NextTurnOutputDataBoundary;
import use_case.PostTurn.PostTurnDataAccessInterface;
import use_case.PostTurn.PostTurnInteractor;
import use_case.PostTurn.PostTurnOutputDataBoundary;
import use_case.PreTurn.*;
import view.BottomPanel;

import javax.swing.*;
import java.io.IOException;

public class BottomPanelUseCaseFactory {
    private BottomPanelUseCaseFactory() {}
    public static BottomPanel create(
            ViewManagerModel viewManagerModel,
            BottomPanelViewModel bottomPanelViewModel,
            CardButtonPanelViewModel cardButtonPanelViewModel,
            FunCardButtonPanelViewModel funCardButtonPanelViewModel,
            GetCardPanelViewModel getCardPanelViewModel,
            PlayerPanelViewModel playerPanelViewModel,
            FileUserDataAccessObject dataAccessInterface,
            APIDataAccessObject apiDataAccessObject) {

        try {
            ConfirmController confirmController = createConfirmController(viewManagerModel, bottomPanelViewModel, cardButtonPanelViewModel,
                    funCardButtonPanelViewModel, dataAccessInterface, getCardPanelViewModel);
            NextTurnController nextTurnController = createNextTurnController(viewManagerModel, playerPanelViewModel,
                    cardButtonPanelViewModel, funCardButtonPanelViewModel, bottomPanelViewModel, dataAccessInterface, apiDataAccessObject, dataAccessInterface, dataAccessInterface, getCardPanelViewModel);
            return new BottomPanel(bottomPanelViewModel, confirmController, nextTurnController);
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static ConfirmController createConfirmController(ViewManagerModel viewManagerModel, BottomPanelViewModel bottomPanelViewModel,
                                                             CardButtonPanelViewModel cardButtonPanelViewModel, FunCardButtonPanelViewModel funCardButtonPanelViewModel,
                                                             ConfirmDataAccessInterface confirmDataAccessInterface, GetCardPanelViewModel getCardPanelViewModel) throws IOException {

        ConfirmOutputDataBoundary confirmOutputDataBoundary = new ConfirmPresenter(viewManagerModel, bottomPanelViewModel, cardButtonPanelViewModel, funCardButtonPanelViewModel, getCardPanelViewModel);

        ConfirmInputDataBoundary confirmInputDataBoundary = new ConfirmInteractor(confirmOutputDataBoundary, confirmDataAccessInterface);

        return new ConfirmController(confirmInputDataBoundary);
    }

    private static NextTurnController createNextTurnController(ViewManagerModel viewManagerModel, PlayerPanelViewModel playerPanelViewModel, CardButtonPanelViewModel cardButtonPanelViewModel,
                                                               FunCardButtonPanelViewModel funCardButtonPanelViewModel, BottomPanelViewModel bottomPanelViewModel,
                                                               NextTurnDataAccessInterface nextTurnDataAccessInterface,
                                                               DrawCardsDataAccessInterface drawCardsDataAccessInterface,
                                                               PostTurnDataAccessInterface postTurnDataAccessInterface, PreTurnDataAccessInterface preTurnDataAccessInterface,
                                                               GetCardPanelViewModel getCardPanelViewModel) {
        PostTurnInteractor postTurnInteractor = new PostTurnInteractor(drawCardsDataAccessInterface, postTurnDataAccessInterface);

        PreTurnInteractor preTurnInteractor = new PreTurnInteractor(drawCardsDataAccessInterface, preTurnDataAccessInterface);

        FindPlayableCardsInterface findPlayableCards = new FindPlayableCards();

        NextTurnOutputDataBoundary nextTurnPresenter = new NextTurnPresenter(playerPanelViewModel, cardButtonPanelViewModel, viewManagerModel, funCardButtonPanelViewModel, bottomPanelViewModel, getCardPanelViewModel);
        NextTurnInputDataBoundary nextTurnInteractor = new NextTurnInteractor(nextTurnDataAccessInterface, nextTurnPresenter, findPlayableCards, postTurnInteractor, preTurnInteractor);
        return new NextTurnController(nextTurnInteractor);
    }


}
