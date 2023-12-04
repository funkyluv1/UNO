package use_case.initiation_undo_left_right_shift;

import data_access.APIDataAccessObject;
import data_access.FileUserDataAccessObject;
import entities.NumberCardsDeck.NumberCardsDeck;
import entities.NumberCardsDeck.NumberCardsDeckFactory;
import entities.card.Card;
import entities.card.FunctionalCard;
import entities.card.FunctionalCardFactory;
import entities.card.NumberCard;
import entities.player.AIPlayerFactory;
import entities.player.HumanPlayer;
import entities.player.HumanPlayerFactory;
import entities.player.Player;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import use_case.DrawCards.*;
import use_case.LeftShift.*;
import use_case.NextTurn.*;
import use_case.PostTurn.*;
import use_case.PreTurn.FindPlayableCards;
import use_case.PreTurn.FindPlayableCardsInterface;
import use_case.PreTurn.PreTurnDataAccessInterface;
import use_case.PreTurn.PreTurnInteractor;
import use_case.RightShift.*;
import use_case.Undo.*;
import use_case.initiation.*;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class InitiationInteractorTest {

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
        @Override
        public ArrayList<NumberCard> findPlayableNumberCards(String roundColor, ArrayList<NumberCard> numberCards) {
            return findPlayableCards.findPlayableNumberCards(roundColor, numberCards);
        }

        @Override
        public ArrayList<FunctionalCard> findPlayableFunctionalCards(String roundColor, ArrayList<FunctionalCard> functionalCards) {
            return findPlayableCards.findPlayableFunctionalCards(roundColor, functionalCards);
        }
    };

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
                assertEquals(undoOutputData.getUnselectedCard(), null);

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                // tests if the dao (specifically the initiationDataAccessInterface) methods

            }
        };

        UndoInputDataBoundary interactor = new UndoInteractor(successPresenter, dao);
        interactor.execute(inputData);
    }


    @Test
    void leftShiftSuccessTest() {
        int displayCardFirstIndex = 0;
        boolean flag_for_func = false;

        LeftShiftInputData inputData = new LeftShiftInputData(displayCardFirstIndex, flag_for_func);


        // NOTES: This creates a successPresenter that tests whether the test case is as we expect.
        LeftShiftOutputDataBoundary successPresenter = new LeftShiftOutputDataBoundary() {
            @Override
            public void prepareSuccessView(LeftShiftOutputData leftShiftOutputData) {
                // tests if the output data has the correct information
                assert(!leftShiftOutputData.getLeftShiftActive());

            }

            public void prepareSuccessView(LeftShiftOutputData leftShiftOutputData, boolean boolean_indicating_funcCards) {
                // tests if the output data has the correct information
                // TODO: funcards not implemented yet
            }
        };

        LeftShiftInputDataBoundary interactor = new LeftShiftInteractor(successPresenter);
        interactor.execute(inputData);
    }


    @Test
    void rightShiftSuccessTest() throws IOException {
        String csvPath = "users.csv";
        NumberCard card = new NumberCard(1, "Y");
        AIPlayerFactory aiPlayerFactory = new AIPlayerFactory();
        HumanPlayerFactory humanPlayerFactory = new HumanPlayerFactory();
        int displayCardFirstIndex = 0;
        boolean flag_for_func = false;
        NumberCardsDeckFactory numberCardsDeckFactory = new NumberCardsDeckFactory() {
            @Override
            public NumberCardsDeck create(String id, int remainingCards) {
                return new NumberCardsDeck(id, remainingCards);
            }
        };

        ArrayList<Card> playerNumCards = new ArrayList<>();
        NumberCard card1 = new NumberCard(1, "R");
        NumberCard card2 = new NumberCard(2, "G");
        NumberCard card3 = new NumberCard(3, "B");
        playerNumCards.add(card1);
        playerNumCards.add(card2);
        playerNumCards.add(card3);
        RightShiftInputData inputData = new RightShiftInputData(playerNumCards, displayCardFirstIndex, flag_for_func);

        RightShiftDataAccessInterface dao = new FileUserDataAccessObject(csvPath, aiPlayerFactory, humanPlayerFactory, numberCardsDeckFactory);

        // NOTES: This creates a successPresenter that tests whether the test case is as we expect.
        RightShiftOutputDataBoundary successPresenter = new RightShiftOutputDataBoundary() {
            @Override
            public void prepareSuccessView(RightShiftOutputData rightShiftOutputData) {
                // tests if the output data has the correct information
                assert(!rightShiftOutputData.getRightShiftActive());

            }

            public void prepareSuccessView(RightShiftOutputData rightShiftOutputData, boolean boolean_indicating_funcCards) {
                // tests if the output data has the correct information

            }
        };

        RightShiftInputDataBoundary interactor = new RightShiftInteractor((FileUserDataAccessObject) dao,successPresenter);
        interactor.execute(inputData);
    }


    @Test
    void drawCardsSuccessTest() throws IOException {
        String playername = "Jason";
        ArrayList<NumberCard> hand = new ArrayList<>();
        NumberCard card1 = new NumberCard(1, "R");
        hand.add(card1);
        NumberCard card2 = new NumberCard(1, "G");
        hand.add(card2);
        NumberCard card3 = new NumberCard(1, "B");
        hand.add(card3);
        HumanPlayer player = new HumanPlayer(playername, hand, 0);
        String csvPath = "users.csv";
        AIPlayerFactory aiPlayerFactory = new AIPlayerFactory();
        HumanPlayerFactory humanPlayerFactory = new HumanPlayerFactory();

        NumberCardsDeckFactory numberCardsDeckFactory = new NumberCardsDeckFactory() {
            @Override
            public NumberCardsDeck create(String id, int remainingCards) {
                return new NumberCardsDeck(id, remainingCards);
            }
        };

        DrawCardsDataAccessInterface dao =  drawCardsDataAccessInterface;
        //new APIDataAccessObject();


        FileUserDataAccessObject dao1 = new FileUserDataAccessObject(csvPath, aiPlayerFactory, humanPlayerFactory, numberCardsDeckFactory);

        NumberCardsDeck numberCardsDeck = dao1.getNumberCardsDeck();

        DrawCardsInputData inputData = new DrawCardsInputData(player, numberCardsDeck,1);

        // NOTES: This creates a successPresenter that tests whether the test case is as we expect.
        DrawCardsOutputDataBoundary successPresenter = new DrawCardsOutputDataBoundary(){
            @Override
            public void prepareSuccessView(DrawCardsOutputData drawCardsOutputData) {
                // tests if the output data has the correct information
                assert(drawCardsOutputData.getNumberCards() != null);
            }

        };

        DrawCardsInputDataBoundary interactor = new DrawCardsInteractor(successPresenter, dao);
        interactor.execute(inputData);
    }

    @Test
    void nextTurnSuccessTest() throws IOException {
        String playername = "Jason";
        ArrayList<NumberCard> hand = new ArrayList<>();
        NumberCard card1 = new NumberCard(1, "R");
        hand.add(card1);
        NumberCard card2 = new NumberCard(1, "G");
        hand.add(card2);
        NumberCard card3 = new NumberCard(1, "B");
        hand.add(card3);
        HumanPlayer player = new HumanPlayer(playername, hand, 0);
        String csvPath = "users.csv";
        AIPlayerFactory aiPlayerFactory = new AIPlayerFactory();
        HumanPlayerFactory humanPlayerFactory = new HumanPlayerFactory();

        NumberCardsDeckFactory numberCardsDeckFactory = new NumberCardsDeckFactory() {
            @Override
            public NumberCardsDeck create(String id, int remainingCards) {
                return new NumberCardsDeck(id, remainingCards);
            }
        };

        NextTurnDataAccessInterface dao =  new FileUserDataAccessObject(csvPath, aiPlayerFactory, humanPlayerFactory, numberCardsDeckFactory);

        NextTurnInputData inputData = new NextTurnInputData(0);

        // NOTES: This creates a successPresenter that tests whether the test case is as we expect.
        NextTurnOutputDataBoundary successPresenter = new NextTurnOutputDataBoundary(){

            @Override
            public void prepare_view(NextTurnOutputData nextTurnOutputData) {

            }
        };

        PreTurnDataAccessInterface preTurnDataAccessInterface = new PreTurnDataAccessInterface() {
            @Override
            public ArrayList<NumberCard> getNumberCards(String player) {
                return null;
            }

            @Override
            public void recordPreTurnChange(ArrayList<NumberCard> numberCards, String currentPlayer) {

            }

            @Override
            public ArrayList<FunctionalCard> getFunctionalCards(String player) {
                return null;
            }

            @Override
            public Player getPlayer(int playerIndex) {
                return null;
            }
        };

        PostTurnDataAccessInterface postTurnDataAccessInterface = new PostTurnDataAccessInterface() {
            @Override
            public void recordPostTurnChange(ArrayList<FunctionalCard> functionalCards, ArrayList<NumberCard> numberCards, String currentPlayer) {

            }

            @Override
            public void recordRoundChange(String currentPlayer, FunctionalCard reward) {

            }
        };


        PreTurnInteractor preTurnInteractor = new PreTurnInteractor(drawCardsDataAccessInterface,
                preTurnDataAccessInterface);
        PostTurnInteractor postTurnInteractor = new PostTurnInteractor(drawCardsDataAccessInterface,
                postTurnDataAccessInterface);

        NextTurnInputDataBoundary interactor = new NextTurnInteractor(dao,successPresenter,
                findPlayableCardsInterface, postTurnInteractor,
                preTurnInteractor);
        interactor.execute(inputData);
    }

}