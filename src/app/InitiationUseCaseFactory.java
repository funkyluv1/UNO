package app;

import interface_adapter.Initialized.InitializedViewModel;
import interface_adapter.Initiation.InitiationController;
import interface_adapter.Initiation.InitiationPresenter;
import interface_adapter.Initiation.InitiationViewModel;
import interface_adapter.ViewManagerModel;
import use_case.initiation.InitiationInputDataBoundary;
import use_case.initiation.InitiationInteractor;
import use_case.initiation.InitiationOutputDataBoundary;
import view.InitiationView;

import javax.swing.*;
import java.io.IOException;

public class InitiationUseCaseFactory {

    /** Prevent instantiation. */
    private InitiationUseCaseFactory() {}

    public static InitiationView create(
            ViewManagerModel viewManagerModel, InitiationViewModel initiationViewModel, InitializedViewModel initializedViewModel) {

        try {
            InitiationController initiationController = createInitiationUseCase(viewManagerModel, initiationViewModel, initializedViewModel);
            return new InitiationView(initiationController, initiationViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static InitiationController createInitiationUseCase(ViewManagerModel viewManagerModel, InitiationViewModel initiationViewModel, InitializedViewModel initializedViewModel) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        InitiationOutputDataBoundary initiationOutputDataBoundary = new InitiationPresenter(viewManagerModel, initiationViewModel, initializedViewModel);

        // enetity classes

        InitiationInputDataBoundary userInitiationInteractor = new InitiationInteractor(
                userDataAccessObject,initiationOutputDataBoundary);

        return new InitiationController(initiationInteractor);
    }

}
