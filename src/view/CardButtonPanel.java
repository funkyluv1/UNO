package view;

import interface_adapter.Initialized.CardButtonPanelState;
import interface_adapter.Initialized.CardButtonPanelViewModel;
import interface_adapter.Initiation.InitiationState;
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

    public CardButtonPanel(CardButtonPanelViewModel cardButtonPanelViewModel, SelectCardController selectCardController){
        this.cardButtonPanelViewModel = cardButtonPanelViewModel;
        this.cardButtonPanelViewModel.addPropertyChangeListener(this);

        // left shift button
        JButton leftShift = new JButton("left");
        leftShift.setPreferredSize(new Dimension(100, 50));
        leftShift.setForeground(Color.WHITE);
        leftShift.setBackground(Color.BLACK);
        leftShift.setFont(new Font("Arial", Font.BOLD, 14));
        leftShift.setOpaque(true);
        playpanel.add(leftShift);

        for (int i = 0; i < 3; i++) {
            JButton cardButton = new JButton();
            cardButton.setPreferredSize(new Dimension(130, 200));
            cardButton.setBorder(BorderFactory.createEmptyBorder());
            cardButton.setBackground(Color.YELLOW); // fill here for the card's color
            cardButton.setOpaque(true);
            Border border = BorderFactory.createLineBorder(Color.BLUE, 2);
            cardButton.setBorder(border);
            int finalI = i;
            cardButton.addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            if (evt.getSource().equals(cardButton)) {
                                CardButtonPanelState currentState = cardButtonPanelViewModel.getState();
                                selectCardController.execute(currentState.get_players().get(0), cardButton.getText(), 2);
                                //TODO: the current player uses game.playerindex
                            }
                        }
                    }
            );
            playpanel.add(cardButton);
            cardNames.add(cardButton);

        }

        // right shift button
        JButton rightShift = new JButton("right");
        rightShift.setPreferredSize(new Dimension(100, 50));
        rightShift.setForeground(Color.WHITE);
        rightShift.setBackground(Color.BLACK);
        rightShift.setFont(new Font("Arial", Font.BOLD, 14));
        rightShift.setOpaque(true);
        playpanel.add(rightShift);
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
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

        this.firePropertyChange();
    }
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    public void firePropertyChange(){support.firePropertyChange("playpanel", null, this.playpanel);}
}
