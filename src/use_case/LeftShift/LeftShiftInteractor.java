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
        if (leftShiftInputData.getDisplayCardFirstIndex() > 1){
            leftShiftActive = true;
        } else {leftShiftActive = false;};
        LeftShiftOutputData leftShiftOutputData = new LeftShiftOutputData(leftShiftActive);
        if (leftShiftInputData.getFlag_For_Func()){
            leftShiftOutputDataBoundary.prepareSuccessView(leftShiftOutputData, leftShiftInputData.getFlag_For_Func());
        }
        else {leftShiftOutputDataBoundary.prepareSuccessView(leftShiftOutputData);}
    }

}
