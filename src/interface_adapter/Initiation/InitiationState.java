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

    public ArrayList<String> get_players(){return players;}

    public String get_initiationError(){return initiationError;}

    public void set_players(String player){this.players.add(player);}

    public void set_initiationError(String initiationError){this.initiationError = initiationError;}

}