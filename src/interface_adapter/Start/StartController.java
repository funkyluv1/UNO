package interface_adapter.Start;

import use_case.Start.StartInputDataBoundary;

public class StartController {
    final StartInputDataBoundary startInteractor;

    public StartController(StartInputDataBoundary startInteractor) {
        this.startInteractor = startInteractor;
    }

    public void execute(){
        startInteractor.execute();
    }
}
