package use_case.NextTurn;

import data_access.FileUserDataAccessObject;
import entities.card.Card;
import entities.card.FunctionalCard;
import entities.card.NumberCard;
import use_case.PreTurn.FindPlayableCardsInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static use_case.initiation.InitiationInteractor.game;

public class NextTurnInteractor implements NextTurnInputDataBoundary{
    final NextTurnDataAccessInterface fileUserDataAccessObject;
    final NextTurnOutputDataBoundary nextTurn_presenter;
    final FindPlayableCardsInterface findPlayableCardsInterface;

    public NextTurnInteractor(NextTurnDataAccessInterface fileUserDataAccessObject, NextTurnOutputDataBoundary nextTurn_presenter,
                              FindPlayableCardsInterface findPlayableCardsInterface){
        this.fileUserDataAccessObject = fileUserDataAccessObject;
        this.nextTurn_presenter = nextTurn_presenter;
        this.findPlayableCardsInterface = findPlayableCardsInterface;
    }


    public void execute(NextTurnInputData nextTurnInputData){
        int player_index = nextTurnInputData.getPlayer_index();
        String name = fileUserDataAccessObject.getPlayer(nextTurnInputData.getPlayer_index()).getPlayerName();
        ArrayList<NumberCard> number_cards = fileUserDataAccessObject.getPlayer(nextTurnInputData.getPlayer_index()).getNumberCards();
        ArrayList<FunctionalCard> fun_cards = fileUserDataAccessObject.getPlayer(nextTurnInputData.getPlayer_index()).getFuncCards();

        Map<String, ArrayList<NumberCard>> playerNumCards = new HashMap<String, ArrayList<NumberCard>>();
        playerNumCards.put(name, number_cards);
        Map<String, ArrayList<FunctionalCard>> playerFunCards = new HashMap<String, ArrayList<FunctionalCard>>();
        playerFunCards.put(name, fun_cards);
        Map<String, ArrayList<NumberCard>> playerPlayableNumCards = new HashMap<String, ArrayList<NumberCard>>();
        playerPlayableNumCards.put(name, findPlayableCardsInterface.findPlayableNumberCards(game.getTopCard().getColor(), number_cards));
        Map<String, ArrayList<FunctionalCard>> playerPlayableFunCards = new HashMap<String, ArrayList<FunctionalCard>>();
        playerPlayableFunCards.put(name, findPlayableCardsInterface.findPlayableFunctionalCards(game.getTopCard().getColor(), fun_cards));

        playerNumCards.put(name, number_cards);
//        if (number_cards.size() > 3){
//            ArrayList<NumberCard> number_cards_empty = new ArrayList<NumberCard>();
//            for (int i = 0; i < 3; i++){
//                number_cards_empty.add(number_cards.get(i));
//            }
//            number_cards = number_cards_empty;
//        }
//
//        if (fun_cards.size() > 3){
//            ArrayList<FunctionalCard> fun_cards_empty = new ArrayList<FunctionalCard>();
//            for (int i = 0; i < 3; i++){
//                fun_cards_empty.add(fun_cards.get(i));
//            }
//            fun_cards = fun_cards_empty;
//        }
        nextTurn_presenter.prepare_view(new NextTurnOutputData(player_index, playerNumCards, playerFunCards, playerPlayableNumCards, playerPlayableFunCards));
    }
}
