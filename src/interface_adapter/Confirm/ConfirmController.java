package interface_adapter.Confirm;

import data_access.StringToCardAdapter;
import entities.card.Card;
import use_case.Confirm.ConfirmInputDataBoundary;
import use_case.SelectCard.SelectCardInputData;
import use_case.SelectCard.SelectCardInputDataBoundary;

import java.util.ArrayList;

public class ConfirmController {

        final ConfirmInputDataBoundary confirmInputDataBoundary;

        public ConfirmController (ConfirmInputDataBoundary confirmInputDataBoundary) {
            this.confirmInputDataBoundary = confirmInputDataBoundary;
        }

        public void execute () {
            confirmInputDataBoundary.execute();
    }

}
