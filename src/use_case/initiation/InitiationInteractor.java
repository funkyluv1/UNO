package use_case.initiation;

import data_access.FileUserDataAccessObject;
import entities.Game;
import entities.NumberCardsDeck.NumberCardsDeck;
import entities.card.FunctionalCard;
import entities.card.NumberCard;
import use_case.DrawCards.DrawCardsDataAccessInterface;

import java.util.ArrayList;

public class InitiationInteractor implements InitiationInputDataBoundary {
    public static Game game;

    final FileUserDataAccessObject fileUserDataAccessObject;
    final InitiationOutputDataBoundary initiationOutputDataBoundary;
    final DrawCardsDataAccessInterface drawCardsDataAccessInterface;

    public InitiationInteractor(FileUserDataAccessObject fileUserDataAccessObject, DrawCardsDataAccessInterface drawCardsDataAccessInterface, InitiationOutputDataBoundary initiationOutputDataBoundary){
        this.fileUserDataAccessObject = fileUserDataAccessObject;
        this.initiationOutputDataBoundary = initiationOutputDataBoundary;
        this.drawCardsDataAccessInterface = drawCardsDataAccessInterface;
    }

        public void execute(InitiationInputData initiationInputData){
            int initialNumberCards = 5;
            NumberCardsDeck numberCardsDeck = drawCardsDataAccessInterface.createNumberCardsDeck();
            fileUserDataAccessObject.initiate(numberCardsDeck, initiationInputData);
            for (String playerName : initiationInputData.getPlayerNames()){
                ArrayList<NumberCard> numberCards = drawCardsDataAccessInterface.drawNumberCards(numberCardsDeck, initialNumberCards);
                fileUserDataAccessObject.savePlayerwithCards(playerName, numberCards, new ArrayList<FunctionalCard>());
            }
            game = Game.getInstance();
            initiationOutputDataBoundary.prepareNewGameView(new InitiationOutputData(initiationInputData.getPlayerNames(), numberCardsDeck));
    };

}
