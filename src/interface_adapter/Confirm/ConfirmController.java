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

    public ConfirmController (ConfirmInputDataBoundary confirmInputDataBoundary) {
        this.confirmInputDataBoundary = confirmInputDataBoundary;
    }
    public void execute (int currPlayerIndex) {
        ConfirmInputData confirmInputData = new ConfirmInputData(currPlayerIndex);
        confirmInputDataBoundary.execute(confirmInputData);
    }

}
