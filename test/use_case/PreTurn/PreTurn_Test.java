package use_case.PreTurn;

import app.*;
import data_access.APIDataAccessObject;
import data_access.FileUserDataAccessObject;
import entities.Game;
import entities.NumberCardsDeck.NumberCardsDeckCreator;
import entities.card.*;
import entities.player.AIPlayerFactory;
import entities.player.HumanPlayerFactory;
import interface_adapter.Confirm.ConfirmPresenter;
import interface_adapter.Initialized.*;
import interface_adapter.Initiation.InitiationPresenter;
import interface_adapter.Initiation.InitiationViewModel;
import interface_adapter.NextTurn.NextTurnPresenter;
import interface_adapter.SelectCard.SelectCardPresenter;
import interface_adapter.SelectFuncCard.SelectFuncCardPresenter;
import interface_adapter.ViewManagerModel;
import junit.framework.TestCase;
import use_case.Confirm.ConfirmInputData;
import use_case.Confirm.ConfirmInteractor;
import use_case.NextTurn.NextTurnInputData;
import use_case.NextTurn.NextTurnInteractor;
import use_case.PostTurn.PostTurnInteractor;
import use_case.SelectCard.SelectCardInputData;
import use_case.SelectCard.SelectCardInteractor;
import use_case.SelectFuncCard.SelectFuncCardInputData;
import use_case.SelectFuncCard.SelectFuncCardInteractor;
import use_case.initiation.InitiationInputData;
import use_case.initiation.InitiationInteractor;
import view.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class PreTurn_Test extends TestCase {
    public void testExecute() throws IOException {
        testNextHasPlusCard();
    }

    public void testNextHasPlusCard() throws IOException {
        File file = new File("users.csv");
        PrintWriter printWriter = new PrintWriter(file);
        printWriter.print("");
        printWriter.close();

        FileUserDataAccessObject userDataAccessObject = new FileUserDataAccessObject("./users.csv", new AIPlayerFactory(), new HumanPlayerFactory(), new NumberCardsDeckCreator());
        APIDataAccessObject apiDataAccessObject = apiDataAccessObject = new APIDataAccessObject();
        FindPlayableCardsInterface findPlayableCardsInterface = new FindPlayableCards();

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        CardButtonPanelViewModel cardButtonPanelViewModel = new CardButtonPanelViewModel();
        GetCardPanelViewModel getCardPanelViewModel = new GetCardPanelViewModel();
        BottomPanelViewModel bottomPanelViewModel = new BottomPanelViewModel();
        PlayerPanelViewModel playerPanelViewModel = new PlayerPanelViewModel();
        FunCardButtonPanelViewModel funCardButtonPanelViewModel = new FunCardButtonPanelViewModel();

        BottomPanel bottomPanel = BottomPanelUseCaseFactory.create(viewManagerModel, bottomPanelViewModel, cardButtonPanelViewModel, funCardButtonPanelViewModel, getCardPanelViewModel,
                playerPanelViewModel, userDataAccessObject, apiDataAccessObject);
        PlayerPanel playerPanel = PlayerPanelUseCaseFactory.create(viewManagerModel, playerPanelViewModel);
        GetCardPanel getCardPanel = GetCardPanelUseCaseFactory.create(viewManagerModel, getCardPanelViewModel, cardButtonPanelViewModel, funCardButtonPanelViewModel, bottomPanelViewModel, apiDataAccessObject, userDataAccessObject);
        FunCardButtonPanel funCardButtonPanel = FunCardButtonPanelUseCaseFactory.create(viewManagerModel, cardButtonPanelViewModel, funCardButtonPanelViewModel, userDataAccessObject);
        CardButtonPanel cardButtonPanel = CardButtonPanelUseCaseFactory.create(viewManagerModel, funCardButtonPanelViewModel, cardButtonPanelViewModel, getCardPanelViewModel, bottomPanelViewModel, userDataAccessObject);

        InitializedViewModel initializedViewModel = new InitializedViewModel(cardButtonPanel, bottomPanel, playerPanel, getCardPanel, funCardButtonPanel);
        InitiationPresenter initiationPresenter = new InitiationPresenter(viewManagerModel, cardButtonPanelViewModel, initializedViewModel, getCardPanelViewModel, bottomPanelViewModel, playerPanelViewModel, funCardButtonPanelViewModel);

        InitiationViewModel initiationViewModel = new InitiationViewModel();
        InitiationInteractor initiationInteractor = new InitiationInteractor(userDataAccessObject, apiDataAccessObject, initiationPresenter, findPlayableCardsInterface);
        InitiationView initiationView = InitiationUseCaseFactory.create(viewManagerModel, initiationViewModel, cardButtonPanelViewModel, initializedViewModel, userDataAccessObject, funCardButtonPanelViewModel,
                findPlayableCardsInterface, getCardPanelViewModel, bottomPanelViewModel, playerPanelViewModel);

        ArrayList<String> playerNames = new ArrayList<>();
        playerNames.add("a");
        playerNames.add("b");
        playerNames.add("c");
        playerNames.add("d");
        InitiationInputData initiationInputData = new InitiationInputData(playerNames, 0);
        initiationInteractor.execute(initiationInputData);

        ArrayList<NumberCard> numberCards = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            NumberCardFactory numberCardFactory = new NumberCardFactory(i, "R");
            numberCards.add(numberCardFactory.createCard());
        }

        int k = 1;
        Card chosenCard = numberCards.get(k);
        SelectCardInputData selectCardInputData = new SelectCardInputData(chosenCard, k);
        SelectCardPresenter selectCardPresenter = new SelectCardPresenter(viewManagerModel, cardButtonPanelViewModel, getCardPanelViewModel, bottomPanelViewModel);
        SelectCardInteractor selectCardInteractor = new SelectCardInteractor(selectCardPresenter);
        selectCardInteractor.execute(selectCardInputData);

        // =======================================================================

        Game game = Game.getInstance();
        game.setSkipped(false);
        game.setPlusN(0);
        while (game.getCurrentPlayerIndex() != 0){
            game.updateCurrentPlayerIndex();
        }

        ArrayList<FunctionalCard> functionalCards = new ArrayList<>();
        functionalCards.add(new PlusTwoCard());

        ArrayList<FunctionalCard> selectedFuncCards = new ArrayList<>();
        FunctionalCard newCard = functionalCards.get(0);
        SelectFuncCardInputData selectFuncCardInputData = new SelectFuncCardInputData(newCard, selectedFuncCards, 0);
        SelectFuncCardPresenter selectFuncCardPresenter = new SelectFuncCardPresenter(viewManagerModel, funCardButtonPanelViewModel);
        SelectFuncCardInteractor selectFuncCardInteractor = new SelectFuncCardInteractor(selectFuncCardPresenter);
        selectFuncCardInteractor.execute(selectFuncCardInputData);

        int currIndex = 0;
        ConfirmInputData confirmInputData = new ConfirmInputData(currIndex);
        ConfirmPresenter confirmPresenter = new ConfirmPresenter(viewManagerModel, bottomPanelViewModel, cardButtonPanelViewModel, funCardButtonPanelViewModel, getCardPanelViewModel);
        ConfirmInteractor confirmInteractor = new ConfirmInteractor(confirmPresenter, userDataAccessObject);
        confirmInteractor.execute(confirmInputData);

        // =======================================================================

        // if the player has plus cards
        int nextIndex = (currIndex + 1) % playerNames.size();
        userDataAccessObject.recordRoundChange(playerNames.get(nextIndex), new PlusFourCard());

        NextTurnInputData nextTurnInputData = new NextTurnInputData(currIndex);
        NextTurnPresenter nextTurnPresenter = new NextTurnPresenter(playerPanelViewModel, cardButtonPanelViewModel, viewManagerModel, funCardButtonPanelViewModel, bottomPanelViewModel, getCardPanelViewModel);
        NextTurnInteractor nextTurnInteractor = new NextTurnInteractor(userDataAccessObject, nextTurnPresenter,
                findPlayableCardsInterface, new PostTurnInteractor(apiDataAccessObject, userDataAccessObject), new PreTurnInteractor(apiDataAccessObject, userDataAccessObject));

        nextTurnInteractor.execute(nextTurnInputData);

        // Test preturn interactor

        // game object
        assertEquals(2, game.getCurrentPlayerIndex());
        assertFalse(game.getSkipped());
        assertEquals(game.getDrawCard(), game.getDrawCard());

    }
}