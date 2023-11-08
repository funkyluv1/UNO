package use_case.initiation;

import data_access.FileUserDataAccessObject;
import entities.NumberCardsDeck.NumberCardsDeck;
import use_case.drawcards.DrawCardsDataAccessInterface;

public class InitiationInteractor implements InitiationInputDataBoundary {

    final FileUserDataAccessObject fileUserDataAccessObject;
    final InitiationOutputDataBoundary initiationOutputDataBoundary;
    final DrawCardsDataAccessInterface drawCardsDataAccessInterface;

    public InitiationInteractor(FileUserDataAccessObject fileUserDataAccessObject, DrawCardsDataAccessInterface drawCardsDataAccessInterface, InitiationOutputDataBoundary initiationOutputDataBoundary){
        this.fileUserDataAccessObject = fileUserDataAccessObject;
        this.initiationOutputDataBoundary = initiationOutputDataBoundary;
        this.drawCardsDataAccessInterface = drawCardsDataAccessInterface;
    }

    /*InitiationController should call InitiationInteractor and DrawCardsInteractor
    * separately. InitiationView should be updated after both InitiationInteractor
    * and DrawCardsInteractor have executed.*/
    public void execute(InitiationInputData initiationInputData){
        NumberCardsDeck numberCardsDeck = drawCardsDataAccessInterface.createNumberCardsDeck();
        fileUserDataAccessObject.create(numberCardsDeck, initiationInputData); //Todo: finish this method
        initiationOutputDataBoundary.prepareNewGameView(new InitiationOutputData(initiationInputData.getPlayerNames()));
    };

}
