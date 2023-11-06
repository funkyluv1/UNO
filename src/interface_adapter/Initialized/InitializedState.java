package interface_adapter.Initialized;

import entities.Game;

public class InitializedState {
    private Game game;

    public InitializedState(InitializedState copy) {
        game = copy.game;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public InitializedState() {}

    public Game get_game(){return game;}

    public void set_game(Game game){this.game = game;}
}
