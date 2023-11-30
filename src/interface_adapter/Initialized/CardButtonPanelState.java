package interface_adapter.Initialized;

import entities.card.FunctionalCard;
import entities.card.NumberCard;

import java.util.ArrayList;
import java.util.Map;

public class CardButtonPanelState{
        private boolean button1enabled = true;
        private boolean button2enabled = true;
        private boolean button3enabled = true;
        private int buttonindexHighlighted;
        private ArrayList<String> players;
        private Map<String, ArrayList<NumberCard>> playerNumCards;
        private Map<String, ArrayList<NumberCard>> playerPlayableNumCards;
        private Map<String, ArrayList<FunctionalCard>> playerFunCards;
        private Map<String, ArrayList<FunctionalCard>> playerPlayableFunCards;
        private Map<String, Integer> displayNumCardsIndexes;

        public CardButtonPanelState(CardButtonPanelState copy) {

            players = copy.players;
            playerNumCards = copy.playerNumCards;
            playerPlayableNumCards = copy.playerPlayableNumCards;
            playerFunCards = copy.playerFunCards;
            playerPlayableFunCards = copy.playerPlayableFunCards;
            displayNumCardsIndexes = copy.displayNumCardsIndexes;
            button1enabled = copy.button1enabled;
            button2enabled = copy.button2enabled;
            button3enabled = copy.button3enabled;
            buttonindexHighlighted = copy.buttonindexHighlighted;
        }

        // Because of the previous copy constructor, the default constructor must be explicit.
        public CardButtonPanelState() {}

        public ArrayList<String> get_players(){return players;}
        public ArrayList<NumberCard> get_Number_Cards(){return playerNumCards.get(players.get(0));}
        public ArrayList<FunctionalCard> get_Functional_Cards(){return playerFunCards.get(players.get(0));}
        public ArrayList<NumberCard> get_Highlighted_Number_Cards(){return playerPlayableNumCards.get(players.get(0));}
        public ArrayList<FunctionalCard> get_Highlighted_Functional_Cards(){return playerPlayableFunCards.get(players.get(0));}
        public void set_players(ArrayList<String> players){this.players = players;}
        public void set_cards(Map<String, ArrayList<NumberCard>> playerNumCards, Map<String, ArrayList<NumberCard>> playerPlayableNumCards,
                              Map<String, ArrayList<FunctionalCard>> playerFunCards, Map<String, ArrayList<FunctionalCard>> playerPlayableFunCards, Map<String, Integer> displayNumCardsIndexes){
            this.playerPlayableFunCards = playerPlayableFunCards;
            this.playerPlayableNumCards = playerPlayableNumCards;
            this.playerNumCards = playerNumCards;
            this.playerFunCards = playerFunCards;
            this.displayNumCardsIndexes = displayNumCardsIndexes;
        }
        public void setButton1enabled(boolean flag){this.button1enabled = flag;}
        public boolean getButton1enabled(){return this.button1enabled;}

        public void setButton2enabled(boolean flag){this.button2enabled = flag;}
        public boolean getButton2enabled(){return this.button2enabled;}
        public void setButton3enabled(boolean flag){this.button3enabled = flag;}
        public boolean getButton3enabled(){return this.button3enabled;}
        public void setButtonindexHighlighted(int button){this.buttonindexHighlighted = button;}
        public int getButtonindexHighlighted(){return this.buttonindexHighlighted;}
    }

