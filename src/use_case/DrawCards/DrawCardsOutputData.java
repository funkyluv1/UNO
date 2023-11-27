package use_case.DrawCards;

import entities.card.FunctionalCard;
import entities.card.NumberCard;
import entities.player.Player;

import java.util.ArrayList;

public class DrawCardsOutputData {
    private final Player player;
    private final ArrayList<NumberCard> numberCards;


    public DrawCardsOutputData(Player player, ArrayList<NumberCard> numberCards){
        this.player = player;
        this.numberCards = numberCards;
    }

    public ArrayList<NumberCard> getNumberCards() {
        return numberCards;
    }
}
