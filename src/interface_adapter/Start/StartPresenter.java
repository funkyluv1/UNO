package interface_adapter.Start;

import interface_adapter.Initiation.InitiationViewModel;
import interface_adapter.ViewManagerModel;
import use_case.Start.StartOutputDataBoundary;

public class StartPresenter implements StartOutputDataBoundary {
    private ViewManagerModel viewManagerModel;
    private final InitiationViewModel initiationViewModel;

    public StartPresenter(ViewManagerModel viewManagerModel, InitiationViewModel initiationViewModel){
        this.initiationViewModel = initiationViewModel;
        this.viewManagerModel = viewManagerModel;
    }
    @Override
    public void prepareInitializeView() {
        viewManagerModel.setActiveView(initiationViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
        System.out.println("change view");
    }
}
