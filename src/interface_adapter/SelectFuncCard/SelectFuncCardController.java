package interface_adapter.SelectFuncCard;

import data_access.StringToCardConverter;
import data_access.StringToCardConverter;
import entities.card.FunctionalCard;
import entities.card.NumberCard;
import use_case.SelectCard.SelectCardInputDataBoundary;
import use_case.SelectFuncCard.SelectFuncCardInputData;
import use_case.SelectFuncCard.SelectFuncCardInputDataBoundary;
import use_case.SelectFuncCard.SelectFuncCardInteractor;
import use_case.SelectFuncCard.SelectFuncCardOutputDataBoundary;

import java.util.ArrayList;

public class SelectFuncCardController {

    final SelectFuncCardInputDataBoundary selectFuncCardInteractor;

    public SelectFuncCardController(SelectFuncCardInputDataBoundary selectFuncCardInteractor) {
        this.selectFuncCardInteractor = selectFuncCardInteractor;
    }

    public void execute(String cardNewText, ArrayList<String> cardsOldText, int button_index) {
        FunctionalCard cardNew = null;

        if (Character.isDigit(cardNewText.charAt(0))) {
            ArrayList<String> list = new ArrayList<>();
            list.add(cardNewText);
            StringToCardConverter stringToCardConverter = new StringToCardConverter(list);
            cardNew = stringToCardConverter.convertToFuncCards().get(0);
        }

        StringToCardConverter stringToCardConverter = new StringToCardConverter(cardsOldText);
        ArrayList<FunctionalCard> cardsOld = stringToCardConverter.convertToFuncCards();

        SelectFuncCardInputData selectCardInputData = new SelectFuncCardInputData(cardNew, cardsOld, button_index);
        selectFuncCardInteractor.execute(selectCardInputData);
    }
}
