package interface_adapter.Initiation;

import entities.Game;

import java.util.ArrayList;

public class InitiationState {
    private ArrayList<String> players;
    private String initiationError = null;

    public InitiationState(InitiationState copy){
        players = copy.players;
        initiationError = copy.initiationError;
    }

    public InitiationState(){}

    public ArrayList<String> getPlayers(){return players};

    public String get_initiationError(){return initiationError;}

    public void setPlayers(ArrayList<String> players){this.players = players;}

    public void set_initiationError(String initiationError){this.initiationError = initiationError;}

}