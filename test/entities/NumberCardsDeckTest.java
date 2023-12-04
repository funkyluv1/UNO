package entities;

import entities.NumberCardsDeck.NumberCardsDeck;
import entities.NumberCardsDeck.NumberCardsDeckCreator;
import entities.NumberCardsDeck.NumberCardsDeckFactory;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

public class NumberCardsDeckTest extends TestCase {
    @Test
    public void test_number_card_deck_constructor(){
        NumberCardsDeck numberCardsDeck = new NumberCardsDeck("Kevin", 3);
        assertEquals("Kevin",numberCardsDeck.getId());
        assertEquals(3, numberCardsDeck.getRemainingCards());

    }


    @Test
    public void test_set_remaining_cards(){
        NumberCardsDeck numberCardsDeck = new NumberCardsDeck("Kevin", 3);
        numberCardsDeck.setRemainingCards(4);
        assertEquals(4, numberCardsDeck.getRemainingCards());

    }

    @Test
    public void test_umcar_creater_create(){
        NumberCardsDeckFactory hh = new NumberCardsDeckCreator();
        NumberCardsDeck a = hh.create("Martin", 2);
        assert(a.getId().equals("Martin"));
        assert(a.getRemainingCards() == 2);

    }
}
