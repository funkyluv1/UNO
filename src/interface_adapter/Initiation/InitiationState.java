package interface_adapter.Initiation;

public class InitiationState {
//    private Game game;
    private String initiationError = null;

    public InitiationState(InitiationState copy){
//        game = copy.game;
        initiationError = copy.initiationError;
    }

    public InitiationState(){}

    public Game get_game(){return game;}

    public Game get_initiationError(){return initiationError;}

    public void set_game(Game game){this.g = game;}

    public void set_initiationError(String initiationError){this.initiationError = initiationError;}

}