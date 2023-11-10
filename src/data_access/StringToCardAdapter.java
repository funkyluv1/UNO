package data_access;

import entities.card.NumberCard;
import entities.card.NumberCardFactory;

import java.util.ArrayList;

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
            }

            if (s.charAt(1) == 'S'){
                NumberCardFactory builder = new NumberCardFactory(s.charAt(1), "Green");
                output.add(builder.createCard());
            } else if (s.charAt(1) == 'C') {
                NumberCardFactory builder = new NumberCardFactory(s.charAt(1), "Blue");
                output.add(builder.createCard());
            } else if (s.charAt(1) == 'D') {
                NumberCardFactory builder = new NumberCardFactory(s.charAt(1), "Yellow");
                output.add(builder.createCard());
            } else {
                NumberCardFactory builder = new NumberCardFactory(s.charAt(1), "Red");
                output.add(builder.createCard());
            }

        }

        return output;
    }
}
