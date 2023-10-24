package entities.card;

import entities.player.Player;

public class Card {
    private final int value;
    private final String color;
    private Player player;

    public Card(int value, String color) {
        this.value = value;
        this.color = color;
        player = null;
    }

    public int getValue() {
        return value;
    }

    public String getColor() {
        return color;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
