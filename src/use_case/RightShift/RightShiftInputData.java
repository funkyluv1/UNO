package use_case.RightShift;

import entities.card.Card;
import entities.card.NumberCard;

import java.util.ArrayList;

public class RightShiftInputData {
    public int displayCardFirstIndex;
    public boolean flag_for_func;
    public ArrayList<Card> playerNumcards;
    public RightShiftInputData(ArrayList<Card> playerNumCards, int displayCardFirstIndex, boolean flag_for_func) {
        this.displayCardFirstIndex = displayCardFirstIndex;
        this.playerNumcards = playerNumCards;
        this.flag_for_func = flag_for_func;
    }

    public int getDisplayCardFirstIndex() {return displayCardFirstIndex;}
    public ArrayList<Card> getPlayerNumcards(){return playerNumcards;}
    public boolean getFlag_for_func(){return flag_for_func;}
}
