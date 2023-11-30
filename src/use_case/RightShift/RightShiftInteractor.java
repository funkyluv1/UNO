package use_case.RightShift;

import entities.card.Card;
import use_case.SelectCard.SelectCardDataAccessInterface;
import use_case.SelectCard.SelectCardInputData;
import use_case.SelectCard.SelectCardOutputData;
import use_case.SelectCard.SelectCardOutputDataBoundary;

import static use_case.initiation.InitiationInteractor.game;

public class RightShiftInteractor implements RightShiftInputDataBoundary{
    final RightShiftOutputDataBoundary rightShiftOutputDataBoundary;
    final RightShiftDataAccessInterface rightShiftDataAccessInterface;
    public RightShiftInteractor(RightShiftOutputDataBoundary rightShiftOutputDataBoundary, RightShiftDataAccessInterface rightShiftDataAccessInterface) {
        this.rightShiftOutputDataBoundary = rightShiftOutputDataBoundary;
        this.rightShiftDataAccessInterface = rightShiftDataAccessInterface;
    }

    public void execute(RightShiftInputData rightShiftInputData) {
//        Card selectedCard = rightShiftInputData.getSelectedCardNew();
//        game.setCurrSelectedNumberCard(selectedCard);
        // need to do something with the game class
        RightShiftOutputData rightShiftOutputData = new RightShiftOutputData();
        rightShiftOutputDataBoundary.prepareSuccessView(rightShiftOutputData);
    }
}
