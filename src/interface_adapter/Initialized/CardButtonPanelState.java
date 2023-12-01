package interface_adapter.Initialized;

import entities.card.FunctionalCard;
import entities.card.NumberCard;

import java.util.ArrayList;
import java.util.Map;

public class CardButtonPanelState{
        private boolean oneCardSelected = false;
        private boolean leftButtonEnabled = false;
        private boolean rightButtonEnabled = false;
//        private boolean button1enabled = true;
//        private boolean button2enabled = true;
//        private boolean button3enabled = true;
        private int buttonindexHighlighted;
        private ArrayList<String> players;
        private ArrayList<NumberCard> playerNumCards;
        private ArrayList<NumberCard> playerPlayableNumCards;
        private int displayNumCardsFirstIndex;

        public CardButtonPanelState(CardButtonPanelState copy) {

            players = copy.players;
            playerNumCards = copy.playerNumCards;
            playerPlayableNumCards = copy.playerPlayableNumCards;
            displayNumCardsFirstIndex = copy.displayNumCardsFirstIndex;
//            button1enabled = copy.button1enabled;
//            button2enabled = copy.button2enabled;
//            button3enabled = copy.button3enabled;
            buttonindexHighlighted = copy.buttonindexHighlighted;
            rightButtonEnabled = copy.rightButtonEnabled;
            leftButtonEnabled = copy.leftButtonEnabled;
            oneCardSelected = copy.oneCardSelected;
        }

        // Because of the previous copy constructor, the default constructor must be explicit.
        public CardButtonPanelState() {}
        public ArrayList<String> get_players(){return players;}
        public ArrayList<NumberCard> get_Number_Cards(){return playerNumCards;}
        public ArrayList<NumberCard> get_Highlighted_Number_Cards(){return playerPlayableNumCards;}
        public void set_players(ArrayList<String> players){this.players = players;}
        public void set_cards(ArrayList<NumberCard> playerNumCards,
                              ArrayList<NumberCard> playerPlayableNumCards,
                              Integer displayNumCardsFirstIndex){
            this.playerPlayableNumCards = playerPlayableNumCards;
            this.playerNumCards = playerNumCards;
            this.displayNumCardsFirstIndex = displayNumCardsFirstIndex;
        }
//        public void setButton1enabled(boolean flag){this.button1enabled = flag;}
//        public boolean getButton1enabled(){return this.button1enabled;}

//        public void setButton2enabled(boolean flag){this.button2enabled = flag;}
//        public boolean getButton2enabled(){return this.button2enabled;}
//        public void setButton3enabled(boolean flag){this.button3enabled = flag;}
//        public boolean getButton3enabled(){return this.button3enabled;}
        public void setButtonindexHighlighted(int button){this.buttonindexHighlighted = button;}
        public int getButtonindexHighlighted(){return this.buttonindexHighlighted;}
        public void setRightButtonEnabled(boolean flag){this.rightButtonEnabled = flag;};
        public boolean getRightButtonEnabled(){return this.rightButtonEnabled;};
        public void setLeftButtonEnabled(boolean flag){this.leftButtonEnabled = flag;};
        public boolean getLeftButtonEnabled(){return this.leftButtonEnabled;};
        public void setDisplayNumCardsFirstIndex(int flag){this.displayNumCardsFirstIndex = flag;};
        public Integer getdisplayCardsFirstIndex(){
            return displayNumCardsFirstIndex;
        }
        public void setOneCardSelected(boolean flag){this.oneCardSelected = flag;};
        public boolean getOneCardsSelected(){return this.oneCardSelected;};
}

