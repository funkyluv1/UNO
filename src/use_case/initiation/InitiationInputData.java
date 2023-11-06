package use_case.initiation;

import entities.player.Player;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class InitiationInputData {
    Player[] players;
//    Integer playerNumber;
    public InitiationInputData(Player[] players){
        this.players = players;
    };
    public Player[] getPlayers(){return this.players;}
    public ArrayList<String> getPlayerNames(){
        ArrayList<String> playerNames = new ArrayList<String>();
        for (Player i: this.players){
            playerNames.add(i.playerName);
        }
        return playerNames;
    };
    public Integer getPlayerNumber(){
        int cnt = 0;
        for(Player i : this.players){
            cnt ++;
        }
        return cnt;
    };
}
