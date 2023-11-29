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


        if (currPlayerIndex == 3) {
            FunctionalCard reward = generateFuncCard();
            Player winner = game.getCurrWinner();
            ArrayList<FunctionalCard> newHand = winner.getFuncCards();
            newHand.add(reward);
            winner.setFuncCards(newHand);

            game.setMaxCardNum(-1);
        }

        postTurnDataAccessInterface.recordPostTurnChange(functionalCards, numberCards, inputData.getCurrentPlayer());
        PostTurnOutputData outputData = new PostTurnOutputData(numberCards, functionalCards, inputData.getCurrentPlayer());
        postTurnOutputDataBoundary.preparePostTurnView(outputData);
    }

    private FunctionalCard generateFuncCard() {
        int randIndex = (int) Math.floor(Math.random()*7);
        String[] randColors = {"red", "blue", "green", "yellow"};
        int randColorIndex = (int) Math.floor(Math.random()*4);

        int randValue = (int) Math.floor(Math.random()*9);
        String randColor = randColors[randColorIndex];
        switch (randIndex) {
            case 0:
                return new BombCard(randValue, randColor);
            case 1:
                return new HotPotatoCard(randValue, randColor);
            case 2:
                return new PlusFourCard();
            case 3:
                return new PlusTwoCard();
            case 4:
                return new RandomCard();
            case 5:
                return new SkipCard();
            case 6:
                return new WildCard();
        }
        return new WildCard();
    }
}
