package use_case.PreTurn;

import entities.card.FunctionalCard;
import entities.card.FunctionalCardFactory;
import entities.card.NumberCard;

import java.util.ArrayList;

public class FindPlayableCards implements FindPlayableCardsInterface{

    public FindPlayableCards(){};

    @Override
    public ArrayList<FunctionalCard> findPlayableFunctionalCards(String roundColor, ArrayList<FunctionalCard> functionalCards) {
        ArrayList<FunctionalCard>playableFunctionalCards = new ArrayList<>();
        String roundC;
        if (roundColor.length() > 1){
            char rC = (char) (roundColor.charAt(0) - 'a' + 'A');
            roundC = String.valueOf(rC);
        } else {roundC = roundColor;}
        for (FunctionalCard functionalCard : functionalCards){
            if (functionalCard.getColor().equals(roundC) || functionalCard.getColor().equals("any")){
                playableFunctionalCards.add(functionalCard);
                functionalCard.isUsable = true;
            }
        }
        return playableFunctionalCards;
    }

    @Override
    public ArrayList<NumberCard> findPlayableNumberCards(String roundColor, ArrayList<NumberCard> numberCards) {
        ArrayList<NumberCard> playableNumberCards = new ArrayList<>();
        String roundC;
        if (roundColor.length() > 1){
            char rC = (char) (roundColor.charAt(0) - 'a' + 'A');
            roundC = String.valueOf(rC);
        } else {roundC = roundColor;}
        for (NumberCard numberCard : numberCards){
            if (numberCard.getColor().equals(roundC)){
                playableNumberCards.add(numberCard);
                numberCard.isUsable = true;
            }
        }
        return playableNumberCards;
    }
}

