package use_case.initiation;
import entities.Game;
public  class InitiationOutputData {
    Game game;

    public InitiationOutputData(Game game) {this.game = game;}

    public Game getGame() {return game;}
}
