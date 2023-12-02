package interface_adapter.SelectFuncCard;

import data_access.StringToCardAdapter;
import entities.card.FunctionalCard;
import entities.card.NumberCard;
import use_case.SelectFuncCard.SelectFuncCardInputData;
import use_case.SelectFuncCard.SelectFuncCardInteractor;

import java.util.ArrayList;

public class SelectFuncCardController {

    final SelectFuncCardInteractor selectFuncCardInteractor;

    public SelectFuncCardController(SelectFuncCardInteractor selectFuncCardInteractor) {
        this.selectFuncCardInteractor = selectFuncCardInteractor;
    }

    public void execute(String cardNewText, ArrayList<String> cardsOldText, int button_index, ArrayList<Integer> selectedIndices) {
        FunctionalCard cardNew = null;

        if (Character.isDigit(cardNewText.charAt(0))) {
            ArrayList<String> list = new ArrayList<>();
            list.add(cardNewText);
            StringToCardAdapter stringToCardAdapter = new StringToCardAdapter(list);
            cardNew = stringToCardAdapter.convertToFuncCards().get(0);
        }

        StringToCardAdapter stringToCardAdapter = new StringToCardAdapter(cardsOldText);
        ArrayList<FunctionalCard> cardsOld = stringToCardAdapter.convertToFuncCards();

        SelectFuncCardInputData selectCardInputData = new SelectFuncCardInputData(cardNew, cardsOld, button_index);
        selectFuncCardInteractor.execute(selectCardInputData);
    }
}
