package use_case.initiation;

import Data_access.FileUserDataAccessObject;

public class InitiationInteractor implements InitiationInputBoundary {
    final FileUserDataAccessObject fileUserDataAccessObject;
    final InitiationOutputBoundary initiationOutputBoundary;

    public InitiationInteractor(FileUserDataAccessObject fileUserDataAccessObject, InitiationOutputBoundary initiationOutputBoundary){
        this.fileUserDataAccessObject = fileUserDataAccessObject;
        this.initiationOutputBoundary = initiationOutputBoundary;
    }

    public void execute(InitiationInputData initiationInputData){
        this.fileUserDataAccessObject.create();
        this.initiationOutputBoundary.prepareNewGameView();
    };

}
