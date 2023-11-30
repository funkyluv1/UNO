package interface_adapter.Initialized;

import entities.Game;
import entities.card.FunctionalCard;
import entities.card.NumberCard;
import entities.player.Player;
import view.BottomPanel;
import view.CardButtonPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Map;

public class InitializedState {
    private CardButtonPanel cardButtonPanel;
    private BottomPanel bottomPanel;
    private ArrayList<String> players;
    private Map<String, ArrayList<NumberCard>> playerNumCards;
    private Map<String, ArrayList<NumberCard>> playerPlayableNumCards;
    private Map<String, ArrayList<FunctionalCard>> playerFunCards;
    private Map<String, ArrayList<FunctionalCard>> playerPlayableFunCards;

    public InitializedState(InitializedState copy) {
        bottomPanel = copy.bottomPanel;
        cardButtonPanel = copy.cardButtonPanel;
        players = copy.players;
        playerNumCards = copy.playerNumCards;
        playerPlayableNumCards = copy.playerPlayableNumCards;
        playerFunCards = copy.playerFunCards;
        playerPlayableFunCards = copy.playerPlayableFunCards;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public InitializedState() {}
    public void set_CardButtonPanel(CardButtonPanel cardButtonPanel){this.cardButtonPanel = cardButtonPanel;}
    public JPanel get_CardButtonPanel(){return this.cardButtonPanel;}
    public void setBottomPanel(BottomPanel bottomPanel){this.bottomPanel = bottomPanel;}
    public JPanel getBottomPanel(){return this.bottomPanel;}
}