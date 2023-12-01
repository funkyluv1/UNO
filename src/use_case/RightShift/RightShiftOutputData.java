package use_case.RightShift;

import entities.card.NumberCard;

import java.util.ArrayList;
import java.util.Map;

public class RightShiftOutputData {
    final Map<String, ArrayList<NumberCard>> playerNumCards;
    final Map<String, Integer> displayCardsFirstIndex;
    public RightShiftOutputData (Map<String, ArrayList<NumberCard>> playerNumCards, Map<String,Integer> displayCardsFirstIndex) {
        this.playerNumCards = playerNumCards;
        this.displayCardsFirstIndex = displayCardsFirstIndex;
    }
}
