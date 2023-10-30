package entities;

import entities.card.*;
import entities.player.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Game {
    private final int playerCapacity;
    private Player[] players;
    private HashMap<Player, ArrayList<Card>> cardsInPlayer;
    public Card topCard;

    public Game(int playerCapacity, Player[] players) {
        this.playerCapacity = playerCapacity;
        this.players = players;
        this.cardsInPlayer = new HashMap<>();
        for (Player player : this.players) {
            cardsInPlayer.put(player, player.getHand());
        }
        String[] randColor = {"red", "blue", "green", "yellow"};
        int randColorIndex = (int) Math.floor(Math.random()*4);
        int randValue = (int) Math.floor(Math.random()*9);
        this.topCard = new NumberCard(randValue, randColor[randColorIndex]);
    }
}
