package use_case.NextTurn;
import entities.card.FunctionalCard;
import entities.card.NumberCard;
import entities.player.Player;

import java.util.ArrayList;


public interface NextTurnDataAccessInterface {
   Player getPlayer(int player_index);
   void play_Card_and_update_DAO(String currPlayer, NumberCard selectedNumCard, ArrayList<FunctionalCard> selectedFunCard);

}

