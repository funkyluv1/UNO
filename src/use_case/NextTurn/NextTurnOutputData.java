package use_case.NextTurn;

import entities.card.FunctionalCard;
import entities.card.NumberCard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NextTurnOutputData {
    int player_index;
    ArrayList<NumberCard> numberCards;
    ArrayList<FunctionalCard> functionalCards;
    ArrayList<NumberCard> playerplayablenumcards;


    public NextTurnOutputData(int player_index, ArrayList<NumberCard> numberCards, ArrayList<FunctionalCard> functionalCards,
                              ArrayList<NumberCard> playerplayablenumcards){
        this.player_index = player_index;
        this.numberCards = numberCards;
        this.functionalCards = functionalCards;
        this.playerplayablenumcards = playerplayablenumcards;
    }

    public int getPlayer_index(){
        return this.player_index;
    }

    public ArrayList<NumberCard> getnumcards(){
        return this.numberCards;
    }

    public ArrayList<FunctionalCard> getFunctionalCards(){return this.functionalCards;}

    public ArrayList<NumberCard> getPlayerplayablenumcards(){return this.playerplayablenumcards;}
}
