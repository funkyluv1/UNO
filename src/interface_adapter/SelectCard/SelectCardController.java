package interface_adapter.SelectCard;

import use_case.SelectCard.SelectCardInputData;
import use_case.SelectCard.SelectCardInputDataBoundary;

public class SelectCardController {
    final SelectCardInputDataBoundary SelectCardInteractor;

    public SelectCardController(SelectCardInputDataBoundary SelectCardInteractor) {
        this.SelectCardInteractor = SelectCardInteractor;
    }

    public void execute(SelectCardInputData inputData){
        SelectCardInteractor.execute(inputData);
    }
}
