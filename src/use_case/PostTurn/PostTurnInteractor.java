package use_case.PostTurn;

import entities.card.*;
import entities.player.Player;
import use_case.DrawCards.DrawCardsDataAccessInterface;

import java.util.ArrayList;

import static use_case.initiation.InitiationInteractor.game;

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
        int currPlayerIndex = inputData.getCurrPlayerIndex();
        ArrayList<FunctionalCard> functionalCards = inputData.getFuncCards();
        ArrayList<NumberCard> numberCards = inputData.getNumberCards();
        for (FunctionalCard card: functionalCards){
            if (card instanceof BombCard) {
                numberCards.addAll(dataAccessInterface.drawNumberCards(inputData.getNumberCardsDeck(), 5));
                functionalCards.remove(card);
            }
            else if (card instanceof HotPotatoCard) {
                numberCards.addAll(dataAccessInterface.drawNumberCards(inputData.getNumberCardsDeck(), card.getValue()));
            }
        }

        String winnerClarification = "";

        if (currPlayerIndex == 3) {
            Object [] randGenerate = generateFuncCard();
            FunctionalCard reward = (FunctionalCard) randGenerate[0];
            String cardName = (String) randGenerate[1];
            int winnerIndex = game.getCurrWinnerIndex();
            postTurnDataAccessInterface.recordRoundChange(winnerIndex, reward);

            game.setMaxCardNum(-1);
            game.setCurrWinner(0);

            winnerClarification = "Player " + (winnerIndex + 1) + " received a " + cardName + "!";
        }

        postTurnDataAccessInterface.recordPostTurnChange(functionalCards, numberCards, inputData.getCurrentPlayer());
        PostTurnOutputData outputData = new PostTurnOutputData(numberCards, functionalCards, inputData.getCurrentPlayer(), winnerClarification);
        postTurnOutputDataBoundary.preparePostTurnView(outputData);
    }

    private Object[] generateFuncCard() {
        int randIndex = (int) Math.floor(Math.random()*7);
        String[] randColors = {"red", "blue", "green", "yellow"};
        int randColorIndex = (int) Math.floor(Math.random()*4);

        int randValue = (int) Math.floor(Math.random()*9);
        String randColor = randColors[randColorIndex];

        Object[] result = new Object[2];
        switch (randIndex) {
            case 0 -> {
                result[0] = new BombCard(randValue, randColor);
                result[1] = "Bomb Card";
                return result;
            }
            case 1 -> {
                result[0] = new HotPotatoCard(randValue, randColor);
                result[1] = "Hot Potato Card";
                return result;
            }
            case 2 -> {
                result[0] = new PlusFourCard();
                result[1] = "Plus Four Card";
                return result;
            }
            case 3 -> {
                result[0] = new PlusTwoCard();
                result[1] = "Plus Two Card";
                return result;
            }
            case 4 -> {
                result[0] = new RandomCard();
                result[1] = "Random Card";
                return result;
            }
            case 5 -> {
                result[0] = new SkipCard();
                result[1] = "Skip Card";
                return result;
            }
        }
        result[0] = new WildCard();
        result[1] = "Wild Card";
        return result;
    }
}
