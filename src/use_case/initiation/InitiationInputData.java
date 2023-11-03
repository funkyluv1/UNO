package use_case.initiation;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class InitiationInputData {
    ArrayList<String> playerNames = new ArrayList<String>();
    Integer playerNumber;
    public InitiationInputData(Integer playerNumber, ArrayList<String> playerNames){
        this.playerNames = playerNames;
        this.playerNumber = playerNumber;
    };
    public ArrayList<String> getPlayerNames(){return this.playerNames;};
    public Integer getPlayerNumber(){return this.playerNumber;};
}
