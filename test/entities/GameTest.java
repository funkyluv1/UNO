package entities;

import entities.card.FunctionalCard;
import entities.card.NumberCard;
import junit.framework.TestCase;

import java.util.ArrayList;

public class GameTest extends TestCase {
    private Game game;

    public void init(){
        game = new Game();
    }

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
        assertTrue(game.getCurrSelectedFunCard() instanceof ArrayList<>());

    }

    public void testSetCurrSelectedFunCard() {
        assertTrue(game.getCurrSelectedFunCard().isEmpty());
    }

    public void testAddNumCardsinRound() {
    }

    public void testGetNumCardsinRound() {
    }

    public void testSetMaxCardNum() {
    }

    public void testGetMaxCardNum() {
    }

    public void testSetCurrWinner() {
    }

    public void testGetCurrWinnerStr() {
    }
}