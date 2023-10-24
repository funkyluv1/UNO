package entities;

import entities.card.Card;
import entities.player.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class Game {
    private final int playerCapacity;
    private Player[] players;
    private HashMap<Player, ArrayList<Card>> cardsInPlayer;

    public Game(int playerCapacity, Player[] players) {
        this.playerCapacity = playerCapacity;
        this.players = players;
        this.cardsInPlayer = new HashMap<>();
        for (Player player : this.players) {
            cardsInPlayer.put(player, player.getHand());
        }
    }
}
