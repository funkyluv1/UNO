package use_case.Confirm;

import entities.card.*;
import entities.player.Player;
import use_case.SelectCard.*;

import java.util.ArrayList;

import static use_case.initiation.InitiationInteractor.game;

public class ConfirmInteractor implements ConfirmInputDataBoundary{
    final ConfirmOutputDataBoundary confirmOutputDataBoundary;
    final ConfirmDataAccessInterface confirmDataAccessInterface;

    public ConfirmInteractor(ConfirmOutputDataBoundary confirmOutputDataBoundary, ConfirmDataAccessInterface confirmDataAccessInterface) {
        this.confirmOutputDataBoundary = confirmOutputDataBoundary;
        this.confirmDataAccessInterface = confirmDataAccessInterface;
    }
    @Override
    public void execute(ConfirmInputData confirmInputData) {
        NumberCard selectedNumCard = (NumberCard) game.getCurrSelectedNumberCard();
        ConfirmOutputData outputData = new ConfirmOutputData(selectedNumCard);
        ArrayList<FunctionalCard> selectedFunCard = game.getCurrSelectedFunCard();
        int currPlayerIndex = confirmInputData.getCurrPlayerIndex();
        String currPlayer = confirmDataAccessInterface.get_specific_player_with_index(currPlayerIndex);

        game.setCurrSelectedNumberCard(null);
        game.addNumCardsinRound(selectedNumCard);
        game.setCurrSelectedFunCard(new ArrayList<>());

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
            game.setCurrWinner(currPlayer);
        }


        confirmOutputDataBoundary.prepareSuccessView(outputData);
    }

}
