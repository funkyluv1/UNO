package use_case.GetCard;

import entities.card.NumberCard;
import entities.card.NumberCardFactory;

import java.util.concurrent.ThreadLocalRandom;

public class GetCardInteractor implements GetCardInputDataBoundary{

    final GetCardOutputDataBoundary getCardOutputDataBoundary;
    final GetCardDataAccessInterface getCardDataAccessObject;

    public GetCardInteractor(GetCardOutputDataBoundary getCardOutputDataBoundary, GetCardDataAccessInterface getCardDataAccessObject){
        this.getCardOutputDataBoundary = getCardOutputDataBoundary;
        this.getCardDataAccessObject = getCardDataAccessObject;
    };


    public void execute(GetCardInputData getCardInputData) {
        String currPlayer = getCardInputData.getPlayerName();

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

        getCardDataAccessObject.getCard(currPlayer, card);

        GetCardOutputData getCardOutputData = new GetCardOutputData(card);
        getCardOutputDataBoundary.prepareNewGameView(getCardOutputData);
    }
}
