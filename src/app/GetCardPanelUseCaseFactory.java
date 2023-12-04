package app;

import data_access.APIDataAccessObject;
import data_access.FileUserDataAccessObject;
import interface_adapter.GetCard.GetCardController;
import interface_adapter.GetCard.GetCardPresenter;
import interface_adapter.Initialized.*;
import interface_adapter.SelectCard.SelectCardController;
import interface_adapter.Undo.UndoController;
import interface_adapter.Undo.UndoPresenter;
import interface_adapter.ViewManagerModel;
import use_case.DrawCards.DrawCardsDataAccessInterface;
import use_case.GetCard.GetCardDataAccessInterface;
import use_case.GetCard.GetCardInputDataBoundary;
import use_case.GetCard.GetCardInteractor;
import use_case.GetCard.GetCardOutputDataBoundary;
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
            BottomPanelViewModel bottomPanelViewModel,
            APIDataAccessObject apiDataAccessObject,
            FileUserDataAccessObject fileUserDataAccessObject) {
        UndoController undoController = createUndoController(viewManagerModel, getCardPanelViewModel, cardButtonPanelViewModel, funCardButtonPanelViewModel,
                bottomPanelViewModel,fileUserDataAccessObject);
        GetCardController getCardController = createGetCardController(viewManagerModel, bottomPanelViewModel, getCardPanelViewModel, apiDataAccessObject, cardButtonPanelViewModel, fileUserDataAccessObject);
        return new GetCardPanel(getCardPanelViewModel, undoController, getCardController, bottomPanelViewModel, cardButtonPanelViewModel);
    }

    private static UndoController createUndoController(ViewManagerModel viewManagerModel,
                                                       GetCardPanelViewModel getCardPanelViewModel,
                                                       CardButtonPanelViewModel cardButtonPanelViewModel,
                                                       FunCardButtonPanelViewModel funCardButtonPanelViewModel,
                                                       BottomPanelViewModel bottomPanelViewModel,
                                                       FileUserDataAccessObject fileUserDataAccessObject) {
        UndoOutputDataBoundary undoOutputDataBoundary = new UndoPresenter(viewManagerModel, getCardPanelViewModel,
                cardButtonPanelViewModel, funCardButtonPanelViewModel, bottomPanelViewModel);

        UndoDataAccessInterface undoDataAccessInterface = fileUserDataAccessObject;
        UndoInputDataBoundary undoInteractor = new UndoInteractor(undoOutputDataBoundary, undoDataAccessInterface);
        return new UndoController(undoInteractor);
    }

    private static GetCardController createGetCardController(ViewManagerModel viewManagerModel,
                                                       BottomPanelViewModel bottomPanelViewModel,
                                                       GetCardPanelViewModel getCardPanelViewModel,
                                                       APIDataAccessObject apiDataAccessObject,
                                                       CardButtonPanelViewModel cardButtonPanelViewModel,
                                                       FileUserDataAccessObject fileUserDataAccessObject) {
        GetCardOutputDataBoundary getCardOutputDataBoundary = new GetCardPresenter(viewManagerModel,getCardPanelViewModel, cardButtonPanelViewModel, bottomPanelViewModel
        );

        GetCardDataAccessInterface getCardDataAccessInterface = fileUserDataAccessObject;
        DrawCardsDataAccessInterface drawCardsDataAccessInterface = apiDataAccessObject;
        GetCardInputDataBoundary getCardInteractor = new GetCardInteractor(getCardOutputDataBoundary, getCardDataAccessInterface, drawCardsDataAccessInterface);
        return new GetCardController(getCardInteractor);
    }
}