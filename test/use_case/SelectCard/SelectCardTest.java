package use_case.SelectCard;

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
import interface_adapter.ViewManagerModel;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;
import use_case.PreTurn.FindPlayableCards;
import use_case.PreTurn.FindPlayableCardsInterface;
import use_case.initiation.InitiationInputData;
import use_case.initiation.InitiationInteractor;
import view.*;

import java.io.IOException;
import java.util.ArrayList;

public class SelectCardTest extends TestCase {

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


        // Test whether, given an input, the use case produces the correct outcome
        selectCardInteractor.execute(selectCardInputData);

        // card button panel
        CardButtonPanelState cardButtonPanelState = cardButtonPanelViewModel.getState();
        assertEquals(cardButtonPanelState.getOneCardsSelected(), true);
        assertEquals(cardButtonPanelState.getLeftButtonEnabled(), false);
        assertEquals(cardButtonPanelState.getRightButtonEnabled(), false);

        // get card panel
        GetCardPanelState getCardPanelState = getCardPanelViewModel.getState();
        assertEquals(getCardPanelState.isGetCardEnabled(), false);
        assertEquals(getCardPanelState.isUndoEnabled(), true);

        // bottom panel
        BottomPanelState bottomPanelState = bottomPanelViewModel.getState();
        assertEquals(bottomPanelState.getConfirmButtonEnabled(),true);
        assertEquals(bottomPanelState.getNextButtonEnabled(), false);

        System.out.println("Test passed");
    }
}

