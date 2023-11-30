package app;

import data_access.APIDataAccessObject;
import data_access.FileUserDataAccessObject;
import interface_adapter.Initialized.CardButtonPanelViewModel;
import interface_adapter.Initialized.InitializedViewModel;
import interface_adapter.Initiation.InitiationController;
import interface_adapter.Initiation.InitiationPresenter;
import interface_adapter.Initiation.InitiationViewModel;
import interface_adapter.RightShift.RightShiftController;
import interface_adapter.RightShift.RightShiftPresenter;
import interface_adapter.SelectCard.SelectCardController;
import interface_adapter.SelectCard.SelectCardPresenter;
import interface_adapter.ViewManagerModel;
import use_case.PreTurn.FindPlayableCardsInterface;
import use_case.DrawCards.DrawCardsDataAccessInterface;
import use_case.RightShift.RightShiftDataAccessInterface;
import use_case.RightShift.RightShiftInputDataBoundary;
import use_case.RightShift.RightShiftInteractor;
import use_case.RightShift.RightShiftOutputDataBoundary;
import use_case.SelectCard.SelectCardDataAccessInterface;
import use_case.SelectCard.SelectCardInputDataBoundary;
import use_case.SelectCard.SelectCardInteractor;
import use_case.SelectCard.SelectCardOutputDataBoundary;
import use_case.initiation.InitiationDataAccessInterface;
import use_case.initiation.InitiationInputDataBoundary;
import use_case.initiation.InitiationInteractor;
import use_case.initiation.InitiationOutputDataBoundary;
import view.CardButtonPanel;
import view.InitiationView;

import javax.swing.*;
import java.io.IOException;

public class CardButtonPanelUseCaseFactory {

    /** Prevent instantiation. */
    private CardButtonPanelUseCaseFactory() {}

    public static CardButtonPanel create(
            ViewManagerModel viewManagerModel,
            CardButtonPanelViewModel cardButtonPanelViewModel,
            FileUserDataAccessObject fileUserDataAccessObject) {

        try {
            SelectCardController selectCardController = createSelectCardController(viewManagerModel,
                    cardButtonPanelViewModel, fileUserDataAccessObject);
            RightShiftController rightShiftController = createRightShiftController(viewManagerModel,
                    cardButtonPanelViewModel, fileUserDataAccessObject);
            return new CardButtonPanel(cardButtonPanelViewModel, selectCardController, rightShiftController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static SelectCardController createSelectCardController(ViewManagerModel viewManagerModel,
                                                                CardButtonPanelViewModel cardButtonPanelViewModel,
                                                                FileUserDataAccessObject userDataAccessObject) throws IOException {

        SelectCardOutputDataBoundary selectCardOutputDataBoundary = new SelectCardPresenter(viewManagerModel,
                cardButtonPanelViewModel);

        SelectCardInputDataBoundary selectCardInteractor = new SelectCardInteractor(selectCardOutputDataBoundary);

        return new SelectCardController(selectCardInteractor);
    }

    private static RightShiftController createRightShiftController(ViewManagerModel viewManagerModel,
                                                                   CardButtonPanelViewModel cardButtonPanelViewModel,
                                                                   FileUserDataAccessObject userDataAccessObject) throws IOException {

        RightShiftOutputDataBoundary rightShiftOutputDataBoundary = new RightShiftPresenter(viewManagerModel,
                cardButtonPanelViewModel);

        RightShiftDataAccessInterface rightShiftDataAccessInterface = userDataAccessObject;

        RightShiftInputDataBoundary rightShiftInteractor = new RightShiftInteractor(rightShiftOutputDataBoundary, rightShiftDataAccessInterface);

        return new RightShiftController(rightShiftInteractor);
    }

}
