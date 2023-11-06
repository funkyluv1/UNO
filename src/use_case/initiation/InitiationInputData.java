package use_case.initiation;

import entities.player.Player;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class InitiationInputData {
    ArrayList<String> playerNames;
//    Integer playerNumber;
    public InitiationInputData(ArrayList<String> playerNames){
        this.playerNames = playerNames;
    };

    public ArrayList<String> getPlayerNames(){return this.playerNames;}

    public int getPlayerNumber(){
        int cnt = 0;
        for(String i : this.playerNames){
            cnt ++;
        }
        return cnt;
    };
}
