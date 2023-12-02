package app;

import interface_adapter.Confirm.ConfirmController;
import interface_adapter.Confirm.ConfirmPresenter;
import interface_adapter.Initialized.BottomPanelViewModel;
import interface_adapter.Initialized.CardButtonPanelViewModel;
import interface_adapter.ViewManagerModel;
import use_case.Confirm.ConfirmInputData;
import use_case.Confirm.ConfirmInputDataBoundary;
import use_case.Confirm.ConfirmInteractor;
import use_case.Confirm.ConfirmOutputDataBoundary;
import view.BottomPanel;

import javax.swing.*;
import java.io.IOException;

public class BottomPanelUseCaseFactory {
    private BottomPanelUseCaseFactory() {}
    public static BottomPanel create(
            ViewManagerModel viewManagerModel,
            BottomPanelViewModel bottomPanelViewModel, CardButtonPanelViewModel cardButtonPanelViewModel, int currPlayerIndex, String currPlayer) {

        try {
            ConfirmController confirmController = createConfirmController(viewManagerModel, bottomPanelViewModel, cardButtonPanelViewModel, currPlayerIndex, currPlayer);
            return new BottomPanel(bottomPanelViewModel, confirmController);
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static ConfirmController createConfirmController(ViewManagerModel viewManagerModel, BottomPanelViewModel bottomPanelViewModel, CardButtonPanelViewModel cardButtonPanelViewModel, int currPlayerIndex, String currPlayer) throws IOException {

        ConfirmOutputDataBoundary confirmOutputDataBoundary = new ConfirmPresenter(viewManagerModel, bottomPanelViewModel, cardButtonPanelViewModel);

        ConfirmInputDataBoundary confirmInputDataBoundary = new ConfirmInteractor(confirmOutputDataBoundary);

        ConfirmInputData confirmInputData = new ConfirmInputData(currPlayerIndex, currPlayer);

        return new ConfirmController(confirmInputDataBoundary, confirmInputData);
    }


}
