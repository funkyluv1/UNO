package view;

import interface_adapter.Confirm.ConfirmController;
import interface_adapter.Initialized.BottomPanelViewModel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import static use_case.initiation.InitiationInteractor.game;

public class BottomPanel extends JPanel implements PropertyChangeListener {

    BottomPanelViewModel bottomPanelViewModel;
    Panel panel = new Panel(5);

    JButton nextButton;
    JButton confirmButton;
    private int ID = 5;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public BottomPanel(BottomPanelViewModel bottomPanelViewModel, ConfirmController confirmController){
        this.bottomPanelViewModel = bottomPanelViewModel;
        this.bottomPanelViewModel.addPropertyChangeListener(this);

        this.setLayout(new FlowLayout());
        JButton nextButton = new JButton("Next");
        nextButton.setPreferredSize(new Dimension(140, 50));
        nextButton.setBorder(BorderFactory.createEmptyBorder());
        nextButton.setBackground(Color.GRAY); // fill here for the card's color
        nextButton.setOpaque(true);
        this.nextButton = nextButton;
        this.add(nextButton);


        JButton confirmButton = new JButton("Confirm");
        confirmButton.setPreferredSize(new Dimension(140, 50));
        confirmButton.setBorder(BorderFactory.createEmptyBorder());
        confirmButton.setBackground(Color.GRAY); // fill here for the card's color
        confirmButton.setOpaque(true);
        this.confirmButton = confirmButton;
        this.add(confirmButton);

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
                            confirmController.execute(game.getCurrentPlayerIndex());
                        }
                    }
                }
        );

        this.nextButton = nextButton;
        this.confirmButton = confirmButton;
        panel.add(nextButton);
        panel.add(confirmButton);
}
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        nextButton.setEnabled(bottomPanelViewModel.getState().getNextButtonEnabled());
        confirmButton.setEnabled(bottomPanelViewModel.getState().getConfirmButtonEnabled());
//        this.setOpaque(true);

    this.firePropertyChange();
}
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    public void firePropertyChange(){support.firePropertyChange("panel", null, this.panel);}

    public int getID(){return this.ID;}
}
