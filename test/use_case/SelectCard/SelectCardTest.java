package use_case.SelectCard;

import entities.card.*;
import entities.player.HumanPlayer;
import entities.player.Player;
import junit.framework.TestCase;

import java.io.IOException;
import java.util.ArrayList;

public class SelectCardInputInteractorOutputTest extends TestCase {

    public void testExecute() {}

    void successTest() throws IOException {
        ArrayList<NumberCard> numberCards = new ArrayList<>();
        for (int i=0; i<6; i++) {
            String[] colors = {"R", "Y", "B", "G"};
            NumberCardFactory numberCardFactory = new NumberCardFactory(i, colors[i % 4]);
            numberCards.add(numberCardFactory.createCard());
        }

        Player player = new HumanPlayer("You", numberCards, 0);

        ArrayList<SelectCardInputData> selectCardInputs = new ArrayList<>();
        ArrayList<SelectCardOutputData> selectCardOutputs = new ArrayList<>();
        for (int i=0; i<numberCards.size(); i++) {
            selectCardInputs.add(new SelectCardInputData(numberCards.get(i), i));
            selectCardOutputs.add(new SelectCardOutputData(numberCards.get(i), i));
        }
        

    }
}

