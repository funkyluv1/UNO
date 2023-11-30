package use_case.DrawCards;

import entities.card.NumberCard;

import java.util.ArrayList;

public class DrawCardsInteractor implements DrawCardsInputDataBoundary {
    final DrawCardsOutputDataBoundary drawCardsOutputDataBoundary;
    final DrawCardsDataAccessInterface drawCardsDataAccessInterface;
    public DrawCardsInteractor(DrawCardsOutputDataBoundary drawCardsOutputDataBoundary, DrawCardsDataAccessInterface drawCardsDataAccessInterface){
        this.drawCardsOutputDataBoundary = drawCardsOutputDataBoundary;
        this.drawCardsDataAccessInterface = drawCardsDataAccessInterface;
    }

    //We can probably combine these three methods to a single execute(DrawCardsInputData drawCardsInputData) method
    //I don't think we have to make those methods that just calls the DataAccessInterface, we can just use the method directly from the DataAccessInterface

//    @Override
//    public NumberCardsDeck createNumberCardsDeck() {
//        return drawCardsDataAccessInterface.createNumberCardsDeck();
//    };

//    @Override
////    public void reshuffleNumberCardsDeck(DrawCardsInputData drawCardsInputData){
////        drawCardsDataAccessInterface.reshuffleNumberCardsDeck(drawCardsInputData.getNumberCardsDeck());
////    }

    @Override
    public void execute(DrawCardsInputData drawCardsInputData) {
        ArrayList<NumberCard> numCardsInfo;
        ArrayList<NumberCard> playerHands;

        if (drawCardsInputData.getDrawNumber() <= drawCardsInputData.getNumberCardsDeck().getRemainingCards()){
            numCardsInfo = drawCardsDataAccessInterface.drawNumberCards(drawCardsInputData.getNumberCardsDeck(), drawCardsInputData.getDrawNumber());
//            for (String numCardInfo : numCardsInfo){
            playerHands = drawCardsInputData.getPlayer().getNumberCards();
            playerHands.addAll(numCardsInfo);
//                //Todo: need Card Factory; Use Card Builder to create new Card and update each player's hands
//            }
            drawCardsInputData.getNumberCardsDeck().setRemainingCards(drawCardsInputData.getNumberCardsDeck().getRemainingCards() - drawCardsInputData.getDrawNumber());
        }
        else{
            int deckRemainingCards = drawCardsInputData.getNumberCardsDeck().getRemainingCards();
            numCardsInfo = drawCardsDataAccessInterface.drawNumberCards(drawCardsInputData.getNumberCardsDeck(), deckRemainingCards);
            drawCardsDataAccessInterface.reshuffleNumberCardsDeck(drawCardsInputData.getNumberCardsDeck());
            numCardsInfo.addAll(drawCardsDataAccessInterface.drawNumberCards(drawCardsInputData.getNumberCardsDeck(), drawCardsInputData.getDrawNumber() - deckRemainingCards));
//            for (String numCardInfo : numCardsInfo){
            playerHands = drawCardsInputData.getPlayer().getNumberCards();
            playerHands.addAll(numCardsInfo);
//                //Todo: need Card Factory; Use Card Builder to create new Card and update each player's hands
//            }
            drawCardsInputData.getNumberCardsDeck().setRemainingCards(drawCardsInputData.getNumberCardsDeck().getRemainingCards() - drawCardsInputData.getDrawNumber() + deckRemainingCards);
        }

    }
}
