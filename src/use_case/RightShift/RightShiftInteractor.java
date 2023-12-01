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
    public RightShiftInteractor(FileUserDataAccessObject fileUserDataAccessObject, RightShiftOutputDataBoundary rightShiftOutputDataBoundary) {
        this.rightShiftOutputDataBoundary = rightShiftOutputDataBoundary;
        this.rightShiftDataAccessInterface = fileUserDataAccessObject;
    }

    public void execute(RightShiftInputData rightShiftInputData) {
        // need to get current player from the game class, then use this current player to find their hand and their first cardi index
        RightShiftOutputData rightShiftOutputData = new RightShiftOutputData();
        rightShiftOutputDataBoundary.prepareSuccessView(rightShiftOutputData);
    }
}
