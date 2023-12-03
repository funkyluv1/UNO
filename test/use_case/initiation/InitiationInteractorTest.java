package use_case.initiation;

import data_access.APIAccess;
import data_access.APIDataAccessObject;
import data_access.FileUserDataAccessObject;
import entities.NumberCardsDeck.NumberCardsDeck;
import entities.NumberCardsDeck.NumberCardsDeckFactory;
import entities.card.FunctionalCard;
import entities.card.NumberCard;
import entities.player.AIPlayerFactory;
import entities.player.HumanPlayerFactory;
import junit.framework.TestCase;
import use_case.DrawCards.DrawCardsDataAccessInterface;
import use_case.DrawCards.DrawCardsInputData;
import use_case.DrawCards.DrawCardsResponseExtractFacade;
import use_case.PreTurn.FindPlayableCards;
import use_case.PreTurn.FindPlayableCardsInterface;

import java.io.IOException;
import java.util.ArrayList;

public class InitiationInteractorTest extends TestCase {

    public void testExecute() {
    }

    void successTest() throws IOException {
        ArrayList<String> playerNames = new ArrayList<String>();
        playerNames.add("Jason");
        playerNames.add("Cynthia");
        playerNames.add("Tony");
        playerNames.add("Aaron");
        playerNames.add("Daniel");
        playerNames.add("David");
        int botNumber = 0;
        String csvPath = "users.csv";
        AIPlayerFactory aiPlayerFactory = new AIPlayerFactory();
        HumanPlayerFactory humanPlayerFactory = new HumanPlayerFactory();

        NumberCardsDeckFactory numberCardsDeckFactory = new NumberCardsDeckFactory() {
            @Override
            public NumberCardsDeck create(String id, int remainingCards) {
                return new NumberCardsDeck(id, remainingCards);
            }
        };

        InitiationInputData initiationInputData = new InitiationInputData(playerNames, botNumber);

        //DrawCardsInputData drawCardsInputData = new DrawCardsInputData(, numberCardsDeckFactory.create("Jason", 3));

        InitiationDataAccessInterface dao = new FileUserDataAccessObject(csvPath, aiPlayerFactory, humanPlayerFactory, numberCardsDeckFactory);

        // IMPORTANT: this creates a "mock presenter" that looks at the output data and assert things about it
        InitiationOutputDataBoundary successPresenter = new InitiationOutputDataBoundary() {
            @Override
            public void prepareNewGameView(InitiationOutputData initiationOutputData) {
                // tests if the output data has the player infos
                assertEquals("Jason", initiationOutputData.getPlayerNames().get(0));
                assertEquals("Cynthia", initiationOutputData.getPlayerNames().get(1));
                assertEquals("Tony", initiationOutputData.getPlayerNames().get(2));
                assertEquals("Aaron", initiationOutputData.getPlayerNames().get(3));
                assertEquals("Daniel", initiationOutputData.getPlayerNames().get(4));
                assertEquals("David", initiationOutputData.getPlayerNames().get(5));

                // tests if the dao can get the player infos from the database
                assertEquals(((FileUserDataAccessObject) dao).get_specific_player_with_index(0), "Jason");
                assertEquals(((FileUserDataAccessObject) dao).getNumberCards("Jason"), initiationOutputData.getPlayerNumCards().get("Jason"));

            }
        };

        DrawCardsDataAccessInterface drawCardsDataAccessInterface = new DrawCardsDataAccessInterface() {
            APIDataAccessObject apidao = new APIDataAccessObject();
            @Override
            public NumberCardsDeck returnNumberCardsDeck() {
                return apidao.returnNumberCardsDeck();
            }

            @Override
            public void reshuffleNumberCardsDeck(NumberCardsDeck numberCardsDeck) {
                apidao.reshuffleNumberCardsDeck(numberCardsDeck);
            }

            @Override
            public ArrayList<NumberCard> drawNumberCards(NumberCardsDeck numberCardsDeck, int drawNumber) {
                return apidao.drawNumberCards(numberCardsDeck,1);
            }
        };

        FindPlayableCardsInterface findPlayableCardsInterface = new FindPlayableCardsInterface() {
            // seems like this interface does not need to be initialized
            @Override
            public ArrayList<NumberCard> findPlayableNumberCards(String roundColor, ArrayList<NumberCard> numberCards) {
                return null;
            }

            @Override
            public ArrayList<FunctionalCard> findPlayableFunctionalCards(String roundColor, ArrayList<FunctionalCard> functionalCards) {
                return null;
            }
        };

        InitiationInputDataBoundary interactor = new InitiationInteractor((FileUserDataAccessObject) dao, drawCardsDataAccessInterface,successPresenter, findPlayableCardsInterface);

        interactor.execute(initiationInputData);
    }
}