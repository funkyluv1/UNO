package interface_adapter.DrawCards;

import entities.NumberCardsDeck.NumberCardsDeck;
import entities.player.Player;
import use_case.DrawCards.DrawCardsInputData;
import use_case.DrawCards.DrawCardsInputDataBoundary;

public class DrawCardsController {
    final DrawCardsInputDataBoundary drawCardsInteractor;

    public DrawCardsController(DrawCardsInputDataBoundary drawCardsInteractor) {
        this.drawCardsInteractor = drawCardsInteractor;
    }

    public void execute(Player player, NumberCardsDeck numberCardsDeck, int drawNumber) {
        DrawCardsInputData inputData = new DrawCardsInputData(player, numberCardsDeck, drawNumber);
        drawCardsInteractor.execute(inputData);
    }

}
