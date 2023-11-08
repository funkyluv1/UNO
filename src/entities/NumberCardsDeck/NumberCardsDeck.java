package entities.NumberCardsDeck;

public class NumberCardsDeck {

    private String id;
    public int remainingCards;

    public NumberCardsDeck(String id, int remainingCards){
        this.id = id;
        this.remainingCards = remainingCards;
    }

    public String getId(){return this.id;}
    public int getRemainingCards(){return this.remainingCards;}

    public int setRemainingCards(int remainingCards){
        this.remainingCards = remainingCards;
        return this.remainingCards;
    }
}
