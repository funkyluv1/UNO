package entities.player;

import entities.card.Card;

import java.util.ArrayList;

public abstract class Player {
    private final int userID;
    public String playerName;
    private ArrayList<Card> hand;
    private boolean isInGame;


    Player(int userID, String playerName, ArrayList<Card> hand) {
        this.userID = userID;
        this.playerName = playerName;
        this.hand = new ArrayList<>(hand);
        this.isInGame = true;
    }
    public int getUserID() {
        return userID;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public boolean isPlaying() {
        return isInGame;
    }

    public void quit() {
        isInGame = false;
    }

    public abstract Card dealCard();
}
