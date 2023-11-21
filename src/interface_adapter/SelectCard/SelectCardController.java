package interface_adapter.SelectCard;

import use_case.SelectCard.SelectCardInputData;
import use_case.SelectCard.SelectCardInputBoundary;

public class SelectCardController {

    final SelectCardInputBoundary selectCardInteractor;

    public SelectCardController (SelectCardInputBoundary selectCardInteractor) {
        this.selectCardInteractor = selectCardInteractor;
    }
}
