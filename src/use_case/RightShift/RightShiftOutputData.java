package use_case.RightShift;

import entities.card.NumberCard;

import java.util.ArrayList;
import java.util.Map;

public class RightShiftOutputData {
    final boolean rightShiftActive;
    public RightShiftOutputData (boolean rightShiftActive) {
        this.rightShiftActive = rightShiftActive;
    }

    public boolean getRightShiftActive(){return this.rightShiftActive;}
}
