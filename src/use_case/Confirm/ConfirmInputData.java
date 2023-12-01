package use_case.Confirm;

import entities.player.Player;

public class ConfirmInputData {
    private int currPlayerIndex;
    private String currPlayer;

    public ConfirmInputData(int currPlayerIndex, String currPlayer) {
        this.currPlayerIndex = currPlayerIndex;
        this.currPlayer = currPlayer;
    }

    public int getCurrPlayerIndex() {
        return currPlayerIndex;
    }

    public String getCurrPlayer() {
        return currPlayer;
    }
}
