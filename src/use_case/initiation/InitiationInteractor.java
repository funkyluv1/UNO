package use_case.initiation;

import Data_access.FileUserDataAccessObject;
import entities.Game;

public class InitiationInteractor implements InitiationInputBoundary {

    final FileUserDataAccessObject fileUserDataAccessObject;
    final InitiationOutputDataBoundary initiationOutputDataBoundary;

    public InitiationInteractor(FileUserDataAccessObject fileUserDataAccessObject, InitiationOutputDataBoundary initiationOutputDataBoundary){
        this.fileUserDataAccessObject = fileUserDataAccessObject;
        this.initiationOutputDataBoundary = initiationOutputDataBoundary;
    }

    /*InitiationController should call InitiationInteractor and DrawCardsInteractor
    * separately. InitiationView should be updated after both InitiationInteractor
    * and DrawCardsInteractor have executed.*/
    public void execute(InitiationInputData initiationInputData){
        Game game = new Game(initiationInputData.getPlayerNumber(), initiationInputData.players);
        fileUserDataAccessObject.create(); //Todo: finish this method
        initiationOutputDataBoundary.prepareNewGameView(new InitiationOutputData(game));
    };

}
