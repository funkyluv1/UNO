package interface_adapter.SelectCard;

import data_access.StringToCardConverter;
import data_access.StringToCardConverter;
import entities.card.Card;
import entities.card.CardFactory;
import entities.card.NumberCard;
import entities.card.NumberCardFactory;
import entities.player.Player;
import use_case.SelectCard.SelectCardInputData;
import use_case.SelectCard.SelectCardInputDataBoundary;
import use_case.SelectCard.SelectCardInteractor;

import java.util.ArrayList;

public class SelectCardController {

    final SelectCardInputDataBoundary selectCardInteractor;

    public SelectCardController (SelectCardInputDataBoundary selectCardInteractor) {
        this.selectCardInteractor = selectCardInteractor;
    }

    public void execute (String text, int button_index) {
        NumberCard cardNew = null;
        if (Character.isDigit(text.charAt(0))) {
            NumberCardFactory numberCardFactory = new NumberCardFactory(
                    Integer.parseInt(String.valueOf(text.charAt(0))), String.valueOf(text.charAt(1)));
            cardNew = numberCardFactory.createCard();

        SelectCardInputData selectCardInputData = new SelectCardInputData(cardNew, button_index);
        selectCardInteractor.execute(selectCardInputData);
        }
    }
}
