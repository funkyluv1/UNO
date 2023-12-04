package entities.Cards;

import entities.card.BombCard;

public class BombCardTest {


    /*
    BOMBCARD

    a card where if you're unable
    to use it right away
    you get the x number of
    NumberCards stated on the bombcard

     */

    public void testGetType()
    {

        BombCard one = new BombCard(1, "Y");
        String type = one.getType();
        assertEquals(type, "Bomb");

    }


    public void testGetNumber()
    {

        BombCard one = new BombCard(1, "G");
        int number = one.getValue();
        assertEquals(number, 1);

    }

    public void testTwoBombCards() {

        BombCard one = new BombCard(1, "G");
        BombCard two = new BombCard(1, "Y");

        int valueOne = one.getValue();
        int valueTwo = one.getValue();

        assertEquals(valueOne, valueTwo);

    }

    public void testGetString() {

        BombCard test = new BombCard( 5, "Y");

        // this should be the output when we call bombcard
        String cardInfo = "Bomb5Y";

        assertEquals(test.getString(), cardInfo);

    }


}
