package view;

import entities.card.Card;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class CardView extends JPanel implements PropertyChangeListener {

    ArrayList<Card> cards;
    int i;

    public CardView(ArrayList<Card> cards, int i){
        this.cards = cards;
        this.i = i; //开始展示的第一张牌在手牌中的index

    }

    public void init_card(){
        JPanel handcardview = new JPanel();

        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new FlowLayout());
        cardPanel.setOpaque(false);

        while (i <= 3 && i <= cards.size()){
            JButton cardButton = new JButton(""+cards.get(i).getValue());
            cardButton.setPreferredSize(new Dimension(130, 200));
            cardButton.setBorder(BorderFactory.createEmptyBorder());
            String colorName = cards.get(i).getColor();
            Color color = null;
            switch (colorName) {
                case "Blue": color = Color.BLUE; break;
                case "Green": color = Color.GREEN; break;
                case "Red": color = Color.RED; break;
                case "Yellow": color = Color.YELLOW; break;
            }
            cardButton.setBackground(color);
            cardButton.setOpaque(true);
            handcardview.add(cardButton);


        }



    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        init_card();

    }
}
