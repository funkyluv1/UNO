package use_case.PreTurn;

import data_access.FileUserDataAccessObject;
import entities.card.*;
import entities.player.Player;
import use_case.drawcards.DrawCardsDataAccessInterface;

import java.util.ArrayList;

import static use_case.initiation.InitiationInteractor.game;

public class PreTurnInteractor implements PreTurnInputDataBoundary {
    final PreTurnOutputDataBoundary preTurnOutputDataBoundary;
    final DrawCardsDataAccessInterface dataAccessInterface;
    final PreTurnDataAccessInterface preTurnDataAccessInterface;

    public PreTurnInteractor(PreTurnOutputDataBoundary preTurnOutputDataBoundary,
                             DrawCardsDataAccessInterface dataAccessInterface,
                             PreTurnDataAccessInterface preTurnDataAccessInterface) {
        this.dataAccessInterface = dataAccessInterface;
        this.preTurnOutputDataBoundary = preTurnOutputDataBoundary;
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

        if (game.getPlusN() > 0 && !hasPlusCard) //previousPlayer played a plusCard, and the currentPlayer doesnt have a plus card
            {
            numberCards.addAll(dataAccessInterface.drawNumberCards(currentInputData.getNumberCardsDeck(), game.getPlusN()));
            preTurnDataAccessInterface.recordPreTurnChange(numberCards, currentPlayer.getPlayerName());
            game.setPlusN(0);
            //TODO: skip and turn to the next Player's PreTurn
        }
        else if (game.getSkipped()) {
            //TODO: skip and turn to the next Player's Preturn
            game.setSkipped(false);
        }
        else {
            //TODO: highlight PlayableCard
            PreTurnOutputData outputData = new PreTurnOutputData(numberCards, currentPlayer.getPlayerName());
            preTurnOutputDataBoundary.preparePreTurnView(outputData);
        }
    }
}
