package entities.Cards;

import entities.card.BombCard;
import entities.card.HotPotatoCard;

public class HotPotatoCardTest {


    public void testGetType()
    {

        HotPotatoCard one = new HotPotatoCard(1, "Y");
        String type = one.getType();
        assertEquals(type, "HotPotato");

    }


    public void testGetNumber()
    {

        HotPotatoCard one = new HotPotatoCard(1, "G");
        int number = one.getValue();
        assertEquals(number, 1);

    }

    public void testTwoBombCards() {

        HotPotatoCard one = new HotPotatoCard(1, "G");
        HotPotatoCard two = new HotPotatoCard(1, "Y");

        int valueOne = one.getValue();
        int valueTwo = one.getValue();

        assertEquals(valueOne, valueTwo);

    }

    public void testGetString() {

        HotPotatoCard test = new HotPotatoCard( 5, "Y");

        // this should be the output when we call bombcard
        String cardInfo = "HotPotato5Y";

        assertEquals(test.getString(), cardInfo);

    }










}
