package interface_adapter.DrawCards;

import interface_adapter.ViewManagerModel;
import use_case.DrawCards.DrawCardsOutputData;
import interface_adapter.DrawCards.DrawCardsViewModel;
import use_case.DrawCards.DrawCardsOutputDataBoundary;

public class DrawCardsPresenter implements DrawCardsOutputDataBoundary {
    private final DrawCardsViewModel drawCardsViewModel;
    private ViewManagerModel viewManagerModel;

    public DrawCardsPresenter(ViewManagerModel viewManagerModel, DrawCardsViewModel DrawCardsViewModel){
        this.viewManagerModel = viewManagerModel;
        this.drawCardsViewModel = DrawCardsViewModel;
    }

    @Override
    public void prepareSuccessView(DrawCardsOutputData drawCardsOutputData) {
        DrawCardsState drawCardsState = drawCardsViewModel.getDrawCardsState();
        drawCardsState.setNumberCards(drawCardsOutputData.getNumberCards());
        drawCardsViewModel.setDrawCardsState(drawCardsState);
        viewManagerModel.firePropertyChanged();
    }
}
