package use_case.GetCard;

import entities.card.NumberCard;

public class GetCardOutputData {

    final NumberCard numberCard;

    public GetCardOutputData(NumberCard numberCard) {
        this.numberCard = numberCard;
    }

    public NumberCard getNumberCard() {return this.numberCard;}


}
