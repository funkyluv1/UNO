package use_case.initiation;
import entities.Game;
import entities.NumberCardsDeck.NumberCardsDeck;
import entities.card.NumberCard;

import java.util.ArrayList;

public class InitiationOutputData {
    final ArrayList<String> playerNames;
    final NumberCardsDeck numberCardsDeck;
    //TODO: need a Map<String username, ArrayList<NumberCards>, ArrayList<FunctionalCards>
    // to store information of the cards in each player's hands;
    //

    public InitiationOutputData(ArrayList<String> playerNames, NumberCardsDeck numberCardsDeck) {
        this.playerNames = playerNames;
        this.numberCardsDeck = numberCardsDeck;
    }

    public ArrayList<String> getPlayerNames() {return this.playerNames;}

    public NumberCardsDeck getNumberCardsDeck(){return this.numberCardsDeck;}


}
