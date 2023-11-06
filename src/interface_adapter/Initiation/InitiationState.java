package interface_adapter.Initiation;
import entities.Game;

public class InitiationState {
    private Game g;
    private String initiationError = null;

    public InitiationState(InitiationState copy){
        this.g = g;
        this.initiationError = initiationError;
    }

    public InitiationState() {}

    public void set_game(Game game){this.g = game;}

    public Game get_game(){return g;}

    public void set_initiationError(String initiationError){this.initiationError = initiationError;}

}
