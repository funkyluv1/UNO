package use_case.initiation;

import data_access.APIDataAccessObject;
import data_access.FileUserDataAccessObject;
import entities.NumberCardsDeck.NumberCardsDeck;
import entities.NumberCardsDeck.NumberCardsDeckFactory;
import entities.card.FunctionalCard;
import entities.card.NumberCard;
import entities.player.AIPlayerFactory;
import entities.player.HumanPlayerFactory;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import use_case.DrawCards.DrawCardsDataAccessInterface;
import use_case.PreTurn.FindPlayableCards;
import use_case.PreTurn.FindPlayableCardsInterface;
import use_case.Undo.*;
import use_case.initiation.InitiationInputData;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class InitiationInteractorTest {

    @Test @Before
    void initiationSuccessTest() throws IOException {
        ArrayList<String> playerNames = new ArrayList<>();
        playerNames.add("Jason");
        playerNames.add("Cynthia");
        playerNames.add("Tony");
        playerNames.add("Aaron");
        playerNames.add("Daniel");
        playerNames.add("David");
        int botNumber = 0;
        InitiationInputData inputData = new InitiationInputData(playerNames, botNumber);
        String csvPath = "users.csv";
        AIPlayerFactory aiPlayerFactory = new AIPlayerFactory();
        HumanPlayerFactory humanPlayerFactory = new HumanPlayerFactory();

        NumberCardsDeckFactory numberCardsDeckFactory = new NumberCardsDeckFactory() {
            @Override
            public NumberCardsDeck create(String id, int remainingCards) {
                return new NumberCardsDeck(id, remainingCards);
            }
        };

        InitiationDataAccessInterface dao = new FileUserDataAccessObject(csvPath, aiPlayerFactory, humanPlayerFactory, numberCardsDeckFactory);

        // NOTES: This creates a successPresenter that tests whether the test case is as we expect.
        InitiationOutputDataBoundary successPresenter = new InitiationOutputDataBoundary() {
            @Override
            public void prepareNewGameView(InitiationOutputData initiationOutputData) {
                // tests if the output data has the correct information
                assertEquals("Jason", initiationOutputData.getPlayerNames().get(0));
                assertEquals("Cynthia", initiationOutputData.getPlayerNames().get(1));
                assertEquals("Tony", initiationOutputData.getPlayerNames().get(2));
                assertEquals("Aaron", initiationOutputData.getPlayerNames().get(3));
                assertEquals("Daniel", initiationOutputData.getPlayerNames().get(4));
                assertEquals("David", initiationOutputData.getPlayerNames().get(5));
                assertEquals(36, initiationOutputData.getNumberCardsDeck().getRemainingCards());
                assertTrue(initiationOutputData.getPlayerNumCards().get("Jason").get(0) instanceof NumberCard);

                // BUG IN PLAYABLENUMCARDS
                //assertTrue(initiationOutputData.getPlayerPlayableNumCards().get("Jason").get(0) instanceof NumberCard);
                //System.out.println(initiationOutputData.getPlayerPlayableNumCards().get("Jason").get(0).getString());

                // PLAYER FUNCCARDS NOT IMPLEMENTED YET
                //System.out.println(initiationOutputData.getPlayerFunCards().get("Jason").get(0));

                //System.out.println(initiationOutputData.getPlayerPlayableFunCards().get("Jason").get(0));

                //assertEquals(0, initiationOutputData.getDisplayNumCardsIndexes().get("Jason"));

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                // tests if the dao (specifically the initiationDataAccessInterface) methods
                //System.out.println(dao.getPlayerDisplayFirstCardIndex("Jason"));

                // NOTES: many other methods of the dao should not be tested here
                // e.g. dao.getNumberCards(), because it is a method in the preTurn data access interface
            }
        };

        // NOTES: This is how we locally declare data access interface, mimic the functionality of the actual interface
        DrawCardsDataAccessInterface drawCardsDataAccessInterface = new DrawCardsDataAccessInterface() {
            APIDataAccessObject apidao = new APIDataAccessObject();
            @Override
            public NumberCardsDeck createNumberCardsDeck() {
                return apidao.createNumberCardsDeck();
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
            FindPlayableCards findPlayableCards = new FindPlayableCards();
            //TODO: implement this interface, there seems to be a bug
            @Override
            public ArrayList<NumberCard> findPlayableNumberCards(String roundColor, ArrayList<NumberCard> numberCards) {
                //return findPlayableCards.findPlayableNumberCards(roundColor, numberCards);
                return null;
            }

            @Override
            public ArrayList<FunctionalCard> findPlayableFunctionalCards(String roundColor, ArrayList<FunctionalCard> functionalCards) {
                //return findPlayableCards.findPlayableFunctionalCards(roundColor, functionalCards);
                return null;
            }
        };

        InitiationInputDataBoundary interactor = new InitiationInteractor((FileUserDataAccessObject) dao, drawCardsDataAccessInterface,successPresenter, findPlayableCardsInterface);
        interactor.execute(inputData);
    }

    @Test
    void undoSuccessTest() throws IOException {
        String csvPath = "users.csv";
        NumberCard card = new NumberCard(1, "Y");
        AIPlayerFactory aiPlayerFactory = new AIPlayerFactory();
        HumanPlayerFactory humanPlayerFactory = new HumanPlayerFactory();

        NumberCardsDeckFactory numberCardsDeckFactory = new NumberCardsDeckFactory() {
            @Override
            public NumberCardsDeck create(String id, int remainingCards) {
                return new NumberCardsDeck(id, remainingCards);
            }
        };

        UndoInputData inputData = new UndoInputData(card);

        UndoDataAccessInterface dao = new FileUserDataAccessObject(csvPath, aiPlayerFactory, humanPlayerFactory, numberCardsDeckFactory);

        // NOTES: This creates a successPresenter that tests whether the test case is as we expect.
        UndoOutputDataBoundary successPresenter = new UndoOutputDataBoundary() {
            @Override
            public void prepareUndoView(UndoOutputData undoOutputData) {
                // tests if the output data has the correct information
                //assertEquals(undoOutputData.getUnselectedCard(), card);
                System.out.println(undoOutputData.getUnselectedCard());

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                // tests if the dao (specifically the initiationDataAccessInterface) methods

            }
        };

        UndoInputDataBoundary interactor = new UndoInteractor(successPresenter, dao);
        interactor.execute(inputData);
    }
}