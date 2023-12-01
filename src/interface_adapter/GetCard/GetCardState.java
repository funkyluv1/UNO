package interface_adapter.GetCard;

import entities.card.NumberCard;

public class GetCardState {

    private NumberCard numberCard;
    public GetCardState(GetCardState copy) {this.numberCard = copy.numberCard;}
    public GetCardState(){}

    public NumberCard getNumberCard() {return this.numberCard;}

    public void setNumberCard(NumberCard numberCard) {this.numberCard = numberCard;}

}
