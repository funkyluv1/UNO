package view;

import interface_adapter.Initialized.BottomPanelViewModel;
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

public class BottomPanel extends JPanel implements PropertyChangeListener {

    BottomPanelViewModel bottomPanelViewModel;
    JPanel bottomPanel = new JPanel();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public BottomPanel(BottomPanelViewModel bottomPanelViewModel, SelectCardController selectCardController){
        this.bottomPanelViewModel = bottomPanelViewModel;
        this.bottomPanelViewModel.addPropertyChangeListener(this);

        JButton nextButton = new JButton("Next");
        nextButton.setPreferredSize(new Dimension(130, 200));
        nextButton.setBorder(BorderFactory.createEmptyBorder());
        nextButton.setBackground(Color.GRAY); // fill here for the card's color
        nextButton.setOpaque(true);


        JButton confirmButton = new JButton("Confirm");
        nextButton.setPreferredSize(new Dimension(130, 200));
        nextButton.setBorder(BorderFactory.createEmptyBorder());
        nextButton.setBackground(Color.GRAY); // fill here for the card's color
        nextButton.setOpaque(true);

        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);

        nextButton.setBorder(border);
        confirmButton.setBorder(border);

        nextButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(nextButton)) {
                            CardButtonPanelState currentState = bottomPanelViewModel.getState();
                            //TODO: NextUseCase controller here
                        }
                    }
                }
        );
        bottomPanel.add(nextButton);
        bottomPanel.add(confirmButton);
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
