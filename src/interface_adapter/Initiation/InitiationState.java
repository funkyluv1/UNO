package interface_adapter.Initiation;

public class InitiationState {
    private Game game;
    private String initiationError = null;

    public InitiationState(InitiationState copy){
        this.g = game;
        this.initiationError = initiationError;
    }

    public void set_game(Game game){this.g = game;}

    public Game get_game(){return game;}

    public void set_initiationError(String initiationError){this.initiationError = initiationError;}

}