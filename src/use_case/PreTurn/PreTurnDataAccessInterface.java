package use_case.PreTurn;

import entities.card.FunctionalCard;
import entities.card.NumberCard;
import entities.player.Player;

import java.util.ArrayList;

public interface PreTurnDataAccessInterface {
    ArrayList<NumberCard> getNumberCards(String player);
    void recordPreTurnChange(ArrayList<NumberCard> numberCards, String currentPlayer);
    ArrayList<FunctionalCard> getPlayerFunctionalCards();
    Player getPlayer(int playerIndex);
}
