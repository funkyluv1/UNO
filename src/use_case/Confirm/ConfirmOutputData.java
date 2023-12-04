package use_case.Confirm;

import entities.card.NumberCard;

public class ConfirmOutputData {
    NumberCard numberCard;
    public ConfirmOutputData(NumberCard numberCard){
        this.numberCard = numberCard;
    }

    public NumberCard getNumberCard(){return this.numberCard;}
    public int getNumber(){return Integer.parseInt(String.valueOf(this.numberCard.getString().charAt(0)));}
}
