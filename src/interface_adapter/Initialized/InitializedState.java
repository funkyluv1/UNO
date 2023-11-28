package interface_adapter.Initialized;

import entities.Game;
import entities.card.FunctionalCard;
import entities.card.NumberCard;
import entities.player.Player;

import java.util.ArrayList;
import java.util.Map;

public class InitializedState {
    private ArrayList<String> players;
    private Map<String, ArrayList<NumberCard>> playerNumCards;
    private Map<String, ArrayList<NumberCard>> playerPlayableNumCards;
    private Map<String, ArrayList<FunctionalCard>> playerFunCards;
    private Map<String, ArrayList<FunctionalCard>> playerPlayableFunCards;

    public InitializedState(InitializedState copy) {

        players = copy.players;
        playerNumCards = copy.playerNumCards;
        playerPlayableNumCards = copy.playerPlayableNumCards;
        playerFunCards = copy.playerFunCards;
        playerPlayableFunCards = copy.playerPlayableFunCards;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public InitializedState() {}

    public ArrayList<String> get_players(){return players;}
    public ArrayList<NumberCard> get_Number_Cards(){return playerNumCards.get(players.get(0));}
    public ArrayList<FunctionalCard> get_Functional_Cards(){return playerFunCards.get(players.get(0));}
    public ArrayList<NumberCard> get_Highlighted_Number_Cards(){return playerPlayableNumCards.get(players.get(0));}
    public ArrayList<FunctionalCard> get_Highlighted_Functional_Cards(){return playerPlayableFunCards.get(players.get(0));}
    public void set_players(ArrayList<String> players){this.players = players;}
    public void set_cards(Map<String, ArrayList<NumberCard>> playerNumCards, Map<String, ArrayList<NumberCard>> playerPlayableNumCards,
                          Map<String, ArrayList<FunctionalCard>> playerFunCards, Map<String, ArrayList<FunctionalCard>> playerPlayableFunCards){
        this.playerPlayableFunCards = playerPlayableFunCards;
        this.playerPlayableNumCards = playerPlayableNumCards;
        this.playerNumCards = playerNumCards;
        this.playerFunCards = playerFunCards;
    }
}
