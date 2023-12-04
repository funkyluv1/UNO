package entities.Cards;

import entities.card.NumberCard;
import entities.card.PlusFourCard;
import entities.card.PlusTwoCard;

import java.util.ArrayList;

public class PlusFourTest {

    /*

    PLUS FOUR

    a card where if you use it
    the person that goes after
    receives 4 additional
    NUMBERCARDS

    behavior is the same as PlusTwo card
    this cannot be avoided by the next person in any way

     */


    public void testGetType()
    {

        PlusFourCard one = new PlusFourCard();
        String type = one.getType();
        //assertEquals(type, "PlusTwo");

    }


    public void testGetNumber()
    {

        PlusFourCard one = new PlusFourCard();

        /*
        should result in None

        PlusTwo cards have no color or value


         */


        //assertEquals(number, None);

    }

    public void testTwoCards() {

        PlusFourCard one = new PlusFourCard();
        PlusFourCard two = new PlusFourCard();

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

        PlusFourCard test = new PlusFourCard();

        // this should be the output when we call bombcard
        String cardInfo = "PlusTwo";

        //assertEquals(test.getString(), cardInfo);

    }


    public void testUseTwoCards() {

        PlusFourCard card = new PlusFourCard();
        ArrayList<NumberCard> functionalCards = new ArrayList<>();

        //functionalCards.add(card);


        //HumanPlayer playerDavid = new HumanPlayer("David", functionalCards, 1);
        //HumanPlayer playerAaron = new HumanPlayer("David", functionalCards, 1);

        /*
        get playerDavid to use a PlusTwo card, check if playerAaron has 2 more cards.
         */


    }


    public void testUseSeveralPlusTwoCards() {

        PlusFourCard card = new PlusFourCard();
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
