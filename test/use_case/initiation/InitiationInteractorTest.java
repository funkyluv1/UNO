package use_case.initiation;

import data_access.APIDataAccessObject;
import data_access.FileUserDataAccessObject;
import entities.NumberCardsDeck.NumberCardsDeck;
import entities.NumberCardsDeck.NumberCardsDeckFactory;
import entities.card.FunctionalCard;
import entities.card.NumberCard;
import entities.player.AIPlayerFactory;
import entities.player.HumanPlayerFactory;
import org.junit.jupiter.api.Test;
import use_case.DrawCards.DrawCardsDataAccessInterface;
import use_case.PreTurn.FindPlayableCardsInterface;
import use_case.initiation.InitiationInputData;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class InitiationInteractorTest {

    @Test
    void successTest() throws IOException {
        ArrayList<String> playerNames = new ArrayList<>();
        playerNames.add("Jason");
        playerNames.add("Cynthia");
        playerNames.add("Tony");
        playerNames.add("Aaron");
        playerNames.add("Daniel");
        playerNames.add("David");
        int botNumber = 0;
        InitiationInputData inputData = new InitiationInputData(playerNames, 0);
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


        InitiationDataAccessInterface dao = new FileUserDataAccessObject(csvPath, aiPlayerFactory, humanPlayerFactory, numberCardsDeckFactory);

        // This creates a successPresenter that tests whether the test case is as we expect.
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
        interactor.execute(inputData);
    }
}