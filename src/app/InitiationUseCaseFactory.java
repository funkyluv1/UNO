package app;

import Data_access.APIDataAccessObject;
import Data_access.FileUserDataAccessObject;
import interface_adapter.Initialized.InitializedViewModel;
import interface_adapter.Initiation.InitiationController;
import interface_adapter.Initiation.InitiationPresenter;
import interface_adapter.Initiation.InitiationViewModel;
import interface_adapter.ViewManagerModel;
import use_case.drawcards.DrawCardsDataAccessInterface;
import use_case.initiation.InitiationDataAccessInterface;
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
            ViewManagerModel viewManagerModel, InitiationViewModel initiationViewModel, InitializedViewModel initializedViewModel, FileUserDataAccessObject fileUserDataAccessObject) {

        try {
            InitiationController initiationController = createInitiationUseCase(viewManagerModel, initiationViewModel, initializedViewModel, fileUserDataAccessObject);
            return new InitiationView(initiationController, initiationViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static InitiationController createInitiationUseCase(ViewManagerModel viewManagerModel, InitiationViewModel initiationViewModel, InitializedViewModel initializedViewModel, FileUserDataAccessObject userDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        InitiationOutputDataBoundary initiationOutputDataBoundary = new InitiationPresenter(viewManagerModel, initiationViewModel, initializedViewModel);

        // enetity classe
        DrawCardsDataAccessInterface drawCardsDataAccessInterface= new APIDataAccessObject();

        InitiationInputDataBoundary initiationInteractor = new InitiationInteractor(
                userDataAccessObject, drawCardsDataAccessInterface, initiationOutputDataBoundary);

        return new InitiationController(initiationInteractor);
    }

}
