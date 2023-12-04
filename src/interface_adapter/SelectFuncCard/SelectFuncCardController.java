package interface_adapter.SelectFuncCard;

import data_access.StringToCardConverter;
import data_access.StringToCardConverter;
import entities.card.*;
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

        if (cardNewText.charAt(0) == '+'){
            if (cardNewText.charAt(1) == '2'){
                cardNew = new FunctionalCardFactory(3, "any", "PlusTwo").createCard();
            } else {cardNew = new FunctionalCardFactory(3, "any", "PlusFour").createCard();}
        } else {cardNew = new FunctionalCardFactory(3, "any", "Skip").createCard();}

        StringToCardConverter stringToCardConverter = new StringToCardConverter(cardsOldText);
        ArrayList<FunctionalCard> cardsOld = stringToCardConverter.convertToFuncCards();

        SelectFuncCardInputData selectCardInputData = new SelectFuncCardInputData(cardNew, cardsOld, button_index);
        selectFuncCardInteractor.execute(selectCardInputData);
    }
}
