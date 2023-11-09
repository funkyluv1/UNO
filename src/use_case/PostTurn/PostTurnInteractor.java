package use_case.PostTurn;

import entities.card.BombCard;
import entities.card.FunctionalCard;
import entities.card.HotPotatoCard;
import entities.card.NumberCard;
import use_case.drawcards.DrawCardsDataAccessInterface;

import java.util.ArrayList;
import java.util.function.Function;

public class PostTurnInteractor implements PostTurnInputDataBoundary{
    final PostTurnOutputDataBoundary postTurnOutputDataBoundary;
    final DrawCardsDataAccessInterface dataAccessInterface;

    public PostTurnInteractor(PostTurnOutputDataBoundary postTurnOutputDataBoundary, DrawCardsDataAccessInterface dataAccessInterface) {
        this.dataAccessInterface = dataAccessInterface;
        this.postTurnOutputDataBoundary = postTurnOutputDataBoundary;
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

        PostTurnOutputData outputData = new PostTurnOutputData(numberCards, functionalCards);
        postTurnOutputDataBoundary.preparePostTurnView(outputData);
    }
}
