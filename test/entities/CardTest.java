package entities;

import entities.card.*;
import entities.player.HumanPlayer;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class CardTest {
    // test abstract class Card
    @Test
    public void test_get_value(){
        Card card1 = new NumberCard(3, "B");
        assert(card1.getValue() == 3);
        Card card2 = new FunctionalCard(4, "R", "PlusTwo");
        assert (card2.getValue() == 4);
    }

    @Test
    public void test_get_color(){
        Card card1 = new NumberCard(3, "B");
        assert (card1.getColor() == "B");
        Card card2 = new FunctionalCard(4, "R", "PlusTwo");
        assert (card2.getColor() == "R");

    }

    //Test Number Card
    @Test
    public void test_NumberCard_constructor(){
        NumberCard numc1 = new NumberCard(3, "B");
        assert(numc1. getColor() == "B");
        assert(numc1.getValue() == 3);
    }

    @Test
    public void test_NumberCard_getstring(){
        NumberCard numc1 = new NumberCard(3, "B");
        System.out.println(numc1.getString());
        assert (numc1.getString().equals("3B"));

    }

    //Test Functional Card
    @Test
    public void test_functionalcard_contructor(){
        FunctionalCard fun1 = new FunctionalCard(3, "B", "PlusTwo");
        assert(fun1.getColor() == "B");
        assert(fun1.getValue() == 3);
    }
    @Test
    public void test_funcard_gettype(){
        FunctionalCard fun1 = new FunctionalCard(3, "B", "PlusTwo");
        assert (fun1.getType() == "PlusTwo");

    }
    @Test
    public void test_funcard_get_string(){
        FunctionalCard fun1 = new FunctionalCard(3, "B", "PlusTwo");
        assert(fun1.getString().equals("PlusTwo3B"));
    }
    @Test
    //Test BombCard
    public void test_bombcard_constructor(){
        BombCard bomb1 = new BombCard(3, "B");
        assert(bomb1.getColor() == "B");
        assert(bomb1.getValue() == 3);
        assert(bomb1.getType() == "Bomb");
        assert(bomb1.getString().equals("Bomb3B"));

    }
    @Test
    //Test HotPotatoCard
    public void test_hotpotato_constructor_and_methods(){
        HotPotatoCard hot1 = new HotPotatoCard(2, "R");
        assert(hot1.getColor() == "R");
        assert(hot1.getValue() == 2);
        assert(hot1.getType() == "HotPotato");
        assert(hot1.getString().equals("HotPotato2R"));
    }
    @Test
    //PlusFourCard
    public void test_Plus_two_card_constructor(){
        PlusTwoCard plus1 = new PlusTwoCard();
        assert (plus1.getColor() == "any");
        assert (plus1.getValue() == 20);
        assert (plus1.getType() == "PlusTwo");
        assert (plus1.getString().equals("PlusTwo20any"));
    }
    @Test
    //Test Random Card
    public void test_RandomCard_contructor(){
        RandomCard ran1 = new RandomCard();
        ArrayList<String> colors = new ArrayList<>();
        colors.add("red");
        colors.add("blue");
        colors.add("green");
        colors.add("yellow");
        assert(colors.contains(ran1.getColor()));
        assert(ran1.getValue() < 10);
        assert(ran1.getType() == "Random");

    }

    @Test
    //test skip card
    public void test_skip_card_constructor(){
        SkipCard ski1 = new SkipCard();
        assert(ski1.getType() == "Skip");
        assert(ski1.getValue() == 15);
        assert(ski1.getColor() == "any");
        assert(ski1.getString().equals("Skip15any"));
    }
    @Test
    //test wildcard
    public void test_wild_card_contructor(){
        WildCard wil1 = new WildCard();
        assert(wil1.getType() == "Wild");
        assert(wil1.getColor() == "any");
        assert(wil1.getValue() == 99);
        assert(wil1.getString().equals("Wild99any"));
    }

    //test CardFactory







}
