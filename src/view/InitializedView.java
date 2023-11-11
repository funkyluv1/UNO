package view;

import interface_adapter.Initialized.InitializedState;
import interface_adapter.Initialized.InitializedViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class InitializedView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "Game Begin";

    public InitializedViewModel initializedViewModel;



    /**
     * A window with a title and a JButton.
     */
    public InitializedView(InitializedViewModel initializedViewModel) {
        this.initializedViewModel = initializedViewModel;
        this.initializedViewModel.addPropertyChangeListener(this);
        JLabel title = new JLabel("Game Board");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel handPanel = new JPanel();
        handPanel.setLayout(new FlowLayout());
//        for (Card card : gameBoard.getHand()) {
//            CardPanel cardPanel = new CardPanel(card);
//            handPanel.add(cardPanel);


        this.add(title);
        this.add(buttons);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        InitializedState state = (InitializedState) evt.getNewValue();
        //username.setText(state.getUsername());
    }

}
