package use_case.Confirm;

import entities.card.FunctionalCard;
import entities.card.NumberCard;

import java.util.ArrayList;

public interface ConfirmDataAccessInterface {
    public String get_specific_player_with_index(int player_index);
    public void play_Card_and_update_DAO(String playerName, NumberCard numberCard, ArrayList<FunctionalCard> functionalCards);
}
