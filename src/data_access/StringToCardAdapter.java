package data_access;

import entities.card.FunctionalCard;
import entities.card.NumberCard;
import entities.card.NumberCardFactory;

import java.util.ArrayList;
import java.util.Map;

public class StringToCardAdapter {
    private ArrayList<String> cardInfo;

    public StringToCardAdapter(ArrayList<String> cardInfo){
        this.cardInfo = cardInfo;
    }

    public ArrayList<NumberCard> convertToNumCards() {
        ArrayList<NumberCard> output = new ArrayList<>();

        for (String s : this.cardInfo) {
            if (s.charAt(0) == 'A') {
                s = s.replace('A', '1');
            }else if (s.charAt(0) == 'J' || s.charAt(0) == 'K' || s.charAt(0) == 'Q'){
                String num = String.valueOf((int) (Math.random() * 9));
                s = s.replace(s.charAt(0), num.charAt(0));
            }
            Integer cardValue = Integer.parseInt(s.substring(0,1));
            /**
             * A NumberCard's color value should be the first capital character of color, such as "G"
             * "Y"...
             */
            if (s.charAt(1) == 'S'){
                NumberCardFactory builder = new NumberCardFactory(cardValue, "G");
                output.add(builder.createCard());
            } else if (s.charAt(1) == 'C') {
                NumberCardFactory builder = new NumberCardFactory(cardValue, "B");
                output.add(builder.createCard());
            } else if (s.charAt(1) == 'D') {
                NumberCardFactory builder = new NumberCardFactory(cardValue, "Y");
                output.add(builder.createCard());
            } else {
                NumberCardFactory builder = new NumberCardFactory(cardValue, "R");
                output.add(builder.createCard());
            }
        }
        return output;
    }

    public ArrayList<FunctionalCard> convertToFuncCards() {
        return null;
    }
}
