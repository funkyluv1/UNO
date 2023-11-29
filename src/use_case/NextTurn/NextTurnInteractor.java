package use_case.NextTurn;

import data_access.FileUserDataAccessObject;
import entities.card.Card;
import entities.card.FunctionalCard;
import entities.card.NumberCard;

import java.util.ArrayList;

public class NextTurnInteractor implements NextTurnInputDataBoundary{
    final NextTurnDataAccessInterface fileUserDataAccessObject;
    final NextTurnOutputDataBoundary nextTurn_presenter;

    public NextTurnInteractor(NextTurnDataAccessInterface fileUserDataAccessObject, NextTurnOutputDataBoundary nextTurn_presenter){
        this.fileUserDataAccessObject = fileUserDataAccessObject;
        this.nextTurn_presenter = nextTurn_presenter;
    }


    public void execute(NextTurnInputData nextTurnInputData){
        String name = fileUserDataAccessObject.getPlayer(nextTurnInputData.getPlayer_index()).getPlayerName();
        ArrayList<NumberCard> number_cards = fileUserDataAccessObject.getPlayer(nextTurnInputData.getPlayer_index()).getNumberCards();
        ArrayList<FunctionalCard> fun_cards = fileUserDataAccessObject.getPlayer(nextTurnInputData.getPlayer_index()).getFuncCards();
        if (number_cards.size() > 3){
            ArrayList<NumberCard> number_cards_empty = new ArrayList<NumberCard>();
            for (int i = 0; i < 3; i++){
                number_cards_empty.add(number_cards.get(i));
            }
            number_cards = number_cards_empty;
        }

        if (fun_cards.size() > 3){
            ArrayList<FunctionalCard> fun_cards_empty = new ArrayList<FunctionalCard>();
            for (int i = 0; i < 3; i++){
                fun_cards_empty.add(fun_cards.get(i));
            }
            fun_cards = fun_cards_empty;
        }
        nextTurn_presenter.prepare_view(name, number_cards, fun_cards);
    }
}
