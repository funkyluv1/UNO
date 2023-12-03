package data_access;

import entities.NumberCardsDeck.NumberCardsDeck;
import entities.card.NumberCard;
import use_case.DrawCards.DrawCardsDataAccessInterface;
import use_case.DrawCards.DrawCardsResponseExtractFacade;

import java.util.ArrayList;

public class APIDataAccessObject implements DrawCardsDataAccessInterface {

    private NumberCardsDeck numberCardsDeck;
    public APIDataAccessObject() {
        this.numberCardsDeck = createNumberCardsDeck();
    }

    @Override
    public NumberCardsDeck returnNumberCardsDeck() {
        return numberCardsDeck;
    }

    public NumberCardsDeck createNumberCardsDeck() {

        String apiUrl = "https://deckofcardsapi.com/api/deck/new/shuffle/?cards=AS,AH,AC,AD,2S,2D,2C,2H,3S,3H,3C,3D,4S,4C,4D,4H,5S,5H,5C,5D,6S,6H,6C,6D,7S,7H,7C,7D,8S,8H,8C,8D,9C,9D,9S,9H,10S,10H,10C,10D";
        APIAccess apiAccess = new APIAccess(apiUrl);
        StringBuilder response = apiAccess.send();

        DrawCardsResponseExtractFacade drawCardsResponseExtractFacade = new DrawCardsResponseExtractFacade(response);
        String id = drawCardsResponseExtractFacade.DrawCardsExtractID();
        String decknum = drawCardsResponseExtractFacade.DrawCardsExtractRemaining();
        return new NumberCardsDeck(id, Integer.parseInt(decknum));
    }

    @Override

    /* Precondition: dealNumber <= numberCardsDeck.remainingCards*/

    public ArrayList<NumberCard> drawNumberCards(NumberCardsDeck numberCardsDeck, int dealNumber) {
        StringBuilder response;
        String id = numberCardsDeck.getId();
        String num = String.valueOf(dealNumber);
        String apiUrl = "https://deckofcardsapi.com/api/deck/" + id + "/draw/?count=" + num;
        APIAccess apiAccess = new APIAccess(apiUrl);
        response = apiAccess.send();

        DrawCardsResponseExtractFacade drawCardsResponseExtractFacade = new DrawCardsResponseExtractFacade(response);
        StringToCardConverter adapter = new StringToCardConverter(drawCardsResponseExtractFacade.DrawCardsExtractNumCards());
        return adapter.convertToNumCards();
    }

    @Override
    public void reshuffleNumberCardsDeck(NumberCardsDeck numberCardsDeck) {
        StringBuilder response;
        String id = numberCardsDeck.getId();
        String apiUrl = "https://deckofcardsapi.com/api/deck/" + id + "/shuffle/";
        APIAccess apiAccess = new APIAccess(apiUrl);
        response = apiAccess.send();

        DrawCardsResponseExtractFacade drawCardsResponseExtractFacade = new DrawCardsResponseExtractFacade(response);
        String decknum = drawCardsResponseExtractFacade.DrawCardsExtractRemaining();
        numberCardsDeck.setRemainingCards(Integer.parseInt(decknum));
    }

}