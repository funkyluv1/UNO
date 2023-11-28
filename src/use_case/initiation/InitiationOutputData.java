package use_case.initiation;
import entities.Game;
import entities.NumberCardsDeck.NumberCardsDeck;
import entities.card.FunctionalCard;
import entities.card.NumberCard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InitiationOutputData {
    final ArrayList<String> playerNames;
    final NumberCardsDeck numberCardsDeck;
    final Map<String, ArrayList<NumberCard>> playerNumCards;
    final Map<String, ArrayList<NumberCard>> playerPlayableNumCards;
    final Map<String, ArrayList<FunctionalCard>> playerFunCards;
    final Map<String, ArrayList<FunctionalCard>> playerPlayableFunCards;
    public InitiationOutputData(ArrayList<String> playerNames, NumberCardsDeck numberCardsDeck,
                                Map<String, ArrayList<NumberCard>> playerNumCards,
                                Map<String, ArrayList<NumberCard>> playerPlayableNumCards ,
                                Map<String, ArrayList<FunctionalCard>> playerPlayableFunCards
                                ){
        this.playerNames = playerNames;
        this.numberCardsDeck = numberCardsDeck;
        this.playerFunCards = playerPlayableFunCards;
        this.playerPlayableFunCards = playerPlayableFunCards;
        this.playerNumCards = playerNumCards;
        this.playerPlayableNumCards = playerPlayableNumCards;
    }

    public ArrayList<String> getPlayerNames() {return this.playerNames;}

    public NumberCardsDeck getNumberCardsDeck(){return this.numberCardsDeck;}

    public Map<String, ArrayList<NumberCard>> getPlayerNumCards(){return this.playerNumCards;}
    public Map<String, ArrayList<NumberCard>> getPlayerPlayableNumCards(){return this.playerPlayableNumCards;}
    public Map<String, ArrayList<FunctionalCard>> getPlayerFunCards(){return this.playerFunCards;}
    public Map<String, ArrayList<FunctionalCard>> getPlayerPlayableFunCards(){return this.playerPlayableFunCards;}


}
