package use_case.initiation;

import entities.player.Player;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class InitiationInputData {
    ArrayList<String> playerNames;
    int botNumber;
//    Integer playerNumber;
    public InitiationInputData(ArrayList<String> playerNames, int botNumber){
        this.playerNames = playerNames;
        this.botNumber = botNumber;
    };

    public int getBotNumber(){return this.botNumber;}
    public ArrayList<String> getPlayerNames(){return this.playerNames;}

    public int getPlayerNumber(){
        int cnt = 0;
        for(String i : this.playerNames){
            cnt ++;
        }
        return cnt;
    };
}
