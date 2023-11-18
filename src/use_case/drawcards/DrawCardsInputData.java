package use_case.drawcards;

import entities.NumberCardsDeck.NumberCardsDeck;
import entities.player.Player;

public class DrawCardsInputData {
    private final Player player;
    private final NumberCardsDeck numberCardsDeck;
    private final int drawNumber;
    public DrawCardsInputData(Player player, NumberCardsDeck numberCardsDeck, int dealNumber){
        this.player = player;
        this.numberCardsDeck = numberCardsDeck;
        this.drawNumber = dealNumber;
    }

    public int getDrawNumber(){return this.drawNumber;};

    public Player getPlayer(){return this.player;};

    public NumberCardsDeck getNumberCardsDeck(){return this.numberCardsDeck;};

}
