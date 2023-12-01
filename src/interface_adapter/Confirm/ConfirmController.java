package interface_adapter.Confirm;

import data_access.StringToCardAdapter;
import entities.card.Card;
import use_case.Confirm.ConfirmInputData;
import use_case.Confirm.ConfirmInputDataBoundary;
import use_case.SelectCard.SelectCardInputData;
import use_case.SelectCard.SelectCardInputDataBoundary;

import java.util.ArrayList;

public class ConfirmController {

    final ConfirmInputDataBoundary confirmInputDataBoundary;
    final ConfirmInputData confirmInputData;

    public ConfirmController (ConfirmInputDataBoundary confirmInputDataBoundary, ConfirmInputData confirmInputData) {
        this.confirmInputDataBoundary = confirmInputDataBoundary;
        this.confirmInputData = confirmInputData;
    }
    public void execute () {
        confirmInputDataBoundary.execute(confirmInputData);
    }

}
