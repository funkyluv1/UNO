package interface_adapter.Initiation;

import entities.player.Player;
import use_case.initiation.InitiationInputBoundary;
import use_case.initiation.InitiationInputData;

public class InitiationController {
    final InitiationInputBoundary initiationInteractor;

    public InitiationController(InitiationInputBoundary initiationInteractor) {
        this.initiationInteractor = initiationInteractor;
    }

    public void execute(Player[] players) {
        InitiationInputData inputData = new InitiationInputData(players);
        initiationInteractor.execute(inputData);
    }
}