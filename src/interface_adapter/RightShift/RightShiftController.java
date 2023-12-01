package interface_adapter.RightShift;

import entities.card.NumberCard;
import use_case.RightShift.RightShiftInputData;
import use_case.RightShift.RightShiftInputDataBoundary;

import java.util.ArrayList;

public class RightShiftController {
    final RightShiftInputDataBoundary rightShiftInteractor;

    public RightShiftController (RightShiftInputDataBoundary rightShiftInteractor) {
        this.rightShiftInteractor = rightShiftInteractor;
    }

    public void execute (ArrayList<NumberCard> playerNumCards, int displaycardIndex) {
        RightShiftInputData rightShiftInputData = new RightShiftInputData(playerNumCards, displaycardIndex);
        rightShiftInteractor.execute(rightShiftInputData);
    }
}
