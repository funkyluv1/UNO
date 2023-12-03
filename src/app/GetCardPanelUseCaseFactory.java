package app;

import data_access.FileUserDataAccessObject;
import interface_adapter.Initialized.CardButtonPanelViewModel;
import interface_adapter.Initialized.FunCardButtonPanelState;
import interface_adapter.Initialized.FunCardButtonPanelViewModel;
import interface_adapter.Initialized.GetCardPanelViewModel;
import interface_adapter.SelectCard.SelectCardController;
import interface_adapter.Undo.UndoController;
import interface_adapter.Undo.UndoPresenter;
import interface_adapter.ViewManagerModel;
import use_case.Undo.UndoDataAccessInterface;
import use_case.Undo.UndoInputDataBoundary;
import use_case.Undo.UndoInteractor;
import use_case.Undo.UndoOutputDataBoundary;
import view.GetCardPanel;

import javax.swing.*;
import java.io.IOException;

public class GetCardPanelUseCaseFactory {
    private GetCardPanelUseCaseFactory(){}

    public static GetCardPanel create(
            ViewManagerModel viewManagerModel,
            GetCardPanelViewModel getCardPanelViewModel,
            CardButtonPanelViewModel cardButtonPanelViewModel,
            FunCardButtonPanelViewModel funCardButtonPanelViewModel,
            FileUserDataAccessObject fileUserDataAccessObject) {
        UndoController undoController = createUndoController(viewManagerModel, getCardPanelViewModel, cardButtonPanelViewModel, funCardButtonPanelViewModel,
                fileUserDataAccessObject);

        return new GetCardPanel(getCardPanelViewModel, undoController);
    }

    private static UndoController createUndoController(ViewManagerModel viewManagerModel,
                                                       GetCardPanelViewModel getCardPanelViewModel,
                                                       CardButtonPanelViewModel cardButtonPanelViewModel,
                                                       FunCardButtonPanelViewModel funCardButtonPanelViewModel,
                                                       FileUserDataAccessObject fileUserDataAccessObject) {
        UndoOutputDataBoundary undoOutputDataBoundary = new UndoPresenter(viewManagerModel, getCardPanelViewModel,cardButtonPanelViewModel, funCardButtonPanelViewModel);

        UndoDataAccessInterface undoDataAccessInterface = fileUserDataAccessObject;
        UndoInputDataBoundary undoInteractor = new UndoInteractor(undoOutputDataBoundary, undoDataAccessInterface);
        return new UndoController(undoInteractor);
    }
}