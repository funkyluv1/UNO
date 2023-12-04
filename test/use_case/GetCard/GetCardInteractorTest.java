package use_case.GetCard;

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
import interface_adapter.GetCard.GetCardController;
import interface_adapter.GetCard.GetCardPresenter;
import interface_adapter.Initialized.*;
import interface_adapter.Initiation.InitiationPresenter;
import interface_adapter.Initiation.InitiationViewModel;
import interface_adapter.SelectCard.SelectCardPresenter;
import interface_adapter.ViewManagerModel;
import junit.framework.TestCase;
import use_case.PreTurn.FindPlayableCards;
import use_case.PreTurn.FindPlayableCardsInterface;
import use_case.SelectCard.SelectCardInputData;
import use_case.SelectCard.SelectCardInteractor;
import use_case.initiation.InitiationInputData;
import use_case.initiation.InitiationInteractor;
import view.*;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class GetCardInteractorTest extends TestCase {

    private Game game = Game.getInstance();

    public void testExecute() throws IOException {
        testOneCard();
        testTwoCard();
        testThreeCards();
        testGetCardInteractorExecute();
    }

    public void testOneCard()
    {
        ArrayList<NumberCard> numberCards = new ArrayList<>();
        NumberCard num1 = new NumberCard(1, "G");
        NumberCard num2 = new NumberCard(1, "R");
        numberCards.add(num1);
        numberCards.add(num2);
    }

    public void testTwoCard(){
        ArrayList<NumberCard> numberCards = new ArrayList<>();
        NumberCard num1 = new NumberCard(1, "G");
        NumberCard num2 = new NumberCard(1, "R");
        numberCards.add(num1);
        numberCards.add(num2);
    }


    public void testThreeCards()
    {

        ArrayList<NumberCard> numberCards = new ArrayList<>();
        NumberCard num1 = new NumberCard(1, "G");
        NumberCard num2 = new NumberCard(1, "R");
        numberCards.add(num1);
        numberCards.add(num2);

    };

    public void testGetCardInteractorExecute() throws IOException {
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

        GetCardInputData getCardInputData = new GetCardInputData(0);
        GetCardPresenter getCardPresenter = new GetCardPresenter(viewManagerModel, getCardPanelViewModel, cardButtonPanelViewModel);
        GetCardInteractor getCardInteractor = new GetCardInteractor(getCardPresenter, userDataAccessObject, apiDataAccessObject);

        // =======================================================================

        game.setSkipped(false);
        game.setPlusN(0);
        while (game.getCurrentPlayerIndex() != 0){
            game.updateCurrentPlayerIndex();
        }

        // Test get card
        getCardInteractor.execute(getCardInputData);

        // card button panel
        CardButtonPanelState cardButtonPanelState = cardButtonPanelViewModel.getState();
        assertFalse(cardButtonPanelState.getOneCardsSelected());
    }


}
