package data_access;

import entities.card.*;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringToCardConverter {
    private final ArrayList<String> cardInfo;

    public StringToCardConverter(ArrayList<String> cardInfo){
        this.cardInfo = cardInfo;
    }

    public ArrayList<NumberCard> convertToNumCards() {
        ArrayList<NumberCard> output = new ArrayList<>();

        for (String s : this.cardInfo) {
            if (s.charAt(0) == 'A') {
                s = s.replace('A', '1');
            }
            else if (s.charAt(0) == 'J' || s.charAt(0) == 'K' || s.charAt(0) == 'Q'){
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
        ArrayList<FunctionalCard> output = new ArrayList<>();

        for (String cardEncoding : this.cardInfo) {
//            Pattern pattern = Pattern.compile("([a-zA-Z]+)(\\d)([a-zA-Z])");
//            Matcher matcher = pattern.matcher(cardEncoding);
//            String type = matcher.group(1);
//            String value = matcher.group(2);
//            String color = matcher.group(3);
            FunctionalCard cardNew;
            if (cardEncoding.charAt(0) == '+'){
                if (cardEncoding.charAt(1) == '2'){
                    cardNew = new FunctionalCardFactory(3, "any", "PlusTwo").createCard();
                } else {cardNew =  new FunctionalCardFactory(3, "any", "PlusFour").createCard();}
            } else {cardNew = new FunctionalCardFactory(3, "any", "Skip").createCard();}

            output.add(cardNew);
        }

        return output;
    }
}
