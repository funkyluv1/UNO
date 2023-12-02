package interface_adapter.RightShift;

import entities.card.Card;
import entities.card.NumberCard;
import use_case.RightShift.RightShiftInputData;
import use_case.RightShift.RightShiftInputDataBoundary;

import java.util.ArrayList;

public class RightShiftController {
    final RightShiftInputDataBoundary rightShiftInteractor;

    public RightShiftController (RightShiftInputDataBoundary rightShiftInteractor) {
        this.rightShiftInteractor = rightShiftInteractor;
    }

    public void execute (ArrayList<Card> playerNumCards, int displaycardIndex, boolean flag_for_func) {
        RightShiftInputData rightShiftInputData = new RightShiftInputData(playerNumCards, displaycardIndex, flag_for_func);
        rightShiftInteractor.execute(rightShiftInputData);
    }
}
