package entities;

import entities.NumberCardsDeck.NumberCardsDeck;
import entities.card.FunctionalCard;
import entities.card.NumberCard;
import junit.framework.TestCase;

import java.util.ArrayList;

public class GameTest extends TestCase {
    private Game game = new Game();

    public void testGetInstance() {
        assertTrue(game.getInstance() != null);
        assertEquals(game.getInstance().getClass(), game.getClass());
    }

    public void testGetTopCard() {
        assertTrue(game.getTopCard() instanceof NumberCard);
    }

    public void testSetTopCard() {
    }

    public void testGetFuncCard() {
    }

    public void testSetFuncCard() {
    }

    public void testGetDrawCardInitialValue() {
        assertEquals(game.getDrawCard(), 0);
    }

    public void testSetDrawCard() {
        assertEquals(game.getDrawCard(), 0);
        game.setDrawCard(1);
        assertEquals(game.getDrawCard(), 1);
    }

    public void testGetSkippedInitialValue() {
        assertEquals(game.getSkipped(), false);
    }

    public void testSetSkipped() {
        assertEquals(game.getSkipped(), false);
        game.setSkipped(true);
        assertEquals(game.getSkipped(), true);
    }

    public void testGetPlusNInitialValue() {
        assertEquals(game.getPlusN(), 0);
    }

    public void testSetPlusN() {
        assertEquals(game.getPlusN(), 0);
        game.setPlusN(1);
        assertEquals(game.getPlusN(), 1);
    }

    public void testGetCurrentPlayerIndexInitialValue() {
        assertEquals(game.getCurrentPlayerIndex(), 1);
    }

    public void testUpdateCurrentPlayerIndex() {
        assertEquals(game.getCurrentPlayerIndex(), 1);
        game.updateCurrentPlayerIndex();
        assertEquals(game.getCurrentPlayerIndex(), 2);
        game.updateCurrentPlayerIndex();
        assertEquals(game.getCurrentPlayerIndex(), 3);
        game.updateCurrentPlayerIndex();
        assertEquals(game.getCurrentPlayerIndex(), 0);
    }

    public void testGetCurrSelectedNumberCardInitialValue() {
        assertEquals(game.getCurrSelectedNumberCard(), null);
    }

    public void testSetCurrSelectedNumberCard() {
        assertEquals(game.getCurrSelectedNumberCard(), null);
        NumberCard card = new NumberCard(1, "Yellow");
        game.setCurrSelectedNumberCard(card);
        assertEquals(game.getCurrSelectedNumberCard(), card);
    }

    public void testGetCurrSelectedFunCardInitialValue() {
        ArrayList<FunctionalCard> cards = new ArrayList<FunctionalCard>();
        assertEquals(game.getCurrSelectedFunCard().getClass(), cards.getClass());
        assertTrue(game.getCurrSelectedFunCard().isEmpty());
    }

    public void testSetCurrSelectedFunCard() {
        assertTrue(game.getCurrSelectedFunCard().isEmpty());
        ArrayList<FunctionalCard> cards = new ArrayList<FunctionalCard>();
        FunctionalCard card1 = new FunctionalCard(0, "Red", "PlusTwo");
        FunctionalCard card2 = new FunctionalCard(0, "Blue", "PlusFour");
        cards.add(card1);
        cards.add(card2);
        game.setCurrSelectedFunCard(cards);
        assertEquals(game.getCurrSelectedFunCard().get(0), card1);
        assertEquals(game.getCurrSelectedFunCard().get(1), card2);
    }

    public void testGetNumCardsinRoundInitialValue() {
        ArrayList<NumberCard> cards = new ArrayList<NumberCard>();
        assertEquals(game.getNumCardsinRound().getClass(), cards.getClass());
        assertTrue(game.getNumCardsinRound().isEmpty());
    }
    public void testAddNumCardsinRound() {
        ArrayList<NumberCard> cards = new ArrayList<NumberCard>();
        assertEquals(game.getNumCardsinRound().getClass(), cards.getClass());
        assertTrue(game.getNumCardsinRound().isEmpty());

        NumberCard card1 = new NumberCard(1, "Yellow");
        NumberCard card2 = new NumberCard(2, "Blue");
        game.addNumCardsinRound(card1);
        game.addNumCardsinRound(card2);
        assertEquals(game.getNumCardsinRound().get(0), card1);
        assertEquals(game.getNumCardsinRound().get(0), card2);
    }

    public void testGetMaxCardNumInitialValue() {
        assertEquals(game.getMaxCardNum(), 0);
    }

    public void testSetMaxCardNum() {
        assertEquals(game.getMaxCardNum(), 0);
        game.setMaxCardNum(1);
        assertEquals(game.getMaxCardNum(), 1);
    }
    public void testGetCurrWinnerStrInitialValue() {
        assertEquals(game.getCurrWinnerStr(), "");
    }

    public void testSetCurrWinner() {
        assertEquals(game.getCurrWinnerStr(), "");
        game.setCurrWinner("John");
        assertEquals(game.getCurrWinnerStr(), "John");
    }

    public void testSetGetNumberCardDeck(){
        NumberCardsDeck cardDeck = new NumberCardsDeck("random id", 3);
        game.setNumberCardDeck(cardDeck);
        assertEquals(game.getNumberCardDeck(), cardDeck);
    }

}