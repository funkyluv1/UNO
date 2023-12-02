package view;

import entities.card.FunctionalCard;
import entities.card.NumberCard;
import interface_adapter.Initialized.CardButtonPanelState;
import interface_adapter.Initialized.CardButtonPanelViewModel;
import interface_adapter.Initialized.FunCardButtonPanelState;
import interface_adapter.Initialized.FunCardButtonPanelViewModel;
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

public class FunCardButtonPanel extends JPanel implements PropertyChangeListener {

    FunCardButtonPanelViewModel funCardButtonPanelViewModel;
    ArrayList<JButton> cardNames = new ArrayList<>();
    JPanel playpanel = new JPanel();
    JButton leftButton;
    JButton rightButton;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public FunCardButtonPanel(FunCardButtonPanelViewModel funCardButtonPanelViewModel,
                              SelectCardController selectCardController,
                              RightShiftController rightShiftController,
                              LeftShiftController leftShiftController){
        this.funCardButtonPanelViewModel = funCardButtonPanelViewModel;
        this.funCardButtonPanelViewModel.addPropertyChangeListener(this);

        // left shift button
        JButton leftShift = new JButton("left");
        leftShift.setPreferredSize(new Dimension(100, 50));
        leftShift.setForeground(Color.WHITE);
        leftShift.setBackground(Color.BLACK);
        leftShift.setFont(new Font("Arial", Font.BOLD, 14));
        leftShift.setOpaque(true);
        leftShift.setEnabled(false);
        playpanel.add(leftShift);
        this.leftButton = leftShift;

        // right shift button
        JButton rightShift = new JButton("right");
        rightShift.setPreferredSize(new Dimension(100, 50));
        rightShift.setForeground(Color.WHITE);
        rightShift.setBackground(Color.BLACK);
        rightShift.setFont(new Font("Arial", Font.BOLD, 14));
        rightShift.setOpaque(true);
        rightShift.setEnabled(false);
        playpanel.add(rightShift);
        this.rightButton = rightShift;

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
                                FunCardButtonPanelState currentState = funCardButtonPanelViewModel.getState();
                                selectCardController.execute(cardButton.getText(),finalI);
                            }
                        }
                    }
            );
            playpanel.add(cardButton);
            cardNames.add(cardButton);
        }
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ArrayList<String> playableFunCardsString = new ArrayList<>();
        ArrayList<String> selectedFunCardsString = new ArrayList<>();
        for (FunctionalCard functionalCard : funCardButtonPanelViewModel.getState().get_Playable_Fun_Cards()){
            playableFunCardsString.add(functionalCard.getString());
        }
        for (FunctionalCard functionalCard : funCardButtonPanelViewModel.getState().get_Selected_Fun_Cards()){
            selectedFunCardsString.add(functionalCard.getString());
        }

        int startInd = funCardButtonPanelViewModel.getState().getdisplayCardsFirstIndex();
        int card_with_value;
        if (funCardButtonPanelViewModel.getState().get_Fun_Cards().size() >= 3){
            card_with_value = 3;
        } else {card_with_value = funCardButtonPanelViewModel.getState().get_Fun_Cards().size();}

        for (int i = startInd; i < startInd + card_with_value; i++) {
            String name = funCardButtonPanelViewModel.getState().get_Fun_Cards().get(i).getString();

            cardNames.get(i - startInd).setText(name);
            if (playableFunCardsString.contains(name)){
                cardNames.get(i - startInd).setEnabled(true);
            }

            if (selectedFunCardsString.contains(name)){
                cardNames.get(i - startInd).setBackground(Color.YELLOW);
            }
        }
        leftButton.setEnabled(funCardButtonPanelViewModel.getState().getLeftButtonEnabled());
        rightButton.setEnabled(funCardButtonPanelViewModel.getState().getRightButtonEnabled());

        this.firePropertyChange();
    }
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    public void firePropertyChange(){support.firePropertyChange("playpanel", null, this.playpanel);}
}