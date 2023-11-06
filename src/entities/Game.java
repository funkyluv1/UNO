package entities;

import entities.card.*;
import entities.player.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Game {
    private final int playerCapacity;
    private Player[] players;
    private Card topCard;

    public Game(int playerCapacity, Player[] players) {
        this.playerCapacity = playerCapacity;
        this.players = players;
        String[] randColor = {"red", "blue", "green", "yellow"};
        int randColorIndex = (int) Math.floor(Math.random()*4);
        int randValue = (int) Math.floor(Math.random()*9);
        this.topCard = new NumberCard(randValue, randColor[randColorIndex]);
    }

    public Player[] getPlayers() {
        return players;
    }

    public Card getTopCard() {
        return topCard;
    }
}
