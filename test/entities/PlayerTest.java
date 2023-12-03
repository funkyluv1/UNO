package entities;

import entities.card.Card;
import entities.card.NumberCard;
import entities.player.HumanPlayer;
import entities.player.HumanPlayerFactory;
import entities.player.Player;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class PlayerTest extends TestCase {

    @Test
    public void test_Humanplayer_constructor(){
        ArrayList<NumberCard> numberCards = new ArrayList<>();
        NumberCard num1 = new NumberCard(1, "Green");
        NumberCard num2 = new NumberCard(1, "Red");
        numberCards.add(num1);
        numberCards.add(num2);
        HumanPlayer humanPlayer = new HumanPlayer("Aaron", numberCards, 1);
        assertNotNull(humanPlayer);
        assertEquals("Aaron", humanPlayer.getPlayerName());

    }

    @Test
    public void test_get_numbercards(){
        ArrayList<NumberCard> numberCards = new ArrayList<>();
        NumberCard num1 = new NumberCard(1, "Green");
        NumberCard num2 = new NumberCard(1, "Red");
        numberCards.add(num1);
        numberCards.add(num2);
        HumanPlayer humanPlayer = new HumanPlayer("Aaron", numberCards, 1);
        assertEquals(humanPlayer.getNumberCards(), numberCards);

    }

    public void test_set_numberCards(){

    }

    public void test_get

    @Test
    public void test_deal_card(){

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

    public void test_set_select_card(){

    }

    public void test_get_selectcard(){

    }

    public void test_set_Selectcard(){

    }



}
