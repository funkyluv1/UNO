package interface_adapter.RightShift;

import use_case.RightShift.RightShiftInputData;
import use_case.RightShift.RightShiftInputDataBoundary;

public class RightShiftController {
    final RightShiftInputDataBoundary rightShiftInteractor;

    public RightShiftController (RightShiftInputDataBoundary rightShiftInteractor) {
        this.rightShiftInteractor = rightShiftInteractor;
    }

    public void execute (String player) {
        RightShiftInputData rightShiftInputData = new RightShiftInputData(player);
        rightShiftInteractor.execute(rightShiftInputData);
    }
}
