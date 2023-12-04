package entities;

import entities.card.*;
import entities.player.*;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class PlayerTest extends TestCase {

    @Test
    public void test_Humanplayer_constructor(){
        ArrayList<NumberCard> numberCards = new ArrayList<>();
        NumberCard num1 = new NumberCard(1, "G");
        NumberCard num2 = new NumberCard(1, "R");
        numberCards.add(num1);
        numberCards.add(num2);
        HumanPlayer humanPlayer = new HumanPlayer("Aaron", numberCards, 1);
        assertNotNull(humanPlayer);
        assertEquals("Aaron", humanPlayer.getPlayerName());

    }

    @Test
    public void test_get_numbercards(){
        ArrayList<NumberCard> numberCards = new ArrayList<>();
        NumberCard num1 = new NumberCard(1, "G");
        NumberCard num2 = new NumberCard(1, "R");
        numberCards.add(num1);
        numberCards.add(num2);
        HumanPlayer humanPlayer = new HumanPlayer("Aaron", numberCards, 1);
        assertEquals(numberCards, humanPlayer.getNumberCards());

    }
    @Test
    public void test_set_numberCards(){
        ArrayList<NumberCard> numberCards = new ArrayList<>();
        NumberCard num3 = new NumberCard(2, "G");
        numberCards.add(num3);
        HumanPlayer humanPlayer = new HumanPlayer("Aaron", numberCards, 0);
        ArrayList<NumberCard> numberCards2 = new ArrayList<>();
        NumberCard num1 = new NumberCard(1, "G");
        NumberCard num2 = new NumberCard(1, "R");
        numberCards2.add(num1);
        numberCards2.add(num2);
        humanPlayer.setNumCards(numberCards2);
        assertEquals(numberCards2, humanPlayer.getNumberCards());
    }
    @Test
    public void test_get_set_funcards(){
        ArrayList<FunctionalCard> functionalCards = new ArrayList<>();
        FunctionalCard fun1 = new FunctionalCard(3, "Y", "PlusTwo");
        FunctionalCard fun2 = new FunctionalCard(4, "Blue", "Skip");
        ArrayList<NumberCard> numberCards = new ArrayList<>();
        NumberCard num1 = new NumberCard(2, "R");
        numberCards.add(num1);
        HumanPlayer player1 = new HumanPlayer("Daniel", numberCards, 0);
        assertEquals(true, player1.getFuncCards().isEmpty());
        player1.setFuncCards(functionalCards);
        assertEquals(functionalCards, player1.getFuncCards());
    }




    @Test
    public void test_deal_setselect_card(){
        ArrayList<NumberCard> numberCards = new ArrayList<>();
        NumberCard num1 = new NumberCard(1, "G");
        NumberCard num2 = new NumberCard(1, "R");
        numberCards.add(num1);
        numberCards.add(num2);
        HumanPlayer humanPlayer = new HumanPlayer("Aaron", numberCards, 1);
        num2.isUsable = true;
        humanPlayer.setSelectedCard(num2);

        ArrayList<FunctionalCard> funCards = new ArrayList<>();
        FunctionalCard fun1 = new FunctionalCard(3, "Y", "PlusTwo");
        FunctionalCard fun2 = new FunctionalCard(4, "Blue", "Skip");
        funCards.add(fun1);
        funCards.add(fun2);
        humanPlayer.setFuncCards(funCards);
        NumberCard topcard = new NumberCard(4, "B");
        Card a = humanPlayer.dealCard(topcard);
        assertEquals(num2, a);
        assertEquals(null, humanPlayer.getSelectedCard());
        assertEquals(1, humanPlayer.getNumberCards().size());



    }
    @Test
    public void test_get_set_firstdisplay(){
        ArrayList<NumberCard> numberCards = new ArrayList<>();
        NumberCard num1 = new NumberCard(1, "G");
        NumberCard num2 = new NumberCard(1, "R");
        numberCards.add(num1);
        numberCards.add(num2);
        HumanPlayer humanPlayer = new HumanPlayer("Aaron", numberCards, 1);
        humanPlayer.setDisplayFirstCardIndex(1);
        assertEquals(1, humanPlayer.getDisplayFirstCardIndex());
    }

    @Test
    public void test_is_playing_quit(){
        ArrayList<NumberCard> numberCards = new ArrayList<>();
        NumberCard num1 = new NumberCard(1, "G");
        NumberCard num2 = new NumberCard(1, "R");
        numberCards.add(num1);
        numberCards.add(num2);
        Player humanPlayer = new HumanPlayer("Aaron", numberCards, 1);
        assertEquals(true, humanPlayer.isPlaying());
        humanPlayer.quit();
        assertEquals(false, humanPlayer.isPlaying());

    }


    @Test
    public void test_get_null_selectcard(){
        ArrayList<NumberCard> numberCards = new ArrayList<>();
        NumberCard num1 = new NumberCard(1, "Green");
        NumberCard num2 = new NumberCard(1, "Red");
        numberCards.add(num1);
        numberCards.add(num2);
        HumanPlayer humanPlayer = new HumanPlayer("Aaron", numberCards, 1);
        assertEquals(null, humanPlayer.getSelectedCard());
    }
    @Test
    public void test_set_get_select_card(){
        ArrayList<NumberCard> numberCards = new ArrayList<>();
        NumberCard num1 = new NumberCard(1, "Green");
        NumberCard num2 = new NumberCard(1, "Red");
        numberCards.add(num1);
        numberCards.add(num2);
        HumanPlayer humanPlayer = new HumanPlayer("Aaron", numberCards, 1);
        num2.isUsable = true;
        humanPlayer.setSelectedCard(num2);
        assertEquals(num2, humanPlayer.getSelectedCard());

    }
    @Test
    public void test_get_setdisplay_first_index(){
        ArrayList<NumberCard> numberCards = new ArrayList<>();
        NumberCard num1 = new NumberCard(1, "Green");
        NumberCard num2 = new NumberCard(1, "Red");
        numberCards.add(num1);
        numberCards.add(num2);
        Player humanPlayer = new HumanPlayer("Aaron", numberCards, 1);
        humanPlayer.setDisplayFirstCardIndex(1);
        assertEquals(1, humanPlayer.getDisplayFirstCardIndex());

    }
    @Test
    public void test_player_get_playername(){
        ArrayList<NumberCard> numberCards = new ArrayList<>();
        NumberCard num1 = new NumberCard(1, "Green");
        NumberCard num2 = new NumberCard(1, "Red");
        numberCards.add(num1);
        numberCards.add(num2);
        Player humanPlayer = new HumanPlayer("Aaron", numberCards, 1);
        assertEquals("Aaron", humanPlayer.getPlayerName());
    }

    @Test
    public void test_Player_get_set_funcards(){
        ArrayList<FunctionalCard> functionalCards = new ArrayList<>();
        FunctionalCard fun1 = new FunctionalCard(3, "Y", "PlusTwo");
        FunctionalCard fun2 = new FunctionalCard(4, "Blue", "Skip");
        ArrayList<NumberCard> numberCards = new ArrayList<>();
        NumberCard num1 = new NumberCard(2, "R");
        numberCards.add(num1);
        Player player1 = new HumanPlayer("Daniel", numberCards, 0);
        assertEquals(true, player1.getFuncCards().isEmpty());
        player1.setFuncCards(functionalCards);
        assertEquals(functionalCards, player1.getFuncCards());
    }

    @Test
    public void test_player_get_numbercards(){
        ArrayList<NumberCard> numberCards = new ArrayList<>();
        NumberCard num1 = new NumberCard(1, "G");
        NumberCard num2 = new NumberCard(1, "R");
        numberCards.add(num1);
        numberCards.add(num2);
        Player humanPlayer = new HumanPlayer("Aaron", numberCards, 1);
        assertEquals(numberCards, humanPlayer.getNumberCards());

    }
    @Test
    public void test_player_set_numberCards(){
        ArrayList<NumberCard> numberCards = new ArrayList<>();
        NumberCard num3 = new NumberCard(2, "G");
        numberCards.add(num3);
        Player humanPlayer = new HumanPlayer("Aaron", numberCards, 0);
        ArrayList<NumberCard> numberCards2 = new ArrayList<>();
        NumberCard num1 = new NumberCard(1, "G");
        NumberCard num2 = new NumberCard(1, "R");
        numberCards2.add(num1);
        numberCards2.add(num2);
        humanPlayer.setNumCards(numberCards2);

        assertEquals(numberCards2, humanPlayer.getNumberCards());
    }

    // Test Human Player Factory

    @Test
    public void test_human_player_fac_constructor_methods(){
        HumanPlayerFactory hf = new HumanPlayerFactory();
        ArrayList<NumberCard> numcards = new ArrayList<>();
        NumberCard num1 = new NumberCard(1, "B");
        NumberCard num2 = new NumberCard(3, "G");
        numcards.add(num1);
        numcards.add(num2);
        ArrayList<FunctionalCard> funcards = new ArrayList<>();
        FunctionalCard fun1 = new FunctionalCard(2, "B", "Skip");
        funcards.add(fun1);
        HumanPlayer a = hf.create("Yuka", numcards, funcards, 0);
        assertEquals(numcards, a.getNumberCards());
        assertEquals(funcards, a.getFuncCards());
        assertEquals("Yuka", a.getPlayerName());


    }

    @Test
    public void testAIPlayerFactoryConstructor () {
        AIPlayerFactory aiPlayerFactory = new AIPlayerFactory();
        ArrayList<NumberCard> numberCards = new ArrayList<>();
        NumberCard num1 = new NumberCard(1, "B");
        NumberCard num2 = new NumberCard(3, "G");
        numberCards.add(num1);
        numberCards.add(num2);
        ArrayList<FunctionalCard> functionalCards = new ArrayList<>();
        functionalCards.add(new BombCard(2, "B"));
        functionalCards.add(new PlusFourCard());

        AIPlayer aiPlayer = aiPlayerFactory.create("bot", numberCards, functionalCards, 0);

        assertEquals("bot", aiPlayer.getPlayerName());
        assertEquals(numberCards, aiPlayer.getNumberCards());
        assertEquals(functionalCards, aiPlayer.getFuncCards());
    }
}
