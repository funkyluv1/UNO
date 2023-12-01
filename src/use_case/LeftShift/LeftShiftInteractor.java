package use_case.LeftShift;

import data_access.FileUserDataAccessObject;
import use_case.RightShift.*;

public class LeftShiftInteractor implements LeftShiftInputDataBoundary {
    final LeftShiftOutputDataBoundary leftShiftOutputDataBoundary;
    private boolean leftShiftActive = false;
    public LeftShiftInteractor(LeftShiftOutputDataBoundary leftShiftOutputDataBoundary) {
        this.leftShiftOutputDataBoundary = leftShiftOutputDataBoundary;
    }
    @Override
    public void execute(LeftShiftInputData leftShiftInputData) {
        if (leftShiftInputData.getDisplayCardFirstIndex() > 0){
            leftShiftActive = true;
        }
        LeftShiftOutputData leftShiftOutputData = new LeftShiftOutputData(leftShiftActive);
        leftShiftOutputDataBoundary.prepareSuccessView(leftShiftOutputData);
    }

}
