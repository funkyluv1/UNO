package interface_adapter.DrawCards;

import entities.card.FunctionalCard;
import entities.card.NumberCard;
import interface_adapter.DrawCards.DrawCardsState;

import java.util.ArrayList;

public class DrawCardsState {
    private ArrayList<String> players = new ArrayList<>();
    private ArrayList<NumberCard> numberCards;
    private ArrayList<FunctionalCard> functionalCards;
    private NumberCard numberCard0;
    private NumberCard numberCard1;
    private NumberCard numberCard2;


    public NumberCard getNumberCard0() {
        return numberCard0;
    }

    public NumberCard getNumberCard1() {
        return numberCard1;
    }

    public NumberCard getNumberCard2() {
        return numberCard2;
    }

    public void setNumberCard0(NumberCard numberCard0) {
        this.numberCard0 = numberCard0;
    }

    public void setNumberCard1(NumberCard numberCard1) {
        this.numberCard1 = numberCard1;
    }

    public void setNumberCard2(NumberCard numberCard2) {
        this.numberCard2 = numberCard2;
    }
    public DrawCardsState(DrawCardsState copy){

        players = copy.players;
        numberCards = copy.numberCards;
        functionalCards = copy.functionalCards;
    }

    public DrawCardsState(){}

    public ArrayList<String> get_players(){return players;}

    public void set_player(String player){this.players.add(player);}

    public ArrayList<NumberCard> getNumberCards() {
        return numberCards;
    }

    public void setNumberCards(ArrayList<NumberCard> numberCards) {
        this.numberCards = numberCards;
    }

    public ArrayList<FunctionalCard> getFunctionalCards() {
        return functionalCards;
    }

    public void setFunctionalCards(ArrayList<FunctionalCard> functionalCards) {
        this.functionalCards = functionalCards;
    }
}
