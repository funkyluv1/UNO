package app;

import data_access.APIDataAccessObject;
import data_access.FileUserDataAccessObject;
import interface_adapter.Confirm.ConfirmController;
import interface_adapter.Confirm.ConfirmPresenter;
import interface_adapter.Initialized.*;
import interface_adapter.NextTurn.NextTurnController;
import interface_adapter.NextTurn.NextTurnPresenter;
import interface_adapter.ViewManagerModel;
import interface_adapter.postTurn.PostTurnPresenter;
import interface_adapter.postTurn.PostTurnViewModel;
import interface_adapter.preTurn.PreTurnPresenter;
import interface_adapter.preTurn.PreTurnViewModel;
import use_case.Confirm.*;
import use_case.DrawCards.DrawCardsDataAccessInterface;
import use_case.NextTurn.NextTurnDataAccessInterface;
import use_case.NextTurn.NextTurnInputDataBoundary;
import use_case.NextTurn.NextTurnInteractor;
import use_case.NextTurn.NextTurnOutputDataBoundary;
import use_case.PostTurn.PostTurnDataAccessInterface;
import use_case.PostTurn.PostTurnInputDataBoundary;
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
            PostTurnViewModel postTurnViewModel, PreTurnViewModel preTurnViewModel,
            APIDataAccessObject apiDataAccessObject,
            FileUserDataAccessObject fileUserDataAccessObject) {

        try {
            ConfirmController confirmController = createConfirmController(viewManagerModel, bottomPanelViewModel, cardButtonPanelViewModel,
                    funCardButtonPanelViewModel, fileUserDataAccessObject, getCardPanelViewModel);
            NextTurnController nextTurnController = createNextTurnController(viewManagerModel, playerPanelViewModel,
                    cardButtonPanelViewModel, funCardButtonPanelViewModel, postTurnViewModel, preTurnViewModel,apiDataAccessObject, fileUserDataAccessObject);
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
                                                               FunCardButtonPanelViewModel funCardButtonPanelViewModel,
                                                               PostTurnViewModel postTurnViewModel, PreTurnViewModel preTurnViewModel,
                                                               APIDataAccessObject apiDataAccessObject, FileUserDataAccessObject fileUserDataAccessObject) {
        PostTurnOutputDataBoundary postTurnPresenter = new PostTurnPresenter(viewManagerModel, postTurnViewModel);
        PostTurnInteractor postTurnInteractor = new PostTurnInteractor(postTurnPresenter, apiDataAccessObject, fileUserDataAccessObject);

        PreTurnOutputDataBoundary preTurnPresenter = new PreTurnPresenter(viewManagerModel, preTurnViewModel);
        PreTurnInteractor preTurnInteractor = new PreTurnInteractor(preTurnPresenter, apiDataAccessObject, fileUserDataAccessObject);

        FindPlayableCardsInterface findPlayableCards = new FindPlayableCards();

        NextTurnOutputDataBoundary nextTurnPresenter = new NextTurnPresenter(playerPanelViewModel, cardButtonPanelViewModel, viewManagerModel, funCardButtonPanelViewModel);
        NextTurnInputDataBoundary nextTurnInteractor = new NextTurnInteractor(fileUserDataAccessObject, nextTurnPresenter, findPlayableCards, postTurnInteractor, preTurnInteractor);
        return new NextTurnController(nextTurnInteractor);
    }


}
