package use_case.PostTurn;

import entities.card.*;
import entities.player.Player;
import use_case.drawcards.DrawCardsDataAccessInterface;

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

        FunctionalCard reward = generateFuncCard();
        Player winner = game.getCurrWinner();
        ArrayList<FunctionalCard> newHand = winner.getFuncCards();
        newHand.add(reward);
        winner.setFuncCards(newHand);

        postTurnDataAccessInterface.recordPostTurnChange(functionalCards, numberCards, inputData.getCurrentPlayer());
        PostTurnOutputData outputData = new PostTurnOutputData(numberCards, functionalCards, inputData.getCurrentPlayer());
        postTurnOutputDataBoundary.preparePostTurnView(outputData);
    }

    private FunctionalCard generateFuncCard() {
        int randIndex = (int) Math.floor(Math.random()*7);
        FunctionalCard result = null;
        String[] randColors = {"red", "blue", "green", "yellow"};
        int randColorIndex = (int) Math.floor(Math.random()*4);

        int randValue = (int) Math.floor(Math.random()*9);
        String randColor = randColors[randColorIndex];
        switch (randIndex) {
            case 0:
                result = new BombCard(randValue, randColor);
            case 1:
                result = new HotPotatoCard(randValue, randColor);
            case 2:
                result = new PlusFourCard();
            case 3:
                result = new PlusTwoCard();
            case 4:
                result = new RandomCard();
            case 5:
                result = new SkipCard();
            case 6:
                result = new WildCard();
        }
        return result;
    }
}
