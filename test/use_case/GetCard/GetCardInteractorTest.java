package use_case.GetCard;

import entities.Game;
import entities.card.NumberCard;
import interface_adapter.GetCard.GetCardController;

import java.util.ArrayList;

public class GetCardInteractorTest {

    private Game game = new Game();


    public void testOneCard()
    {
        ArrayList<NumberCard> numberCards = new ArrayList<>();
        NumberCard num1 = new NumberCard(1, "G");
        NumberCard num2 = new NumberCard(1, "R");
        numberCards.add(num1);
        numberCards.add(num2);
    }

    public void testTwoCard(){
        ArrayList<NumberCard> numberCards = new ArrayList<>();
        NumberCard num1 = new NumberCard(1, "G");
        NumberCard num2 = new NumberCard(1, "R");
        numberCards.add(num1);
        numberCards.add(num2);
    }


    public void testThreeCards()
    {

        ArrayList<NumberCard> numberCards = new ArrayList<>();
        NumberCard num1 = new NumberCard(1, "G");
        NumberCard num2 = new NumberCard(1, "R");
        numberCards.add(num1);
        numberCards.add(num2);

    };


}
