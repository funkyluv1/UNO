package use_case.LeftShift;

import use_case.RightShift.RightShiftOutputData;

public interface LeftShiftOutputDataBoundary {
    void prepareSuccessView(LeftShiftOutputData leftShiftOutputData);
    void prepareSuccessView(LeftShiftOutputData leftShiftOutputData, boolean boolean_indicating_funcCards);
}
