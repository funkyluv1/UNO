package interface_adapter.drawcards;

import entities.NumberCardsDeck;
import entities.player.Player;
import use_case.drawcards.DrawCardsInputData;
import use_case.drawcards.DrawCardsInputDataBoundary;

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
