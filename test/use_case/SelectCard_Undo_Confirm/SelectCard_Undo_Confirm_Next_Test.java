package use_case.SelectCard_Undo_Confirm;

import app.*;
import data_access.APIDataAccessObject;
import data_access.FileUserDataAccessObject;
import entities.Game;
import entities.NumberCardsDeck.NumberCardsDeckCreator;
import entities.card.Card;
import entities.card.NumberCard;
import entities.card.NumberCardFactory;
import entities.player.AIPlayerFactory;
import entities.player.HumanPlayerFactory;
import interface_adapter.Confirm.ConfirmPresenter;
import interface_adapter.Initialized.*;
import interface_adapter.Initiation.InitiationPresenter;
import interface_adapter.Initiation.InitiationViewModel;
import interface_adapter.NextTurn.NextTurnPresenter;
import interface_adapter.SelectCard.SelectCardPresenter;
import interface_adapter.Undo.UndoPresenter;
import interface_adapter.ViewManagerModel;
import junit.framework.TestCase;
import org.junit.Test;
import use_case.Confirm.ConfirmInputData;
import use_case.Confirm.ConfirmInteractor;
import use_case.NextTurn.NextTurnInputData;
import use_case.NextTurn.NextTurnInteractor;
import use_case.PostTurn.PostTurnInteractor;
import use_case.PreTurn.FindPlayableCards;
import use_case.PreTurn.FindPlayableCardsInterface;
import use_case.PreTurn.PreTurnInteractor;
import use_case.SelectCard.SelectCardInputData;
import use_case.SelectCard.SelectCardInteractor;
import use_case.Undo.UndoInputData;
import use_case.Undo.UndoInteractor;
import use_case.initiation.InitiationInputData;
import use_case.initiation.InitiationInteractor;
import view.*;

import java.io.IOException;
import java.util.ArrayList;

public class SelectCard_Undo_Confirm_Next_Test extends TestCase {

    public void testExecute() throws IOException {
        successTest();
    }

    @Test
    void successTest() throws IOException {
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
        GetCardPanel getCardPanel = GetCardPanelUseCaseFactory.create(viewManagerModel, getCardPanelViewModel, cardButtonPanelViewModel,funCardButtonPanelViewModel, bottomPanelViewModel,apiDataAccessObject, userDataAccessObject);
        FunCardButtonPanel funCardButtonPanel = FunCardButtonPanelUseCaseFactory.create(viewManagerModel, cardButtonPanelViewModel, funCardButtonPanelViewModel, userDataAccessObject);
        CardButtonPanel cardButtonPanel = CardButtonPanelUseCaseFactory.create(viewManagerModel, funCardButtonPanelViewModel, cardButtonPanelViewModel, getCardPanelViewModel,bottomPanelViewModel,userDataAccessObject);

        InitializedViewModel initializedViewModel = new InitializedViewModel(cardButtonPanel, bottomPanel, playerPanel, getCardPanel, funCardButtonPanel);
        InitiationPresenter initiationPresenter = new InitiationPresenter(viewManagerModel, cardButtonPanelViewModel, initializedViewModel, getCardPanelViewModel, bottomPanelViewModel, playerPanelViewModel, funCardButtonPanelViewModel);

        InitiationViewModel initiationViewModel = new InitiationViewModel();
        InitiationInteractor initiationInteractor = new InitiationInteractor(userDataAccessObject, apiDataAccessObject, initiationPresenter, findPlayableCardsInterface);
        InitiationView initiationView = InitiationUseCaseFactory.create(viewManagerModel, initiationViewModel, cardButtonPanelViewModel,initializedViewModel, userDataAccessObject,  funCardButtonPanelViewModel,
                findPlayableCardsInterface, getCardPanelViewModel, bottomPanelViewModel, playerPanelViewModel);

        ArrayList<String> playerNames = new ArrayList<>();
        playerNames.add("a");
        playerNames.add("b");
        playerNames.add("c");
        playerNames.add("d");
        InitiationInputData initiationInputData = new InitiationInputData(playerNames, 0);
        initiationInteractor.execute(initiationInputData);

        ArrayList<NumberCard> numberCards = new ArrayList<>();
        for (int i=0; i<3; i++) {
            NumberCardFactory numberCardFactory = new NumberCardFactory(i, "R");
            numberCards.add(numberCardFactory.createCard());
        }

        int k = 1;
        Card chosenCard = numberCards.get(k);
        SelectCardInputData selectCardInputData = new SelectCardInputData(chosenCard, k);
        SelectCardPresenter selectCardPresenter = new SelectCardPresenter(viewManagerModel, cardButtonPanelViewModel, getCardPanelViewModel, bottomPanelViewModel);
        SelectCardInteractor selectCardInteractor = new SelectCardInteractor(selectCardPresenter);

        // =======================================================================

        Game game = Game.getInstance();
        game.setSkipped(false);
        game.setPlusN(0);
        while (game.getCurrentPlayerIndex() != 0){
            game.updateCurrentPlayerIndex();
        }

        // Test select card
        selectCardInteractor.execute(selectCardInputData);

        // card button panel
        CardButtonPanelState cardButtonPanelState1 = cardButtonPanelViewModel.getState();
        assertTrue(cardButtonPanelState1.getOneCardsSelected());

        // get card panel
        GetCardPanelState getCardPanelState1 = getCardPanelViewModel.getState();
        assertFalse(getCardPanelState1.isGetCardEnabled());
        assertTrue(getCardPanelState1.isUndoEnabled());

        // bottom panel
        BottomPanelState bottomPanelState1 = bottomPanelViewModel.getState();
        assertTrue(bottomPanelState1.getConfirmButtonEnabled());
        assertFalse(bottomPanelState1.getNextButtonEnabled());

        // =======================================================================

        UndoInputData undoInputData = new UndoInputData(numberCards.get(k));
        UndoPresenter undoPresenter = new UndoPresenter(viewManagerModel, getCardPanelViewModel, cardButtonPanelViewModel, funCardButtonPanelViewModel, bottomPanelViewModel);
        UndoInteractor undoInteractor = new UndoInteractor(undoPresenter, userDataAccessObject);

        // Test undo
        undoInteractor.execute(undoInputData);

        // card button panel
        CardButtonPanelState cardButtonPanelState2 = cardButtonPanelViewModel.getState();
        assertFalse(cardButtonPanelState2.getOneCardsSelected());

        // get card panel
        GetCardPanelState getCardPanelState2 = getCardPanelViewModel.getState();
        assertFalse(getCardPanelState2.isUndoEnabled());

        // bottom panel
        BottomPanelState bottomPanelState2 = bottomPanelViewModel.getState();
        assertFalse(bottomPanelState2.getConfirmButtonEnabled());
        assertFalse(bottomPanelState2.getNextButtonEnabled());

        // =======================================================================

        int currIndex = 0;
        ConfirmInputData confirmInputData = new ConfirmInputData(currIndex);
        ConfirmPresenter confirmPresenter = new ConfirmPresenter(viewManagerModel, bottomPanelViewModel, cardButtonPanelViewModel, funCardButtonPanelViewModel, getCardPanelViewModel);
        ConfirmInteractor confirmInteractor = new ConfirmInteractor(confirmPresenter, userDataAccessObject);

        // Test Confirm
        selectCardInteractor.execute(selectCardInputData);
        confirmInteractor.execute(confirmInputData);

        // card button panel
        CardButtonPanelState cardButtonPanelState3 = cardButtonPanelViewModel.getState();
        assertTrue(cardButtonPanelState3.getOneCardsSelected());

        // fun card panel
        FunCardButtonPanelState funCardButtonPanelState3 = funCardButtonPanelViewModel.getState();
        assertFalse(funCardButtonPanelState3.getAllButtonDisable());

        // get card panel
        GetCardPanelState getCardPanelState3 = getCardPanelViewModel.getState();
        assertFalse(getCardPanelState3.isGetCardEnabled());
        assertFalse(getCardPanelState3.isUndoEnabled());

        // bottom panel
        BottomPanelState bottomPanelState3 = bottomPanelViewModel.getState();
        assertFalse(bottomPanelState3.getConfirmButtonEnabled());
        assertTrue(bottomPanelState3.getNextButtonEnabled());

        // =======================================================================

        NextTurnInputData nextTurnInputData = new NextTurnInputData(currIndex);
        NextTurnPresenter nextTurnPresenter = new NextTurnPresenter(playerPanelViewModel, cardButtonPanelViewModel, viewManagerModel, funCardButtonPanelViewModel, bottomPanelViewModel, getCardPanelViewModel);
        NextTurnInteractor nextTurnInteractor = new NextTurnInteractor(userDataAccessObject, nextTurnPresenter,
                findPlayableCardsInterface, new PostTurnInteractor(apiDataAccessObject, userDataAccessObject), new PreTurnInteractor(apiDataAccessObject, userDataAccessObject));

        // Test next
        nextTurnInteractor.execute(nextTurnInputData);

        // player panel
        int nextIndex = (currIndex + 1) % playerNames.size();
        PlayerPanelState playerPanelState3 = playerPanelViewModel.getState();
        assertEquals(nextIndex, playerPanelState3.getCurrent_player_index());

        // card button panel
        CardButtonPanelState cardButtonPanelState4 = cardButtonPanelViewModel.getState();
        assertFalse(cardButtonPanelState4.getOneCardsSelected());

        // get card panel
        GetCardPanelState getCardPanelState4 = getCardPanelViewModel.getState();
        assertFalse(getCardPanelState4.isUndoEnabled());

        // bottom panel
        BottomPanelState bottomPanelState4 = bottomPanelViewModel.getState();
        assertFalse(bottomPanelState4.getConfirmButtonEnabled());
        assertFalse(bottomPanelState4.getNextButtonEnabled());
    }
}