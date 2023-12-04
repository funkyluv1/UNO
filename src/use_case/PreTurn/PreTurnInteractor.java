package use_case.PreTurn;

import entities.NumberCardsDeck.NumberCardsDeck;
import entities.card.*;
import entities.player.Player;
import use_case.DrawCards.DrawCardsDataAccessInterface;

import java.util.ArrayList;

import static use_case.initiation.InitiationInteractor.game;

public class PreTurnInteractor implements PreTurnInputDataBoundary {
    // final PreTurnOutputDataBoundary preTurnOutputDataBoundary;
    final DrawCardsDataAccessInterface dataAccessInterface;
    final PreTurnDataAccessInterface preTurnDataAccessInterface;

    public PreTurnInteractor(DrawCardsDataAccessInterface dataAccessInterface,
                             PreTurnDataAccessInterface preTurnDataAccessInterface) {
        // this.preTurnOutputDataBoundary = preTurnOutputDataBoundary;
        this.dataAccessInterface = dataAccessInterface;
        this.preTurnDataAccessInterface = preTurnDataAccessInterface;
    }

    @Override
    public void execute(PreTurnInputData currentInputData) {
        Player currentPlayer = preTurnDataAccessInterface.getPlayer(currentInputData.getCurrentPlayerIndex());
        ArrayList<NumberCard> numberCards = preTurnDataAccessInterface.getNumberCards(currentPlayer.getPlayerName());
        ArrayList<FunctionalCard> functionalCards = preTurnDataAccessInterface.getFunctionalCards(currentPlayer.getPlayerName());
        ArrayList<FunctionalCard> plusCards = new ArrayList<>();
        boolean hasPlusCard = false;

        for (FunctionalCard functionalCard : functionalCards){
            if (functionalCard.getType().equals("PlusTwo") || functionalCard.getType().equals("PlusFour")){
                plusCards.add(functionalCard);
                hasPlusCard = true;
            }
        }

        NumberCardsDeck numberCardsDeck = game.getNumberCardDeck();
        PreTurnInputData nextInputData = new PreTurnInputData(currentInputData.getCurrentPlayerIndex() + 1);
        if (game.getPlusN() > 0 && !hasPlusCard) { //previousPlayer played a plusCard, and the currentPlayer doesnt have a plus card
            numberCards.addAll(dataAccessInterface.drawNumberCards(numberCardsDeck, game.getPlusN()));
            preTurnDataAccessInterface.recordPreTurnChange(numberCards, currentPlayer.getPlayerName());
            game.setPlusN(0);
            game.updateCurrentPlayerIndex();
            execute(nextInputData);
        }
        else if (game.getSkipped()) {
            game.setSkipped(false);
            game.updateCurrentPlayerIndex();
            execute(nextInputData);
        }
        else {
            PreTurnOutputData outputData = new PreTurnOutputData(numberCards, currentPlayer.getPlayerName());
        }
    }
}