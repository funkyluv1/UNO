package interface_adapter.GetCard;

import interface_adapter.ViewManagerModel;
import use_case.GetCard.GetCardOutputData;
import use_case.GetCard.GetCardOutputDataBoundary;

public class GetCardPresenter implements GetCardOutputDataBoundary {

    private ViewManagerModel viewManagerModel;

    @Override
    public void prepareNewGameView(GetCardOutputData getCardOutputData) {

    }
}
