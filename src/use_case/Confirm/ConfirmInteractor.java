package use_case.Confirm;

import entities.card.*;
import entities.player.Player;
import use_case.SelectCard.*;

import java.util.ArrayList;

import static use_case.initiation.InitiationInteractor.game;

public class ConfirmInteractor implements ConfirmInputDataBoundary{
    final ConfirmOutputDataBoundary confirmOutputDataBoundary;

    public ConfirmInteractor(ConfirmOutputDataBoundary confirmOutputDataBoundary) {
        this.confirmOutputDataBoundary = confirmOutputDataBoundary;
    }
    @Override
    public void execute(ConfirmInputData confirmInputData) {
        NumberCard selectedNumCard = (NumberCard) game.getCurrSelectedNumberCard();
        ArrayList<FunctionalCard> selectedFunCard = game.getCurrSelectedFunCard();
        int currPlayerIndex = confirmInputData.getCurrPlayerIndex();

        game.setCurrSelectedNumberCard(null);
        game.addNumCardsinRound(selectedNumCard);
        game.setCurrSelectedFunCard(null);

        for (FunctionalCard functionalCard : selectedFunCard){
            if (functionalCard instanceof PlusTwoCard){
                game.setDrawCard(game.getDrawCard() + 2);
                game.setSkipped(true);
            }
            else if (functionalCard instanceof PlusFourCard){
                game.setDrawCard(game.getDrawCard() + 2);
                game.setSkipped(true);
            }
            else if (functionalCard instanceof SkipCard){
                game.setSkipped(true);
            }
            //TODO: PotatoCard/ BombCard needed to be implemented
        }

        int currMax = game.getMaxCardNum();
        if (selectedNumCard.getValue() > currMax) {
            game.setMaxCardNum(selectedNumCard.getValue());
            game.setCurrWinner(currPlayerIndex);
        }

        confirmOutputDataBoundary.prepareSuccessView();
    }

}
