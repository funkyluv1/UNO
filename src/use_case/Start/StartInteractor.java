package use_case.Start;

import use_case.SelectFuncCard.SelectFuncCardInputData;
import use_case.SelectFuncCard.SelectFuncCardInputDataBoundary;

public class StartInteractor implements StartInputDataBoundary {
    final StartOutputDataBoundary startOutputDataBoundary;

    public StartInteractor(StartOutputDataBoundary startOutputDataBoundary){
        this.startOutputDataBoundary = startOutputDataBoundary;
    }

    @Override
    public void execute() {
        startOutputDataBoundary.prepareInitializeView();
    }
}
