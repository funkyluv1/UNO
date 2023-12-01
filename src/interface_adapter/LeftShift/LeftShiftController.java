package interface_adapter.LeftShift;


import use_case.LeftShift.LeftShiftInputData;
import use_case.LeftShift.LeftShiftInputDataBoundary;

public class LeftShiftController {
        final LeftShiftInputDataBoundary leftShiftInputDataBoundary;

        public LeftShiftController (LeftShiftInputDataBoundary leftShiftInputDataBoundary) {
            this.leftShiftInputDataBoundary = leftShiftInputDataBoundary;
        }
        public void execute (int displaycardIndex) {
            LeftShiftInputData leftShiftInputData = new LeftShiftInputData(displaycardIndex);
            leftShiftInputDataBoundary.execute(leftShiftInputData);
        }
}
