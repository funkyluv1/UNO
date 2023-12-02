    package interface_adapter.SelectFuncCard;

    import interface_adapter.Initialized.CardButtonPanelState;
    import interface_adapter.Initialized.CardButtonPanelViewModel;
    import interface_adapter.Initialized.FunCardButtonPanelState;
    import interface_adapter.Initialized.FunCardButtonPanelViewModel;
    import interface_adapter.ViewManagerModel;
    import use_case.SelectCard.SelectCardOutputData;
    import use_case.SelectCard.SelectCardOutputDataBoundary;
    import use_case.SelectFuncCard.SelectFuncCardOutputData;
    import use_case.SelectFuncCard.SelectFuncCardOutputDataBoundary;

    public class SelectFuncCardPresenter implements SelectFuncCardOutputDataBoundary {
    private final FunCardButtonPanelViewModel funCardButtonPanelViewModel;
    private ViewManagerModel viewManagerModel;

    public SelectFuncCardPresenter(ViewManagerModel viewManagerModel, FunCardButtonPanelViewModel funCardButtonPanelViewModel){
        this.viewManagerModel = viewManagerModel;
        this.funCardButtonPanelViewModel = funCardButtonPanelViewModel;
    }

    @Override
    public void prepareSelectCardView(SelectFuncCardOutputData selectFuncCardOutputData) {
        FunCardButtonPanelState funCardButtonPanelState = funCardButtonPanelViewModel.getState();
        funCardButtonPanelState.set_Selected_Fun_Cards(selectFuncCardOutputData.getSelectedCards());
        funCardButtonPanelState.setDisplayNumCardsFirstIndex(selectFuncCardOutputData.getButton_Index());

        funCardButtonPanelViewModel.setState(funCardButtonPanelState);
        funCardButtonPanelViewModel.firePropertyChanged();

        viewManagerModel.setActiveView("Initialized");
        viewManagerModel.firePropertyChanged();
    }
}
