package use_case.RightShift;

import data_access.FileUserDataAccessObject;
import entities.card.Card;
import use_case.SelectCard.SelectCardDataAccessInterface;
import use_case.SelectCard.SelectCardInputData;
import use_case.SelectCard.SelectCardOutputData;
import use_case.SelectCard.SelectCardOutputDataBoundary;

import static use_case.initiation.InitiationInteractor.game;

public class RightShiftInteractor implements RightShiftInputDataBoundary{
    final RightShiftOutputDataBoundary rightShiftOutputDataBoundary;
    final RightShiftDataAccessInterface rightShiftDataAccessInterface;
    private boolean rightShiftActive = true;
    public RightShiftInteractor(FileUserDataAccessObject fileUserDataAccessObject, RightShiftOutputDataBoundary rightShiftOutputDataBoundary) {
        this.rightShiftOutputDataBoundary = rightShiftOutputDataBoundary;
        this.rightShiftDataAccessInterface = fileUserDataAccessObject;
    }

    public void execute(RightShiftInputData rightShiftInputData) {
        if (rightShiftInputData.getDisplayCardFirstIndex() + 4 >= rightShiftInputData.getPlayerNumcards().size()){
            rightShiftActive = false;
        } else {rightShiftActive = true;}
        RightShiftOutputData rightShiftOutputData = new RightShiftOutputData(rightShiftActive);
        if (!rightShiftInputData.getFlag_for_func()){
            rightShiftOutputDataBoundary.prepareSuccessView(rightShiftOutputData);
        }
        else {rightShiftOutputDataBoundary.prepareSuccessView(rightShiftOutputData, true);}
    }
}