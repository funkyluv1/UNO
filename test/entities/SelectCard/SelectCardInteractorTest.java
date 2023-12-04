package entities.SelectCard;

import entities.Game;
import entities.card.NumberCard;

import java.util.ArrayList;

public class SelectCardInteractorTest {

    private Game game = new Game();



    public void testSelectACard() {
        ArrayList<NumberCard> numberCards = new ArrayList<>();
        NumberCard num1 = new NumberCard(1, "G");
        NumberCard num2 = new NumberCard(1, "R");
        numberCards.add(num1);
        numberCards.add(num2);

    }

    public void testSelectLastCard() {
        ArrayList<NumberCard> numberCards = new ArrayList<>();
        NumberCard num1 = new NumberCard(1, "G");
        NumberCard num2 = new NumberCard(1, "R");
        numberCards.add(num1);
        numberCards.add(num2);

    }


    public void testSelectAfterUndo() {
        ArrayList<NumberCard> numberCards = new ArrayList<>();
        NumberCard num1 = new NumberCard(1, "G");
        NumberCard num2 = new NumberCard(1, "R");
        numberCards.add(num1);
        numberCards.add(num2);

    }

}
