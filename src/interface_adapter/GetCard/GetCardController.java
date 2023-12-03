package interface_adapter.GetCard;

import entities.card.NumberCard;
import use_case.GetCard.GetCardInputData;
import use_case.GetCard.GetCardInputDataBoundary;

public class GetCardController {

    final GetCardInputDataBoundary getCardInteractor;


    public GetCardController(GetCardInputDataBoundary getCardInteractor) {
        this.getCardInteractor = getCardInteractor;
    }

    public void execute(int index) {
        GetCardInputData inputData = new GetCardInputData(index);
        this.getCardInteractor.execute(inputData);
    }

}
