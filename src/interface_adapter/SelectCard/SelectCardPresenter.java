package interface_adapter.SelectCard;

import interface_adapter.Initialized.CardButtonPanelState;
import interface_adapter.Initialized.CardButtonPanelViewModel;
import interface_adapter.ViewManagerModel;
import use_case.SelectCard.SelectCardOutputData;
import use_case.SelectCard.SelectCardOutputDataBoundary;
import view.CardButtonPanel;

public class SelectCardPresenter implements SelectCardOutputDataBoundary {
    private final CardButtonPanelViewModel cardButtonPanelViewModel;
    private ViewManagerModel viewManagerModel;

    public SelectCardPresenter(ViewManagerModel viewManagerModel, CardButtonPanelViewModel cardButtonPanelViewModel){
        this.viewManagerModel = viewManagerModel;
        this.cardButtonPanelViewModel = cardButtonPanelViewModel;
    }

    @Override
    public void prepareSelectCardView(SelectCardOutputData selectCardOutputData) {
        CardButtonPanelState cardButtonPanelState = cardButtonPanelViewModel.getState();
        cardButtonPanelState.setButtonindexHighlighted(selectCardOutputData.getButton_index());

        cardButtonPanelState.setButton1enabled(false);
        cardButtonPanelState.setButton2enabled(false);
        cardButtonPanelState.setButton3enabled(false);

        cardButtonPanelViewModel.setState(cardButtonPanelState);
        cardButtonPanelViewModel.firePropertyChanged();
    }
}
