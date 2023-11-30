package use_case.Confirm;

import entities.player.Player;

public class ConfirmInputData {
    private Player currPlayer;

    public ConfirmInputData(Player player) {
        currPlayer = player;
    }

    public Player getCurrPlayer() {
        return currPlayer;
    }

}
