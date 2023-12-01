package use_case.DrawCards;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DrawCardsResponseExtractFacade {
    String responseString;
    public DrawCardsResponseExtractFacade(StringBuilder response){
        this.responseString = response.toString();
    }

    //Todo: debug
    public String DrawCardsExtractID(){
        int i = this.responseString.indexOf("deck_id");
        int x = i + 11;
        StringBuilder stringBuilder = new StringBuilder();
        while (!this.responseString.substring(x, x+1).equals("\"")){
            stringBuilder.append(this.responseString.substring(x, x+1));
            x ++;
        }
        return stringBuilder.toString();
    }

    //Todo: debug
    public String DrawCardsExtractRemaining(){
        int i = this.responseString.indexOf("remaining");
        int x = i + 11;
        StringBuilder stringBuilder = new StringBuilder();
        while (!this.responseString.substring(x, x+1).equals(",")){
            stringBuilder.append(this.responseString.substring(x, x+1));
            x ++;
        }
        return stringBuilder.toString().strip();
    }

    /*Precondition: Only called after a NumberCardsDeck is created*/
    public ArrayList<String> DrawCardsExtractNumCards(){
        ArrayList<String> numCardsInfo = new ArrayList<String>();
        Pattern pattern = Pattern.compile("\"code\": \"([^\"]+)\"");
        Matcher matcher = pattern.matcher(responseString);

        while (matcher.find()) {
            String codeValue = matcher.group(1);
            numCardsInfo.add(codeValue);
        }

        return numCardsInfo;
    }


}
