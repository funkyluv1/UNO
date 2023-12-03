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
            ArrayList<String> list = new ArrayList<>();
            list.add(text);
            StringToCardConverter stringToCardAdapter = new StringToCardConverter(list);
            cardNew = stringToCardAdapter.convertToNumCards().get(0);//TODO: StringToCardAdapter have bugs; will change everything to Red
        }
        SelectCardInputData selectCardInputData = new SelectCardInputData(cardNew, button_index);
        selectCardInteractor.execute(selectCardInputData);
    }
}
