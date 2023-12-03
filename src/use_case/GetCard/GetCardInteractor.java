package use_case.GetCard;

import entities.card.NumberCard;
import entities.card.NumberCardFactory;
import use_case.DrawCards.DrawCardsDataAccessInterface;

import java.util.*;


public class GetCardInteractor implements GetCardInputDataBoundary{

    final GetCardOutputDataBoundary getCardOutputDataBoundary;
    final GetCardDataAccessInterface getCardDataAccessObject;
    final DrawCardsDataAccessInterface drawCardsDataAccessInterface;

    public GetCardInteractor(GetCardOutputDataBoundary getCardOutputDataBoundary, GetCardDataAccessInterface getCardDataAccessObject, DrawCardsDataAccessInterface drawCardsDataAccessInterface){
        this.getCardOutputDataBoundary = getCardOutputDataBoundary;
        this.getCardDataAccessObject = getCardDataAccessObject;
        this.drawCardsDataAccessInterface = drawCardsDataAccessInterface;
    };


    public void execute(GetCardInputData getCardInputData) {

        NumberCard card = drawCardsDataAccessInterface.drawNumberCards(drawCardsDataAccessInterface.returnNumberCardsDeck(), 1).get(0);

        GetCardOutputData getCardOutputData = new GetCardOutputData(card);
        getCardOutputDataBoundary.prepareNewGameView(getCardOutputData);
    }
}
