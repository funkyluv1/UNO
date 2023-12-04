package view;

import entities.card.Card;
import entities.card.NumberCard;
import interface_adapter.Initialized.CardButtonPanelState;
import interface_adapter.Initialized.CardButtonPanelViewModel;
import interface_adapter.Initiation.InitiationState;
import interface_adapter.LeftShift.LeftShiftController;
import interface_adapter.RightShift.RightShiftController;
import interface_adapter.SelectCard.SelectCardController;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class CardButtonPanel extends JPanel implements PropertyChangeListener {

    CardButtonPanelViewModel cardButtonPanelViewModel;
    ArrayList<JButton> cardNames = new ArrayList<>();
    Panel playpanel = new Panel(3);
    JButton leftButton;
    JButton rightButton;
    SelectCardController selectCardController;
    private int id = 3;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public CardButtonPanel(CardButtonPanelViewModel cardButtonPanelViewModel, SelectCardController selectCardController,
                           RightShiftController rightShiftController, LeftShiftController leftShiftController){
        this.cardButtonPanelViewModel = cardButtonPanelViewModel;
        this.cardButtonPanelViewModel.addPropertyChangeListener(this);
        this.selectCardController = selectCardController;

        // left shift button
        TextButton leftShift = new TextButton("<");
        leftShift.setPreferredSize(new Dimension(50, 60));
        leftShift.setOpaque(false);
        leftShift.setEnabled(false);
        leftShift.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(leftShift)) {
                            CardButtonPanelState currentState = cardButtonPanelViewModel.getState();
                            leftShiftController.execute(currentState.getdisplayCardsFirstIndex(), false);
                        }
                    }
                }
        );
        this.leftButton = leftShift;
        playpanel.add(this.leftButton);

        for (int i = 0; i < 3; i++) {
            CardButton cardButton = new CardButton();
            cardButton.setOpaque(false);
            Border border = BorderFactory.createLineBorder(Color.BLUE, 2);
            cardButton.setBorder(border);
            int finalI = i;
            playpanel.add(cardButton);
            cardNames.add(cardButton);
        }

        // right shift button
        TextButton rightShift = new TextButton(">");
        rightShift.setPreferredSize(new Dimension(50, 60));
        rightShift.setEnabled(false);
        rightShift.setOpaque(false);
        rightShift.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(rightShift)) {
                            CardButtonPanelState currentState = cardButtonPanelViewModel.getState();
                            ArrayList<Card> cardsList = new ArrayList<>();
                            cardsList.addAll(currentState.get_Number_Cards());
                            rightShiftController.execute(cardsList, currentState.getdisplayCardsFirstIndex(), false);
                        }
                    }
                }
        );
        this.rightButton = rightShift;
        playpanel.add(this.rightButton);

    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ArrayList<String> playableNumCardsString = new ArrayList<>();
        for (NumberCard numberCard : cardButtonPanelViewModel.getState().get_Highlighted_Number_Cards()){
            playableNumCardsString.add(numberCard.getString());
        }

        int startInd = cardButtonPanelViewModel.getState().getdisplayCardsFirstIndex();
        int card_with_value;
        if (cardButtonPanelViewModel.getState().get_Number_Cards().size() >= 3){
            card_with_value = 3;
        } else {card_with_value = cardButtonPanelViewModel.getState().get_Number_Cards().size();}

        for (int i = startInd; i < startInd + card_with_value; i++) {
            String name = cardButtonPanelViewModel.getState().get_Number_Cards().get(i).getString();

            // CardButton: default is Disabled; Enable playable Card;
            cardNames.get(i - startInd).setText(name);
            if (playableNumCardsString.contains(name)){
                cardNames.get(i - startInd).setEnabled(true);
            } else {cardNames.get(i - startInd).setEnabled(false);}

//          TODO: probably don't need highlight any more; contrast between Enabled and Disabled Button is distinguishable
//          cardNames.get(cardButtonPanelViewModel.getState().getButtonindexHighlighted()).setBackground(Color.CYAN);

//            if (name.charAt(1) == 'B'){cardNames.get(i - startInd).setBackground(Color.BLUE);}
//            else if (name.charAt(1) == 'R'){cardNames.get(i - startInd).setBackground(Color.RED);}
//            else if (name.charAt(1) == 'G'){cardNames.get(i - startInd).setBackground(Color.GREEN);}

            //If someone has selected one card beforehand, then disable the enabled playable card.
            if (cardButtonPanelViewModel.getState().getOneCardsSelected()){
                cardNames.get(i - startInd).setEnabled(false);
            }

            int finalI = i;
            cardNames.get(i - startInd).addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            if (evt.getSource().equals(cardNames.get(finalI - startInd))) {
                                CardButtonPanelState currentState = cardButtonPanelViewModel.getState();
                                selectCardController.execute(currentState.get_Number_Cards().get(finalI).getString(),finalI - startInd);
                            }
                        }
                    }
            );

//            if (i == startInd){cardNames.get(i - startInd).setEnabled(cardButtonPanelViewModel.getState().getButton1enabled());}
//            else if (i == startInd + 1){cardNames.get(i - startInd).setEnabled(cardButtonPanelViewModel.getState().getButton2enabled());}
//            else cardNames.get(i - startInd).setEnabled(cardButtonPanelViewModel.getState().getButton3enabled());

        }
        leftButton.setEnabled(cardButtonPanelViewModel.getState().getLeftButtonEnabled());
        rightButton.setEnabled(cardButtonPanelViewModel.getState().getRightButtonEnabled());
        playpanel.setPreferredSize(new Dimension(600, 200));
        this.firePropertyChange();
    }
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    public void firePropertyChange(){support.firePropertyChange("playpanel", null, this.playpanel);}
    public int getID(){return this.id;}

}
