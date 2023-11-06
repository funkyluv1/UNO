package use_case.initiation;

import entities.Game;

public class InitiationOutputData {

    final Game game;

    public InitiationOutputData(Game game){
        this.game = game;
    }

    public Game getGame(){return this.game;};
}
