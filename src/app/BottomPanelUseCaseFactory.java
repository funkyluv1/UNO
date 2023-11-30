package app;

import data_access.FileUserDataAccessObject;
import interface_adapter.Confirm.ConfirmController;
import interface_adapter.Confirm.ConfirmPresenter;
import interface_adapter.Initialized.BottomPanelViewModel;
import interface_adapter.Initialized.CardButtonPanelViewModel;
import interface_adapter.SelectCard.SelectCardController;
import interface_adapter.SelectCard.SelectCardPresenter;
import interface_adapter.ViewManagerModel;
import use_case.Confirm.ConfirmInputDataBoundary;
import use_case.Confirm.ConfirmInteractor;
import use_case.Confirm.ConfirmOutputDataBoundary;
import use_case.SelectCard.SelectCardInputDataBoundary;
import use_case.SelectCard.SelectCardInteractor;
import use_case.SelectCard.SelectCardOutputDataBoundary;
import view.BottomPanel;
import view.CardButtonPanel;

import javax.swing.*;
import java.io.IOException;

public class BottomPanelUseCaseFactory {
    private BottomPanelUseCaseFactory() {}
    public static BottomPanel create(
            ViewManagerModel viewManagerModel,
            BottomPanelViewModel bottomPanelViewModel) {

        try {
            ConfirmController confirmController = createConfirmController(viewManagerModel, bottomPanelViewModel);
            return new BottomPanel(bottomPanelViewModel, confirmController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static ConfirmController createConfirmController(ViewManagerModel viewManagerModel, BottomPanelViewModel bottomPanelViewModel) throws IOException {

        ConfirmOutputDataBoundary confirmOutputDataBoundary = new ConfirmPresenter(viewManagerModel, bottomPanelViewModel);

        ConfirmInputDataBoundary confirmInputDataBoundary = new ConfirmInteractor(confirmOutputDataBoundary);

        return new ConfirmController(confirmInputDataBoundary);
    }


}
