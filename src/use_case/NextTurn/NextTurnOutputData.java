package use_case.NextTurn;

import entities.card.FunctionalCard;
import entities.card.NumberCard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NextTurnOutputData {
    int player_index;
    final Map<String, ArrayList<NumberCard>> playernumcards;
    final Map<String, ArrayList<FunctionalCard>> playerfunccards;
    final Map<String, ArrayList<NumberCard>> playerplayablenumbercard;
    final Map<String, ArrayList<FunctionalCard>> playerplayablefunccard;

    public NextTurnOutputData(int player_index, Map<String, ArrayList<NumberCard>> playernumcards,
                              Map<String, ArrayList<FunctionalCard>> playerfunccards,
                              Map<String, ArrayList<NumberCard>> playerplayablenumbercard,
                              Map<String, ArrayList<FunctionalCard>> playerplayablefunccard){
        this.player_index = player_index;
        this.playernumcards = playernumcards;
        this.playerfunccards = playerfunccards;
        this.playerplayablefunccard = playerplayablefunccard;
        this.playerplayablenumbercard = playerplayablenumbercard;
    }

    public int getPlayer_index(){
        return this.player_index;
    }

    public Map<String, ArrayList<NumberCard>> getPlayerNumberCards(){
        return this.playernumcards;
    }

    public Map<String, ArrayList<FunctionalCard>> getPlayerFuncrCards(){
        return this.playerfunccards;
    }
    public Map<String, ArrayList<NumberCard>> getPlayerPlayableNumberCards(){
        return this.playerplayablenumbercard;
    }
    public Map<String, ArrayList<FunctionalCard>> getPlayerPlayableFuncrCards(){
        return this.playerplayablefunccard;
    }
}
