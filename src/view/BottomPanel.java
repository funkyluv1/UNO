package view;

import interface_adapter.Confirm.ConfirmController;
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
    JButton nextButton;
    JButton confirmButton;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public BottomPanel(BottomPanelViewModel bottomPanelViewModel, ConfirmController confirmController){
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
                            //TODO: NextUseCase controller here
                        }
                    }
                }
        );
        confirmButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(confirmButton)) {
                            confirmController.execute();
                        }
                    }
                }
        );

        this.nextButton = nextButton;
        this.confirmButton = confirmButton;
        bottomPanel.add(nextButton);
        bottomPanel.add(confirmButton);
}
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        nextButton.setEnabled(bottomPanelViewModel.getState().getNextButtonEnabled());
        confirmButton.setEnabled(bottomPanelViewModel.getState().getConfirmButtonEnabled());

    this.firePropertyChange();
}
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    public void firePropertyChange(){support.firePropertyChange("bottomPanel", null, this.bottomPanel);}
}
