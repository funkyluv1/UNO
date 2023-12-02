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
        ArrayList<FunctionalCard> cardsOld = new ArrayList<>();

        if (Character.isDigit(text.charAt(0))) {
            ArrayList<String> list = new ArrayList<>();
            list.add(text);
            StringToCardAdapter stringToCardAdapter = new StringToCardAdapter(list);
            cardNew = stringToCardAdapter.convertToNumCards().get(0);//TODO: StringToCardAdapter have bugs; will change everything to Red
        }

        SelectFuncCardInputData selectCardInputData = new SelectFuncCardInputData(cardNew, cardsOld, button_index, selectedIndices);
        selectFuncCardInteractor.execute(selectCardInputData);
    }
}
