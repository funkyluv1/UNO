package use_case.drawcards;

import entities.NumberCardsDeck;
import entities.card.NumberCard;

import java.util.ArrayList;

public class DrawCardsInteractor implements DrawCardsInputDataBoundary {
    public DrawCardsOutputDataBoundary drawCardsOutputDataBoundary;
    public DrawCardsDataAccessInterface drawCardsDataAccessInterface;
    public DrawCardsInteractor(DrawCardsOutputDataBoundary drawCardsOutputDataBoundary, DrawCardsDataAccessInterface drawCardsDataAccessInterface){
        this.drawCardsOutputDataBoundary = drawCardsOutputDataBoundary;
        this.drawCardsDataAccessInterface = drawCardsDataAccessInterface;
    }

    @Override
    public NumberCardsDeck createNumberCardsDeck(){
        return drawCardsDataAccessInterface.createNumberCardsDeck();
    };

    @Override
    public void reshuffleNumberCardsDeck(DrawCardsInputData drawCardsInputData){
        drawCardsDataAccessInterface.reshuffleNumberCardsDeck(drawCardsInputData.getNumberCardsDeck());
    }

    @Override
    public void drawNumberCards(DrawCardsInputData drawCardsInputData) {
        ArrayList<String> numCardsInfo;
        ArrayList<NumberCard> playerHands;

        if (drawCardsInputData.getDrawNumber() <= drawCardsInputData.getNumberCardsDeck().getRemainingCards()){
            numCardsInfo = drawCardsDataAccessInterface.drawNumberCards(drawCardsInputData.getNumberCardsDeck(), drawCardsInputData.getDrawNumber());
            for (String numCardInfo : numCardsInfo){
                playerHands = drawCardsInputData.getPlayer().getNumberCards();
                //Todo: need Card Builder; Use Card Builder to create new Card and update each player's hands
            }
            drawCardsInputData.getNumberCardsDeck().setRemainingCards(drawCardsInputData.getNumberCardsDeck().getRemainingCards() - drawCardsInputData.getDrawNumber());
        }
        else{
            int deckRemainingCards = drawCardsInputData.getNumberCardsDeck().getRemainingCards();
            numCardsInfo = drawCardsDataAccessInterface.drawNumberCards(drawCardsInputData.getNumberCardsDeck(), deckRemainingCards);
            reshuffleNumberCardsDeck(drawCardsInputData);
            numCardsInfo.addAll(drawCardsDataAccessInterface.drawNumberCards(drawCardsInputData.getNumberCardsDeck(), drawCardsInputData.getDrawNumber() - deckRemainingCards));
            for (String numCardInfo : numCardsInfo){
                playerHands = drawCardsInputData.getPlayer().getNumberCards();
                //Todo: need Card Builder; Use Card Builder to create new Card and update each player's hands
            }
            drawCardsInputData.getNumberCardsDeck().setRemainingCards(drawCardsInputData.getNumberCardsDeck().getRemainingCards() - drawCardsInputData.getDrawNumber() + deckRemainingCards);
        }

    }
}
