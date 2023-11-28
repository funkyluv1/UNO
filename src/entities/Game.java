package entities;

import entities.card.*;
import entities.player.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Game {

    private static Game instance;
    private NumberCard topCard;
    private ArrayList<Card> funcCardList;
    private int drawCard;
    private boolean isSkipped;
    private int plusN; //the total number of additional cards need to be drawn after +2 and +4 cards

    private int currentPlayerIndex;

    private Object currSelectedCard;

    private int maxCardNum;
    private Player currWinner;
    private ArrayList<String> playersList;

    private Game() {
        String[] randColor = {"red", "blue", "green", "yellow"};
        int randColorIndex = (int) Math.floor(Math.random()*4);
        int randValue = (int) Math.floor(Math.random()*9);
        this.topCard = new NumberCard(randValue, randColor[randColorIndex]);
        drawCard = 0;
        isSkipped = false;
        plusN = 0;
        currentPlayerIndex = 0;
        currSelectedCard = null;
        maxCardNum = 0;
        currWinner = null;
    }

    public static Game getInstance() {
        if (instance == null)
            instance = new Game();
        return instance;
    }

    public NumberCard getTopCard() {
        return topCard;
    }

    public void setTopCard(NumberCard card) {
        topCard = card;
    }

    public ArrayList<Card> getFuncCard() {
        return funcCardList;
    }

    public void setFuncCard(ArrayList<Card> funcCards) {
        funcCardList = funcCards;
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

    public int getPlusN() {
        return plusN;
    }

    public void setPlusN(int plusN) {
        this.plusN = plusN;
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public void updateCurrentPlayerIndex() {
        currentPlayerIndex += 1;
    }

    public void setCurrSelectedCard(Object card) {
        currSelectedCard = card;
    }
    public Object getCurrSelectedCard() {
        return currSelectedCard;
    }

    public void setMaxCardNum(int n) {
        maxCardNum = n;
    }

    public int getMaxCardNum() {
        return maxCardNum;
    }

    public void setCurrWinner(Player player) {
        currWinner = player;
    }

    public Player getCurrWinner() {
        return currWinner;
    }
}
