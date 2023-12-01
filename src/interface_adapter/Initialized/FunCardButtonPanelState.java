package interface_adapter.Initialized;

import entities.card.FunctionalCard;
import entities.card.NumberCard;

import java.util.ArrayList;

public class FunCardButtonPanelState {
    private boolean oneCardSelected = false;
    private boolean leftButtonEnabled = false;
    private boolean rightButtonEnabled = false;
    private int buttonindexHighlighted;
    private ArrayList<String> players;
    private ArrayList<FunctionalCard> playerFunCards;
    private ArrayList<FunctionalCard> playerPlayableFunCards;
    private int displayFunCardsFirstIndex;
    public FunCardButtonPanelState(FunCardButtonPanelState copy) {

        players = copy.players;
        playerFunCards = copy.playerFunCards;
        playerPlayableFunCards = copy.playerPlayableFunCards;
        displayFunCardsFirstIndex = copy.displayFunCardsFirstIndex;
        buttonindexHighlighted = copy.buttonindexHighlighted;
        rightButtonEnabled = copy.rightButtonEnabled;
        leftButtonEnabled = copy.leftButtonEnabled;
        oneCardSelected = copy.oneCardSelected;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public FunCardButtonPanelState() {}
    public ArrayList<String> get_players(){return players;}
    public ArrayList<FunctionalCard> get_Fun_Cards(){return playerFunCards;}
    public ArrayList<FunctionalCard> get_Highlighted_Fun_Cards(){return playerPlayableFunCards;}
    public void set_players(ArrayList<String> players){this.players = players;}
    public void set_cards(ArrayList<FunctionalCard> playerFunCards,
                          ArrayList<FunctionalCard> playerPlayableFunCards,
                          Integer displayFunCardsFirstIndex){
        this.playerPlayableFunCards = playerPlayableFunCards;
        this.playerFunCards= playerFunCards;
        this.displayFunCardsFirstIndex = displayFunCardsFirstIndex;
    }
    public void setButtonindexHighlighted(int button){this.buttonindexHighlighted = button;}
    public int getButtonindexHighlighted(){return this.buttonindexHighlighted;}
    public void setRightButtonEnabled(boolean flag){this.rightButtonEnabled = flag;};
    public boolean getRightButtonEnabled(){return this.rightButtonEnabled;};
    public void setLeftButtonEnabled(boolean flag){this.leftButtonEnabled = flag;};
    public boolean getLeftButtonEnabled(){return this.leftButtonEnabled;};
    public void setDisplayNumCardsFirstIndex(int flag){this.displayFunCardsFirstIndex = flag;};
    public Integer getdisplayCardsFirstIndex(){
        // need current player instead of setting it as the first player
        return displayFunCardsFirstIndex;
    }
    public void setOneCardSelected(boolean flag){this.oneCardSelected = flag;};
    public boolean getOneCardsSelected(){return this.oneCardSelected;};
}
