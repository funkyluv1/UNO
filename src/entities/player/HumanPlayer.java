package entities.player;

import entities.Game;
import entities.card.*;

import java.util.ArrayList;
import java.util.List;

/**
 * HumanPlayer is the concrete class for all instances of human players and a subclass
 * of Player. Core behaviours of this class are controlled by users.
 * In addition to the attributes inherited from the Player class, the HumanPlayer
 * class has an additional attribute:
 * - selectedCard: the card selected by the user to be dealt in this round
 *
 * @author Cynthia Luo
 */
public class HumanPlayer extends Player {
    private Card selectedCard;

    public HumanPlayer(int userID, String playerName, ArrayList<NumberCard> hand){
        super(userID, playerName, hand);
        selectedCard = null;
    }

    /**
     * Returns a chosen Card object to be dealt by this HumanPlayer object and removes
     * it from the hand.
     *
     * @param topCard the most recently placed card in the game
     * @return the Card object dealt
     */
    @Override
    public Card dealCard(Card topCard) {
        if (selectedCard instanceof NumberCard) {
            getNumberCards().remove(selectedCard);
        }
        else if (selectedCard != null) {
            getNumberCards().remove(selectedCard);
        }
        Card dealtCard = selectedCard;
        selectedCard = null;
        return dealtCard;
    }

    /**
     * Returns selectedCard attribute of this HumanPlayer
     *
     * @return the selectedCard attribute of this HumanPlayer
     */
    public Card getSelectedCard() {
        return selectedCard;
    }

    /**
     * Sets the selectedCard attribute of this HumanPlayer to [card], if [card] is
     * usable during this round
     *
     * @param card the card selected
     */
    public void setSelectedCard(Card card) {
        // TODO: "highlight" this card after the GUI part is done
        if (card.isUsable) {
            selectedCard = card;
        }
    }

    @Override
    public void preTurn(Game game){
        NumberCard topCard = game.getTopCard();
        ArrayList<Card> funcCards = game.getFuncCard();
        super.updateUsableCards(topCard, funcCards);
        for (Card funcCard : funcCards) {
            if (funcCard instanceof SkipCard) {
                game.setSkipped(true);
            }
            if (funcCard instanceof PlusTwoCard) {
                game.setDrawCard(game.getDrawCard() + 2);
            }
            if (funcCard instanceof PlusFourCard) {
                game.setDrawCard(game.getDrawCard() + 4);
            }
            // TODO: add cases for Wild, Bomb, HotPotato, Random, if applicable
        }
    }

    @Override
    public void inTurn(Game game){
        // TODO: implement me
    }

    @Override
    public void postTurn(Game game){
        // TODO: implement me
    }

}
