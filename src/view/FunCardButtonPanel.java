package view;

import entities.card.Card;
import entities.card.FunctionalCard;
import entities.card.NumberCard;
import interface_adapter.Initialized.CardButtonPanelState;
import interface_adapter.Initialized.CardButtonPanelViewModel;
import interface_adapter.Initialized.FunCardButtonPanelState;
import interface_adapter.Initialized.FunCardButtonPanelViewModel;
import interface_adapter.Initiation.InitiationState;
import interface_adapter.LeftShift.LeftShiftController;
import interface_adapter.RightShift.RightShiftController;
import interface_adapter.SelectFuncCard.SelectFuncCardController;

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
    Panel playpanel = new Panel(4);
    JButton leftButton;
    JButton rightButton;
    private int id = 4;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public FunCardButtonPanel(FunCardButtonPanelViewModel funCardButtonPanelViewModel,
                              SelectFuncCardController selectFuncCardController,
                              RightShiftController rightShiftController,
                              LeftShiftController leftShiftController){
        this.funCardButtonPanelViewModel = funCardButtonPanelViewModel;
        this.funCardButtonPanelViewModel.addPropertyChangeListener(this);

        // left shift button
        TextButton leftShift = new TextButton("<");
//        leftShift.setPreferredSize(new Dimension(50, 60));
//        leftShift.setForeground(Color.WHITE);
//        leftShift.setBackground(Color.BLACK);
//        leftShift.setFont(new Font("Arial", Font.BOLD, 14));
        leftShift.setOpaque(false);
        leftShift.setEnabled(false);
        leftShift.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(leftShift)) {
                            FunCardButtonPanelState currentState = funCardButtonPanelViewModel.getState();
                            leftShiftController.execute(currentState.getdisplayCardsFirstIndex(), true);
                        }
                    }
                }
        );
        playpanel.add(leftShift);
        this.leftButton = leftShift;

        for (int i = 0; i < 3; i++) {
            CardButton cardButton = new CardButton();
//            cardButton.setPreferredSize(new Dimension(100, 100));
//            cardButton.setBorder(BorderFactory.createEmptyBorder());
//            cardButton.setBackground(Color.YELLOW); // fill here for the card's color
//            cardButton.setOpaque(true);
//            Border border = BorderFactory.createLineBorder(Color.BLUE, 2);
//            cardButton.setBorder(border);
            int finalI = i;
            cardButton.addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            if (evt.getSource().equals(cardButton)) {
                                FunCardButtonPanelState currentState = funCardButtonPanelViewModel.getState();
                                ArrayList<String> selectedCards = new ArrayList<>();
                                for (FunctionalCard functionalCard : currentState.get_Selected_Fun_Cards()){
                                    selectedCards.add(functionalCard.getString());
                                }
                                selectFuncCardController.execute(cardButton.getText(), selectedCards, finalI);
                            }
                        }
                    }
            );
            playpanel.add(cardButton);
            cardNames.add(cardButton);
        }

        // right shift button
        TextButton rightShift = new TextButton(">");
//        rightShift.setPreferredSize(new Dimension(50, 60));
//        rightShift.setForeground(Color.WHITE);
//        rightShift.setBackground(Color.BLACK);
//        rightShift.setFont(new Font("Arial", Font.BOLD, 14));
        rightShift.setOpaque(false);
        rightShift.setEnabled(false);
        rightShift.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(rightShift)) {
                            FunCardButtonPanelState currentState = funCardButtonPanelViewModel.getState();
                            ArrayList<Card> cardList = new ArrayList<>();
                            cardList.addAll(currentState.get_Fun_Cards());
                            rightShiftController.execute(cardList, currentState.getdisplayCardsFirstIndex(), true);
                        }
                    }
                }
        );
        playpanel.add(rightShift);
        this.rightButton = rightShift;


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
            cardNames.get(i - startInd).setToolTipText(name);
            if (playableFunCardsString.contains(name)){
                cardNames.get(i - startInd).setEnabled(true);
            }

            if (funCardButtonPanelViewModel.getState().getAllButtonDisable()){
                cardNames.get(i - startInd).setEnabled(false);
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
    public int getID(){return this.id;}

}
