package view;

import interface_adapter.Initiation.InitiationController;
import interface_adapter.Initiation.InitiationState;
import interface_adapter.Initiation.InitiationViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class InitiationView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName =  "game view";
    //private final InitiationController initiationController;
    private final InitiationViewModel initiationViewModel;
    private final JButton initialize;


    public InitiationView(InitiationController initiationController, InitiationViewModel initiationViewModel) {
        //this.initiationController = initiationController;
        this.initiationViewModel = initiationViewModel;
        initiationViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(InitiationViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();
        initialize = new JButton(InitiationViewModel.INITIATION_BUTTON_LABEL);
        buttons.add(initialize);


        initialize.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(initialize)) {
                            //initiationController.execute();
                            InitiationState currentState = initiationViewModel.getState();
                            //Game game = currentState.getGame();
                            String game = "game data";
                            JFrame gameframe = new JFrame();
                            JLabel gamelabel = new JLabel(game);
                            gameframe.add(gamelabel);
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(buttons);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showConfirmDialog(this, "Cancel not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    }
}
