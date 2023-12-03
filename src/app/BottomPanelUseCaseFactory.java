package app;

import interface_adapter.Confirm.ConfirmController;
import interface_adapter.Confirm.ConfirmPresenter;
import interface_adapter.Initialized.BottomPanelViewModel;
import interface_adapter.Initialized.CardButtonPanelViewModel;
import interface_adapter.Initialized.FunCardButtonPanelViewModel;
import interface_adapter.Initialized.GetCardPanelViewModel;
import interface_adapter.ViewManagerModel;
import use_case.Confirm.*;
import view.BottomPanel;

import javax.swing.*;
import java.io.IOException;

public class BottomPanelUseCaseFactory {
    private BottomPanelUseCaseFactory() {}
    public static BottomPanel create(
            ViewManagerModel viewManagerModel,
            BottomPanelViewModel bottomPanelViewModel,
            CardButtonPanelViewModel cardButtonPanelViewModel,
            FunCardButtonPanelViewModel funCardButtonPanelViewModel, GetCardPanelViewModel getCardPanelViewModel,
            ConfirmDataAccessInterface confirmDataAccessInterface) {

        try {
            ConfirmController confirmController = createConfirmController(viewManagerModel, bottomPanelViewModel, cardButtonPanelViewModel,
                    funCardButtonPanelViewModel, confirmDataAccessInterface, getCardPanelViewModel);
            return new BottomPanel(bottomPanelViewModel, confirmController);
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static ConfirmController createConfirmController(ViewManagerModel viewManagerModel, BottomPanelViewModel bottomPanelViewModel,
                                                             CardButtonPanelViewModel cardButtonPanelViewModel, FunCardButtonPanelViewModel funCardButtonPanelViewModel,
                                                             ConfirmDataAccessInterface confirmDataAccessInterface, GetCardPanelViewModel getCardPanelViewModel) throws IOException {

        ConfirmOutputDataBoundary confirmOutputDataBoundary = new ConfirmPresenter(viewManagerModel, bottomPanelViewModel, cardButtonPanelViewModel, funCardButtonPanelViewModel, getCardPanelViewModel);

        ConfirmInputDataBoundary confirmInputDataBoundary = new ConfirmInteractor(confirmOutputDataBoundary, confirmDataAccessInterface);

        return new ConfirmController(confirmInputDataBoundary);
    }


}
