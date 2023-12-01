package use_case.RightShift;

import entities.card.NumberCard;

import java.util.ArrayList;

public class RightShiftInputData {
    public int displayCardFirstIndex;
    public ArrayList<NumberCard> playerNumcards;
    public RightShiftInputData(ArrayList<NumberCard> playerNumCards, int displayCardFirstIndex) {
        this.displayCardFirstIndex = displayCardFirstIndex;
        this.playerNumcards = playerNumCards;
    }

    public int getDisplayCardFirstIndex() {return displayCardFirstIndex;}
    public ArrayList<NumberCard> getPlayerNumcards(){return playerNumcards;}
}
