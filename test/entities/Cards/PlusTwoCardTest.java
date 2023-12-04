package entities.Cards;

import entities.card.BombCard;
import entities.card.FunctionalCard;
import entities.card.NumberCard;
import entities.card.PlusTwoCard;
import entities.player.HumanPlayer;

import java.util.ArrayList;

public class PlusTwoCardTest {

    /*

    PLUS TWO

    a card where if you use it
    the person that goes after
    receives 2 additional
    NUMBERCARDS

    this cannot be avoided by the next person in any way

     */


    public void testGetType()
    {

        PlusTwoCard one = new PlusTwoCard();
        String type = one.getType();
        //assertEquals(type, "PlusTwo");

    }


    public void testGetNumber()
    {

        PlusTwoCard one = new PlusTwoCard();

        /*
        should result in None

        PlusTwo cards have no color or value


         */


        //assertEquals(number, None);

    }

    public void testTwoCards() {

        PlusTwoCard one = new PlusTwoCard();
        PlusTwoCard two = new PlusTwoCard();

        int valueOne = one.getValue();
        int valueTwo = one.getValue();

        /*
        these should not have any values
        plustwo does not take any parameters
         */

        //assertEquals(valueOne, None);
        //assertEquals(valueTwo, None);

    }

    public void testGetString() {

        PlusTwoCard test = new PlusTwoCard();

        // this should be the output when we call bombcard
        String cardInfo = "PlusTwo";

        //assertEquals(test.getString(), cardInfo);

    }


    public void testUseTwoCards() {

        PlusTwoCard card = new PlusTwoCard();
        ArrayList<NumberCard> functionalCards = new ArrayList<>();

        //functionalCards.add(card);


        //HumanPlayer playerDavid = new HumanPlayer("David", functionalCards, 1);
        //HumanPlayer playerAaron = new HumanPlayer("David", functionalCards, 1);

        /*
        get playerDavid to use a PlusTwo card, check if playerAaron has 2 more cards.
         */


    }


    public void testUseSeveralPlusTwoCards() {

        PlusTwoCard card = new PlusTwoCard();
        ArrayList<NumberCard> functionalCards = new ArrayList<>();

        //functionalCards.add(card);


        //HumanPlayer playerDavid = new HumanPlayer("David", functionalCards, 1);
        //HumanPlayer playerAaron = new HumanPlayer("David", functionalCards, 1);

        /*
        get playerDavid to use x amounts of plusTwo cards, playerAaron should have 2 * x
        more NumberCards
         */

    }

}
