package use_case.PreTurn;

import entities.card.*;
import use_case.drawcards.DrawCardsDataAccessInterface;

import java.util.ArrayList;

public class PreTurnInteractor implements PreTurnInputDataBoundary {
    final PreTurnOutputDataBoundary preTurnOutputDataBoundary;
    final DrawCardsDataAccessInterface dataAccessInterface;
    final PreTurnDataAccessInterface preTurnDataAccessInterface;

    public PreTurnInteractor(PreTurnOutputDataBoundary preTurnOutputDataBoundary, DrawCardsDataAccessInterface dataAccessInterface, PreTurnDataAccessInterface preTurnDataAccessInterface) {
        this.dataAccessInterface = dataAccessInterface;
        this.preTurnOutputDataBoundary = preTurnOutputDataBoundary;
        this.preTurnDataAccessInterface = preTurnDataAccessInterface;
    }

    @Override
    public void execute(PreTurnInputData inputData) {
        ArrayList<NumberCard> numberCards = preTurnDataAccessInterface.getNumberCards(inputData.getCurrentPlayer());

        if (inputData.getPlusN() > 0) {
            numberCards.addAll(dataAccessInterface.drawNumberCards(inputData.getNumberCardsDeck(), inputData.getPlusN()));
            preTurnDataAccessInterface.recordPreTurnChange(numberCards, inputData.getCurrentPlayer());
        }
        if (inputData.isSkipped()) {
            //methods to skip the player
            preTurnDataAccessInterface.recordSkip();
        }


        PreTurnOutputData outputData = new PreTurnOutputData(numberCards, inputData.getCurrentPlayer());
        preTurnOutputDataBoundary.preparePreTurnView(outputData);
    }
}
