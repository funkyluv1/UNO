package interface_adapter.Initialized;

import entities.Game;
import entities.player.Player;

import java.util.ArrayList;

public class InitializedState {
    private ArrayList<String> players;

    public InitializedState(InitializedState copy) {
        players = copy.players;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public InitializedState() {}

    public ArrayList<String> get_players(){return players;}

    public void set_players(ArrayList<String> players){this.players = players;}
}
