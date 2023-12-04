package use_case.NextTurn;

import data_access.FileUserDataAccessObject;
import entities.card.Card;
import entities.card.FunctionalCard;
import entities.card.NumberCard;
import entities.card.NumberCardFactory;
import entities.player.Player;
import use_case.PostTurn.PostTurnInputData;
import use_case.PostTurn.PostTurnInputDataBoundary;
import use_case.PostTurn.PostTurnInteractor;
import use_case.PreTurn.FindPlayableCardsInterface;
import use_case.PreTurn.PreTurnInputData;
import use_case.PreTurn.PreTurnInputDataBoundary;
import use_case.PreTurn.PreTurnInteractor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static use_case.initiation.InitiationInteractor.game;

public class NextTurnInteractor implements NextTurnInputDataBoundary {
    final NextTurnDataAccessInterface fileUserDataAccessObject;
    final NextTurnOutputDataBoundary nextTurn_presenter;
    final FindPlayableCardsInterface findPlayableCardsInterface;
    final PostTurnInteractor postTurnInteractor;
    final PreTurnInteractor preTurnInteractor;

    public NextTurnInteractor(NextTurnDataAccessInterface fileUserDataAccessObject, NextTurnOutputDataBoundary nextTurn_presenter,
                              FindPlayableCardsInterface findPlayableCardsInterface, PostTurnInteractor postTurnInteractor,
                              PreTurnInteractor preTurnInteractor){
        this.fileUserDataAccessObject = fileUserDataAccessObject;
        this.nextTurn_presenter = nextTurn_presenter;
        this.findPlayableCardsInterface = findPlayableCardsInterface;
        this.postTurnInteractor = postTurnInteractor;
        this.preTurnInteractor = preTurnInteractor;
    }


    public void execute(NextTurnInputData nextTurnInputData){
        int player_index = nextTurnInputData.getPlayer_index();
        String name = fileUserDataAccessObject.getPlayer(nextTurnInputData.getPlayer_index()).getPlayerName();
        ArrayList<String> players = new ArrayList<String>();
        for (int b = 0; b < 4; b++){
            players.add(fileUserDataAccessObject.getPlayer(b).getPlayerName());
        }
//        Map<String, Integer> playerwithindex = new HashMap<String, Integer>();
//        for (int a = 0; a < 3; a ++){
//            playerwithindex.put(players.get(a), 0);
//        }

        ArrayList<NumberCard> number_cards = fileUserDataAccessObject.getPlayer(nextTurnInputData.getPlayer_index()).getNumberCards();
        ArrayList<FunctionalCard> fun_cards = fileUserDataAccessObject.getPlayer(nextTurnInputData.getPlayer_index()).getFuncCards();
        NumberCard selectedNumCard = (NumberCard) game.getCurrSelectedNumberCard();
        ArrayList<FunctionalCard> selectedFunCard = game.getCurrSelectedFunCard();
        if (selectedNumCard != null){
            fileUserDataAccessObject.play_Card_and_update_DAO(name,selectedNumCard, selectedFunCard);

            game.setCurrSelectedNumberCard(null);
            game.addNumCardsinRound(selectedNumCard);
            game.setCurrSelectedFunCard(new ArrayList<>());
        }


        // call post turn
        PostTurnInputData postTurnInputData = new PostTurnInputData(player_index, fun_cards, number_cards, name);
        postTurnInteractor.execute(postTurnInputData);

        game.updateCurrentPlayerIndex();
        // call pre turn
        PreTurnInputData preTurnInputData = new PreTurnInputData((player_index + 1) % 4);
        preTurnInteractor.execute(preTurnInputData);

        player_index = game.getCurrentPlayerIndex();
        number_cards = fileUserDataAccessObject.getPlayer((nextTurnInputData.getPlayer_index() + 1) % 4).getNumberCards();
        fun_cards = fileUserDataAccessObject.getPlayer((nextTurnInputData.getPlayer_index() + 1) % 4).getFuncCards();
        ArrayList<NumberCard> playerplablenumcards = findPlayableCardsInterface.findPlayableNumberCards(game.getTopCard().getColor(), number_cards);
        ArrayList<FunctionalCard> playerplablefuncards = findPlayableCardsInterface.findPlayableFunctionalCards(game.getTopCard().getColor(), fun_cards);


//        Map<String, ArrayList<NumberCard>> playerNumCards = new HashMap<String, ArrayList<NumberCard>>();
//        playerNumCards.put(name, number_cards);
//        Map<String, ArrayList<FunctionalCard>> playerFunCards = new HashMap<String, ArrayList<FunctionalCard>>();
//        playerFunCards.put(name, fun_cards);
//        Map<String, ArrayList<NumberCard>> playerPlayableNumCards = new HashMap<String, ArrayList<NumberCard>>();
//        playerPlayableNumCards.put(name, findPlayableCardsInterface.findPlayableNumberCards(game.getTopCard().getColor(), number_cards));
//        Map<String, ArrayList<FunctionalCard>> playerPlayableFunCards = new HashMap<String, ArrayList<FunctionalCard>>();
//        playerPlayableFunCards.put(name, findPlayableCardsInterface.findPlayableFunctionalCards(game.getTopCard().getColor(), fun_cards));
//
//        playerNumCards.put(name, number_cards);
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
        nextTurn_presenter.prepare_view(new NextTurnOutputData(player_index, number_cards, fun_cards, playerplablenumcards, playerplablefuncards));
    }
}
