package use_case.initiation;
import entities.Game;

import java.util.ArrayList;

public  class InitiationOutputData {
    ArrayList<String> playerNames;

    public InitiationOutputData(ArrayList<String> playerNames) {this.playerNames = playerNames;}

    public ArrayList<String> getPlayerNames() {return this.playerNames;}
}
