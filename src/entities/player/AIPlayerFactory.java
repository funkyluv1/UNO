package entities.player;

import entities.card.FunctionalCard;
import entities.card.NumberCard;

import java.util.ArrayList;

public class AIPlayerFactory implements PlayerFactory {
    private final ArrayList<String> botNames = new ArrayList<String>();

    public AIPlayerFactory(){
        botNames.add("Bot1");
        botNames.add("Bot2");
        botNames.add("Bot3");
        botNames.add("Bot4");
        botNames.add("Bot5");
    };

    @Override
    public AIPlayer create(String playerName, ArrayList<NumberCard> numcards, ArrayList<FunctionalCard> functionalCards, int displayFirstCardIndex) {
        AIPlayer aiPlayer = new AIPlayer(playerName, numcards, displayFirstCardIndex);
        aiPlayer.setFuncCards(functionalCards);
        return aiPlayer;
    }
}