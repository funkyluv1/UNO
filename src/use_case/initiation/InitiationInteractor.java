package use_case.initiation;

import data_access.FileUserDataAccessObject;
import entities.Game;
import entities.NumberCardsDeck.NumberCardsDeck;
import entities.card.FunctionalCard;
import entities.card.NumberCard;
import use_case.PreTurn.FindPlayableCardsInterface;
import use_case.DrawCards.DrawCardsDataAccessInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InitiationInteractor implements InitiationInputDataBoundary {
    public static Game game;
    final FileUserDataAccessObject fileUserDataAccessObject;
    final InitiationOutputDataBoundary initiationOutputDataBoundary;
    final DrawCardsDataAccessInterface drawCardsDataAccessInterface;
    final FindPlayableCardsInterface findPlayableCardsInterface;

    public InitiationInteractor(FileUserDataAccessObject fileUserDataAccessObject,
                                DrawCardsDataAccessInterface drawCardsDataAccessInterface,
                                InitiationOutputDataBoundary initiationOutputDataBoundary,
                                FindPlayableCardsInterface findPlayableCardsInterface){
        this.fileUserDataAccessObject = fileUserDataAccessObject;
        this.initiationOutputDataBoundary = initiationOutputDataBoundary;
        this.drawCardsDataAccessInterface = drawCardsDataAccessInterface;
        this.findPlayableCardsInterface = findPlayableCardsInterface;
    }

    public void execute(InitiationInputData initiationInputData){
        Map<String, ArrayList<NumberCard>> playerNumCards = new HashMap<>();
        Map<String, ArrayList<NumberCard>> playerPlayableNumCards = new HashMap<>();
        Map<String, ArrayList<FunctionalCard>> playerPlayableFunCards = new HashMap<>();
        Map<String, Integer> displayCardsFirstIndex = new HashMap<>();

        int initialNumberCards = 5;
        NumberCardsDeck numberCardsDeck = drawCardsDataAccessInterface.createNumberCardsDeck();
        fileUserDataAccessObject.initiate(numberCardsDeck, initiationInputData);
        for (String playerName : initiationInputData.getPlayerNames()){
            ArrayList<NumberCard> numberCards = drawCardsDataAccessInterface.drawNumberCards(numberCardsDeck, initialNumberCards);
            playerNumCards.put(playerName, numberCards);
            playerPlayableFunCards.put(playerName, new ArrayList<FunctionalCard>());
            int displayCardsFirstIndexData = fileUserDataAccessObject.getPlayerDisplayFirstCardIndex(playerName);
            displayCardsFirstIndex.put(playerName, displayCardsFirstIndexData);
            fileUserDataAccessObject.savePlayerwithCards(playerName, numberCards, new ArrayList<FunctionalCard>(),  displayCardsFirstIndexData);
        }
        game = Game.getInstance();
        for (String player : playerNumCards.keySet()){
            playerPlayableNumCards.put(player,
                    findPlayableCardsInterface.findPlayableNumberCards(game.getTopCard().getColor(),playerNumCards.get(player)));
        }
        initiationOutputDataBoundary.prepareNewGameView(new InitiationOutputData(initiationInputData.getPlayerNames(), numberCardsDeck,
                playerNumCards, playerPlayableNumCards, playerPlayableFunCards,  displayCardsFirstIndex));
    };

}