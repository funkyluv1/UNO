package view;

import interface_adapter.Initialized.CardButtonPanelState;
import interface_adapter.Initialized.CardButtonPanelViewModel;
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
    JPanel playpanel = new JPanel();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public CardButtonPanel(CardButtonPanelViewModel cardButtonPanelViewModel, SelectCardController selectCardController, RightShiftController rightShiftController){
        this.cardButtonPanelViewModel = cardButtonPanelViewModel;
        this.cardButtonPanelViewModel.addPropertyChangeListener(this);

        JButton leftShiftButton = new JButton();
        leftShiftButton.setPreferredSize(new Dimension(50, 50));
        leftShiftButton.setBorder(BorderFactory.createEmptyBorder());
        leftShiftButton.setBackground(Color.BLACK); // fill here for the card's color
        leftShiftButton.setOpaque(true);
        Border border = BorderFactory.createLineBorder(Color.BLUE, 2);
        leftShiftButton.setBorder(border);

        for (int i = 0; i < 3; i++) {
            JButton cardButton = new JButton();
            cardButton.setPreferredSize(new Dimension(130, 200));
            cardButton.setBorder(BorderFactory.createEmptyBorder());
            cardButton.setBackground(Color.YELLOW); // fill here for the card's color
            cardButton.setOpaque(true);
            //Border border = BorderFactory.createLineBorder(Color.BLUE, 2);
            cardButton.setBorder(border);
            int finalI = i;
            cardButton.addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            if (evt.getSource().equals(cardButton)) {
                                CardButtonPanelState currentState = cardButtonPanelViewModel.getState();
                                selectCardController.execute(currentState.get_players().get(0), cardButton.getText(), finalI);
                                //TODO: the current player uses game.playerindex
                            }
                        }
                    }
            );

            playpanel.add(cardButton);
            cardNames.add(cardButton);
        }
        JButton rightShiftButton = new JButton();
        rightShiftButton.setPreferredSize(new Dimension(50, 50));
        rightShiftButton.setBorder(BorderFactory.createEmptyBorder());
        rightShiftButton.setBackground(Color.BLACK); // fill here for the card's color
        rightShiftButton.setOpaque(true);
        //Border border = BorderFactory.createLineBorder(Color.BLUE, 2);
        rightShiftButton.setBorder(border);

        playpanel.add(leftShiftButton);
        cardNames.add(leftShiftButton);
        playpanel.add(rightShiftButton);
        cardNames.add(rightShiftButton);

        rightShiftButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(rightShiftButton)) {
                            CardButtonPanelState currentState = cardButtonPanelViewModel.getState();
                            rightShiftController.execute(currentState.get_players().get(0));
                        }
                    }
                }
        );

    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // property change for the three card panels
        for (int i = 0; i < 3; i++) {
            String name = cardButtonPanelViewModel.getState().get_Number_Cards().get(i).getString();

            if (name.charAt(1) == 'B'){cardNames.get(i).setBackground(Color.BLUE);}
            else if (name.charAt(1) == 'R'){cardNames.get(i).setBackground(Color.RED);}
            else if (name.charAt(1) == 'G'){cardNames.get(i).setBackground(Color.GREEN);}

            cardNames.get(i).setText(name);

            if (i == 0){cardNames.get(i).setEnabled(cardButtonPanelViewModel.getState().getButton1enabled());}
            else if (i == 1){cardNames.get(i).setEnabled(cardButtonPanelViewModel.getState().getButton2enabled());}
            else cardNames.get(i).setEnabled(cardButtonPanelViewModel.getState().getButton3enabled());

            cardNames.get(0).setEnabled(false);//There are bugs in FindPlayableCards; assume the first numCard is not playable
        }
        cardNames.get(cardButtonPanelViewModel.getState().getButtonindexHighlighted()).setBackground(Color.CYAN);

        // property change for rightshift


        this.firePropertyChange();
    }
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    public void firePropertyChange(){support.firePropertyChange("playpanel", null, this.playpanel);}
}
