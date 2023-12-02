package use_case.GetCard;

import entities.card.NumberCard;
import entities.card.NumberCardFactory;

import java.util.concurrent.ThreadLocalRandom;

public class GetCardInteractor implements GetCardInputDataBoundary{

    public GetCardInteractor(){};


    public void execute(GetCardInputData player) {
        int randomNum = ThreadLocalRandom.current().nextInt(1,  10 + 1);
        int randomcol = ThreadLocalRandom.current().nextInt(1, 5);
        String randomColor = "";

        switch (randomcol) {
            case 1 -> randomColor = "R";
            case 2 -> randomColor = "G";
            case 3 -> randomColor = "B";
            case 4 -> randomColor = "Y";
        }

        NumberCard card = (new NumberCardFactory(randomNum, randomColor)).createCard();


    }
}
