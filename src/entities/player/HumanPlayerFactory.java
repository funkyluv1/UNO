package entities.player;

import entities.card.FunctionalCard;
import entities.card.NumberCard;

import java.util.ArrayList;

public class HumanPlayerFactory implements PlayerFactory{
    public HumanPlayer create(String username, ArrayList<NumberCard> numcards, ArrayList<FunctionalCard> functionalCards){
        HumanPlayer humanPlayer = new HumanPlayer(username, numcards);
        humanPlayer.setFuncCards(functionalCards);
        return humanPlayer;
    }
}
