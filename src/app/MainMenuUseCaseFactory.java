package app;

import interface_adapter.Initiation.InitiationViewModel;
import interface_adapter.Start.StartController;
import interface_adapter.Start.StartPresenter;
import interface_adapter.Undo.UndoController;
import interface_adapter.ViewManagerModel;
import use_case.Start.StartInputDataBoundary;
import use_case.Start.StartInteractor;
import use_case.Start.StartOutputDataBoundary;
import view.MainMenuView;

import java.io.IOException;

public class MainMenuUseCaseFactory {
    private MainMenuUseCaseFactory() {
    }

    public static MainMenuView create(ViewManagerModel viewManagerModel, InitiationViewModel initiationViewModel){
        StartController startController = createStartController(viewManagerModel, initiationViewModel);

        return new MainMenuView(startController);
    }

    private static StartController createStartController(ViewManagerModel viewManagerModel, InitiationViewModel initiationViewModel) {
        StartOutputDataBoundary startOutputDataBoundary = new StartPresenter(viewManagerModel, initiationViewModel);
        StartInputDataBoundary startInteractor = new StartInteractor(startOutputDataBoundary);
        return new StartController(startInteractor);
    }
}
