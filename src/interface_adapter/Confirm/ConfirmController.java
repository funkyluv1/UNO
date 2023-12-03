package interface_adapter.Confirm;

import use_case.Confirm.ConfirmInputData;
import use_case.Confirm.ConfirmInputDataBoundary;

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
