package interface_adapter.Initiation;

import entities.Game;

import java.util.ArrayList;

public class InitiationState {
    private ArrayList<String> players = new ArrayList<>();

    public InitiationState(InitiationState copy){
        players = copy.players;
    }

    public InitiationState(){}

    public ArrayList<String> get_players(){return players;}

    public void set_player(String player){this.players.add(player);}

}