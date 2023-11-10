package use_case.PostTurn;

import entities.card.BombCard;
import entities.card.FunctionalCard;
import entities.card.HotPotatoCard;
import entities.card.NumberCard;
import use_case.drawcards.DrawCardsDataAccessInterface;

import java.util.ArrayList;

public class PostTurnInteractor implements PostTurnInputDataBoundary{
    final PostTurnOutputDataBoundary postTurnOutputDataBoundary;
    final DrawCardsDataAccessInterface dataAccessInterface;
    final PostTurnDataAccessInterface postTurnDataAccessInterface;

    public PostTurnInteractor(PostTurnOutputDataBoundary postTurnOutputDataBoundary, DrawCardsDataAccessInterface dataAccessInterface, PostTurnDataAccessInterface postTurnDataAccessInterface) {
        this.dataAccessInterface = dataAccessInterface;
        this.postTurnOutputDataBoundary = postTurnOutputDataBoundary;
        this.postTurnDataAccessInterface = postTurnDataAccessInterface;
    }

    @Override
    public void execute(PostTurnInputData inputData) {
        ArrayList<FunctionalCard> functionalCards = inputData.getFuncCards();
        ArrayList<NumberCard> numberCards = inputData.getNumberCards();
        for (FunctionalCard card: functionalCards){
            if (card instanceof BombCard) {
                numberCards.addAll(dataAccessInterface.drawNumberCards(inputData.getNumberCardsDeck(), 5));
                functionalCards.remove(card);
            } else if (card instanceof HotPotatoCard) {
                numberCards.addAll(dataAccessInterface.drawNumberCards(inputData.getNumberCardsDeck(), 2));
            }
        }
        postTurnDataAccessInterface.recordPostTurnChange(functionalCards, numberCards, inputData.getCurrentPlayer());
        PostTurnOutputData outputData = new PostTurnOutputData(numberCards, functionalCards, inputData.getCurrentPlayer());
        postTurnOutputDataBoundary.preparePostTurnView(outputData);
    }
}
