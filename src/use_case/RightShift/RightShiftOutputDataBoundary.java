package use_case.RightShift;

import use_case.initiation.InitiationOutputData;

public interface RightShiftOutputDataBoundary {
    void prepareSuccessView(RightShiftOutputData rightShiftOutputData);
    void prepareSuccessView(RightShiftOutputData rightShiftOutputData, boolean flag_for_func);
}
