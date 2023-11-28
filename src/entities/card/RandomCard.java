package entities.card;

public class RandomCard extends FunctionalCard{
    /**
     * Constructs a new RandomCard object.
     */
    public RandomCard() {
        super((int) Math.floor(Math.random()*9),
                new String[]{"red", "blue", "green", "yellow"}[(int) Math.floor(Math.random()*4)], "Random");
//        super.changeID(Integer.toString(value));
    }
}
