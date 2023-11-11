package interface_adapter.Initiation;

import entities.player.Player;
import use_case.initiation.InitiationInputDataBoundary;
import use_case.initiation.InitiationInputData;

import java.util.ArrayList;

public class InitiationController {
    final InitiationInputDataBoundary initiationInteractor;

    public InitiationController(InitiationInputDataBoundary initiationInteractor) {
        this.initiationInteractor = initiationInteractor;
    }

    public void execute(ArrayList<String> players, int botNumber) {
        InitiationInputData inputData = new InitiationInputData(players, botNumber);
        initiationInteractor.execute(inputData);
    }
}