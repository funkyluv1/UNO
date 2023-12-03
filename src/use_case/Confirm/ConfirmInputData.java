package use_case.Confirm;

import entities.player.Player;

public class ConfirmInputData {
    private int currPlayerIndex;

    public ConfirmInputData(int currPlayerIndex) {
        this.currPlayerIndex = currPlayerIndex;
    }

    public int getCurrPlayerIndex() {
        return currPlayerIndex;
    }
}
