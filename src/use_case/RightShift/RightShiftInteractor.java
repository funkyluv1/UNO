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
        if (rightShiftInputData.getDisplayCardFirstIndex() + 3 >= rightShiftInputData.getPlayerNumcards().size()){
            rightShiftActive = false;
        }
        RightShiftOutputData rightShiftOutputData = new RightShiftOutputData(rightShiftActive);
        rightShiftOutputDataBoundary.prepareSuccessView(rightShiftOutputData);
    }
}