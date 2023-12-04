package entities.Cards;

import entities.card.BombCard;
import entities.card.RandomCard;

public class RandomCardTest {


    public void testGetType()
    {

        /* inherits from FunctionalCard class,
        but it acts the same way as
        a normal number card would

        */

        RandomCard one = new RandomCard();
        String type = one.getType();
        assertEquals(type, "Number");
    }



}
