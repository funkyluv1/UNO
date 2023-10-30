package interface_adapter.Initiation;

import use_case.initiation.InitiationInputBoundary;

public class InitiationController {
    final InitiationInputBoundary initiationInteractor;

    public InitiationController(InitiationInputBoundary initiationInteractor) {
        this.initiationInteractor = initiationInteractor;
    }

    public void execute() {initiationInteractor.execute(); }
}
