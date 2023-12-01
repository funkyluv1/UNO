package interface_adapter.preTurn;

import interface_adapter.ViewManagerModel;
import use_case.PreTurn.PreTurnOutputData;
import use_case.PreTurn.PreTurnOutputDataBoundary;

public class PreTurnPresenter implements PreTurnOutputDataBoundary {
    final private PreTurnViewModel preTurnViewModel;

    private ViewManagerModel viewManagerModel;

    public PreTurnPresenter(ViewManagerModel viewManagerModel, PreTurnViewModel preTurnViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.preTurnViewModel = preTurnViewModel;
    }

    @Override
    public void preparePreTurnView(PreTurnOutputData outputData) {
        PreTurnState preTurnState = preTurnViewModel.getState();
        preTurnState.setNumberCards(outputData.getNumberCards());

        preTurnViewModel.setState(preTurnState);
        //TODO: need to initialize all Panels
        viewManagerModel.setActiveView(preTurnViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
