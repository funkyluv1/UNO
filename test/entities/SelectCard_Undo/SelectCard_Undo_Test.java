package entities.SelectCard_Undo;

import app.*;
import data_access.APIDataAccessObject;
import data_access.FileUserDataAccessObject;
import entities.NumberCardsDeck.NumberCardsDeckCreator;
import entities.card.NumberCard;
import entities.card.NumberCardFactory;
import entities.player.AIPlayerFactory;
import entities.player.HumanPlayer;
import entities.player.HumanPlayerFactory;
import entities.player.Player;
import interface_adapter.Initialized.*;
import interface_adapter.Initiation.InitiationPresenter;
import interface_adapter.Initiation.InitiationViewModel;
import interface_adapter.SelectCard.SelectCardPresenter;
import interface_adapter.Undo.UndoPresenter;
import interface_adapter.ViewManagerModel;
import junit.framework.TestCase;
import org.junit.Test;
import use_case.PreTurn.FindPlayableCards;
import use_case.PreTurn.FindPlayableCardsInterface;
import use_case.SelectCard.SelectCardInputData;
import use_case.SelectCard.SelectCardInteractor;
import use_case.Undo.UndoInputData;
import use_case.Undo.UndoInteractor;
import use_case.initiation.InitiationInputData;
import use_case.initiation.InitiationInteractor;
import view.*;

import java.io.IOException;
import java.util.ArrayList;

public class SelectCard_Undo_Test extends TestCase {

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

        Player player = new HumanPlayer("You", numberCards, 0);

        int k = 1;
        SelectCardInputData selectCardInputData = new SelectCardInputData(numberCards.get(k), k);
        SelectCardPresenter selectCardPresenter = new SelectCardPresenter(viewManagerModel, cardButtonPanelViewModel, getCardPanelViewModel, bottomPanelViewModel);
        SelectCardInteractor selectCardInteractor = new SelectCardInteractor(selectCardPresenter);


        // Test select card
        selectCardInteractor.execute(selectCardInputData);

        // card button panel
        CardButtonPanelState cardButtonPanelState1 = cardButtonPanelViewModel.getState();
        assertEquals(cardButtonPanelState1.getOneCardsSelected(), true);

        // get card panel
        GetCardPanelState getCardPanelState1 = getCardPanelViewModel.getState();
        assertEquals(getCardPanelState1.isGetCardEnabled(), false);
        assertEquals(getCardPanelState1.isUndoEnabled(), true);

        // bottom panel
        BottomPanelState bottomPanelState1 = bottomPanelViewModel.getState();
        assertEquals(bottomPanelState1.getConfirmButtonEnabled(),true);
        assertEquals(bottomPanelState1.getNextButtonEnabled(), false);

        // =======================================================================

        UndoInputData undoInputData = new UndoInputData(numberCards.get(k));
        UndoPresenter undoPresenter = new UndoPresenter(viewManagerModel, getCardPanelViewModel, cardButtonPanelViewModel, funCardButtonPanelViewModel, bottomPanelViewModel);
        UndoInteractor undoInteractor = new UndoInteractor(undoPresenter, userDataAccessObject);

        // Test undo
        undoInteractor.execute(undoInputData);

        // card button panel
        CardButtonPanelState cardButtonPanelState2 = cardButtonPanelViewModel.getState();
        assertEquals(cardButtonPanelState2.getOneCardsSelected(), false);

        // get card panel
        GetCardPanelState getCardPanelState2 = getCardPanelViewModel.getState();
        assertEquals(getCardPanelState2.isUndoEnabled(), false);

        // bottom panel
        BottomPanelState bottomPanelState2 = bottomPanelViewModel.getState();
        assertEquals(bottomPanelState2.getConfirmButtonEnabled(),false);
        assertEquals(bottomPanelState2.getNextButtonEnabled(), false);

    }
}

