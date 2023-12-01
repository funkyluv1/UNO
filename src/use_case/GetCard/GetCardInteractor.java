package use_case.GetCard;

import entities.card.NumberCard;
import entities.card.NumberCardFactory;

import java.util.concurrent.ThreadLocalRandom;

public class GetCardInteractor implements GetCardInputDataBoundary{

    public GetCardInteractor(){};


    public void execute(GetCardInputData player) {
        int randomNum = ThreadLocalRandom.current().nextInt(1,  10 + 1);
        int randomcol = ThreadLocalRandom.current().nextInt(1, 5);
        String randomColor;

        switch (randomcol) {
            case 1 -> randomColor = "RED";
            case 2 -> randomColor = "GREEN";
            case 3 -> randomColor = "BLUE";
            case 4 -> randomColor = "YELLOW";
        }

        // delete this later
        randomColor = "RED";

        NumberCard card = (new NumberCardFactory(randomNum, randomColor)).createCard();


    }
}
