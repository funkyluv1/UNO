package entities;

import entities.card.*;
import entities.player.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Game {

    private static Game instance;
    private Card topCard;
    private int drawCard;
    private boolean isSkipped;

    private Game() {
        String[] randColor = {"red", "blue", "green", "yellow"};
        int randColorIndex = (int) Math.floor(Math.random()*4);
        int randValue = (int) Math.floor(Math.random()*9);
        this.topCard = new NumberCard(randValue, randColor[randColorIndex]);
        drawCard = 0;
        isSkipped = false;
    }

    public static Game getInstance() {
        if (instance == null)
            instance = new Game();
        return instance;
    }

    public Card getTopCard() {
        return topCard;
    }

    public void setTopCard(Card card) {
        topCard = card;
    }

    public int getDrawCard() {
        return drawCard;
    }

    public void setDrawCard(int drawNum){
        drawCard = drawNum;
    }

    public boolean getSkipped() {
        return isSkipped;
    }

    public void setSkipped(boolean skipped) {
        isSkipped = skipped;
    }

}
