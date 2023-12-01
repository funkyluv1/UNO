package interface_adapter.GetCard;

import entities.card.NumberCard;
import use_case.GetCard.GetCardInputData;
import use_case.GetCard.GetCardInputDataBoundary;

public class GetCardController {

    final GetCardInputDataBoundary getCardInteractor;


    public GetCardController(GetCardInputDataBoundary getCardInteractor) {
        this.getCardInteractor = getCardInteractor;
    }

    public void execute(String player) {
        GetCardInputData inputData = new GetCardInputData(player);
        this.getCardInteractor.execute(inputData);
    }

}
