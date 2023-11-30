package use_case.NextTurn;
import entities.player.Player;


public interface NextTurnDataAccessInterface {
   Player getPlayer(int player_index);
}

