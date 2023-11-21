package interface_adapter.SelectCard;

import entities.card.Card;
import entities.player.Player;
import use_case.SelectCard.SelectCardInputData;
import use_case.SelectCard.SelectCardInputBoundary;
import use_case.SelectCard.SelectCardInteractor;

public class SelectCardController {

    final SelectCardInputBoundary selectCardInteractor;

    public SelectCardController (SelectCardInputBoundary selectCardInteractor) {
        this.selectCardInteractor = selectCardInteractor;
    }

    public void execute (Player player, Card cardNew) {
        SelectCardInputData selectCardInputData = new SelectCardInputData(player, cardNew);
        selectCardInteractor.execute(selectCardInputData);
    }
}
