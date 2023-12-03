package app;

import data_access.FileUserDataAccessObject;
import interface_adapter.Initialized.CardButtonPanelViewModel;
import interface_adapter.Initialized.FunCardButtonPanelViewModel;
import interface_adapter.LeftShift.LeftShiftController;
import interface_adapter.LeftShift.LeftShiftPresenter;
import interface_adapter.RightShift.RightShiftController;
import interface_adapter.RightShift.RightShiftPresenter;
import interface_adapter.SelectFuncCard.*;
import interface_adapter.ViewManagerModel;
import use_case.LeftShift.LeftShiftInputDataBoundary;
import use_case.LeftShift.LeftShiftInteractor;
import use_case.LeftShift.LeftShiftOutputDataBoundary;
import use_case.RightShift.RightShiftInputDataBoundary;
import use_case.RightShift.RightShiftInteractor;
import use_case.RightShift.RightShiftOutputDataBoundary;
import use_case.SelectFuncCard.*;
import view.FunCardButtonPanel;

import javax.swing.*;
import java.io.IOException;

public class FunCardButtonPanelUseCaseFactory {
     public FunCardButtonPanelUseCaseFactory() {

     }

     public static FunCardButtonPanel create (ViewManagerModel viewManagerModel,CardButtonPanelViewModel cardButtonPanelViewModel,
                                              FunCardButtonPanelViewModel funCardButtonPanelViewModel,
                                              FileUserDataAccessObject fileUserDataAccessObject) {
         try {
             SelectFuncCardController selectFuncCardController = createSelectFuncCardController(viewManagerModel, funCardButtonPanelViewModel);
             RightShiftController rightShiftController = createRightShiftController(viewManagerModel, funCardButtonPanelViewModel, cardButtonPanelViewModel, fileUserDataAccessObject);
             LeftShiftController leftShiftController = createLeftShiftController(viewManagerModel, cardButtonPanelViewModel, funCardButtonPanelViewModel);
             return new FunCardButtonPanel(funCardButtonPanelViewModel, selectFuncCardController, rightShiftController, leftShiftController);
         }
         catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Could not create functional card button panel.");
         }

         return null;
     }

    private static SelectFuncCardController createSelectFuncCardController(ViewManagerModel viewManagerModel,
                                                                   FunCardButtonPanelViewModel funCardButtonPanelViewModel) {

        SelectFuncCardOutputDataBoundary selectFuncCardOutputDataBoundary = new SelectFuncCardPresenter(viewManagerModel,
                funCardButtonPanelViewModel);
        SelectFuncCardInputDataBoundary selectCardInteractor = new SelectFuncCardInteractor(selectFuncCardOutputDataBoundary);
        return new SelectFuncCardController(selectCardInteractor);
    }

    private static RightShiftController createRightShiftController(ViewManagerModel viewManagerModel,
                                                                   FunCardButtonPanelViewModel funCardButtonPanelViewModel,
                                                                   CardButtonPanelViewModel cardButtonPanelViewModel,
                                                                   FileUserDataAccessObject userDataAccessObject) {

        RightShiftOutputDataBoundary rightShiftOutputDataBoundary = new RightShiftPresenter(viewManagerModel, cardButtonPanelViewModel,
                funCardButtonPanelViewModel);

        RightShiftInputDataBoundary rightShiftInteractor = new RightShiftInteractor(userDataAccessObject, rightShiftOutputDataBoundary);

        return new RightShiftController(rightShiftInteractor);
    }

    private static LeftShiftController createLeftShiftController(ViewManagerModel viewManagerModel, CardButtonPanelViewModel cardButtonPanelViewModel,
                                                                 FunCardButtonPanelViewModel funCardButtonPanelViewModel) {

        LeftShiftOutputDataBoundary leftShiftOutputDataBoundary = new LeftShiftPresenter(viewManagerModel, cardButtonPanelViewModel,
                funCardButtonPanelViewModel);

        LeftShiftInputDataBoundary leftShiftInteractor = new LeftShiftInteractor(leftShiftOutputDataBoundary);

        return new LeftShiftController(leftShiftInteractor);
    }
}
