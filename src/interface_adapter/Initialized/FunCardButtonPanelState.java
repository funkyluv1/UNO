package interface_adapter.Initialized;

import entities.card.FunctionalCard;
import entities.card.NumberCard;

import java.util.ArrayList;

public class FunCardButtonPanelState {
    private boolean allButtonDisable = false;
    private boolean leftButtonEnabled = false;
    private boolean rightButtonEnabled = false;
    private ArrayList<String> players;
    private ArrayList<FunctionalCard> playerFunCards;
    private ArrayList<FunctionalCard> playerPlayableFunCards;
    private ArrayList<FunctionalCard> currselectedCards = new ArrayList<>();
    private int displayFunCardsFirstIndex;
    public FunCardButtonPanelState(FunCardButtonPanelState copy) {

        players = copy.players;
        playerFunCards = copy.playerFunCards;
        playerPlayableFunCards = copy.playerPlayableFunCards;
        displayFunCardsFirstIndex = copy.displayFunCardsFirstIndex;
        rightButtonEnabled = copy.rightButtonEnabled;
        leftButtonEnabled = copy.leftButtonEnabled;
        currselectedCards = copy.currselectedCards;
        allButtonDisable = copy.allButtonDisable;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public FunCardButtonPanelState() {}
    public ArrayList<String> get_players(){return players;}
    public ArrayList<FunctionalCard> get_Fun_Cards(){return playerFunCards;}
    public void set_Fun_Cards(ArrayList<FunctionalCard> cards){this.playerFunCards = cards;}
    public ArrayList<FunctionalCard> get_Playable_Fun_Cards(){return playerPlayableFunCards;}
    public void set_Playable_Fun_Cards(ArrayList<FunctionalCard> cards){this.playerPlayableFunCards = cards;}
    public ArrayList<FunctionalCard>get_Selected_Fun_Cards(){return this.currselectedCards;}
    public void set_Selected_Fun_Cards(ArrayList<FunctionalCard> cards){this.currselectedCards = cards;}
    public void set_players(ArrayList<String> players){this.players = players;}
    public void set_cards(ArrayList<FunctionalCard> playerFunCards,
                          ArrayList<FunctionalCard> playerPlayableFunCards,
                          Integer displayFunCardsFirstIndex){
        this.playerPlayableFunCards = playerPlayableFunCards;
        this.playerFunCards= playerFunCards;
        this.displayFunCardsFirstIndex = displayFunCardsFirstIndex;
    }
    public void setRightButtonEnabled(boolean flag){this.rightButtonEnabled = flag;};
    public boolean getRightButtonEnabled(){return this.rightButtonEnabled;};
    public void setLeftButtonEnabled(boolean flag){this.leftButtonEnabled = flag;};
    public boolean getLeftButtonEnabled(){return this.leftButtonEnabled;};
    public void setDisplayNumCardsFirstIndex(int flag){this.displayFunCardsFirstIndex = flag;};
    public Integer getdisplayCardsFirstIndex(){
        // need current player instead of setting it as the first player
        return displayFunCardsFirstIndex;
    }
    public void setAllButtonDisable(boolean flag){this.allButtonDisable = flag;};
    public boolean getAllButtonDisable(){return this.allButtonDisable;};
}
