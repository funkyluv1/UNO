package use_case.SelectCard;

import entities.card.Card;
import entities.player.Player;

import java.util.ArrayList;
import java.util.HashMap;

import static use_case.initiation.InitiationInteractor.game;

public class SelectCardInteractor implements SelectCardInputBoundary{
    final SelectCardOutputBoundary selectCardOutputDataBoundary;

    public SelectCardInteractor (SelectCardOutputBoundary selectCardOutputDataBoundary) {
        this.selectCardOutputDataBoundary = selectCardOutputDataBoundary;
    }

    @Override
    public void execute(SelectCardInputData selectCardInputData) {
        Card selectedCard = selectCardInputData.getSelectedCardNew();
        Player player = selectCardInputData.getPlayer();
        game.setCurrSelectedCard(selectedCard);

        HashMap<Card, Boolean> playerCards = new HashMap<>();
        for (Card card : player.getNumberCards()){
            playerCards.put(card, card == selectedCard);
        }
        for (Card card : player.getFuncCards()){
            playerCards.put(card, card == selectedCard);
        }

        SelectCardOutputData selectCardOutputData = new SelectCardOutputData(player, playerCards);
        selectCardOutputDataBoundary.prepareSelectCardView(selectCardOutputData);
    }
}
