package View;

//import interface_adapter.initiation.InitiationController;
//import interface_adapter.initiation.IntiationState;
import interface_adapter.Initiation.InitiationViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class GameView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "game view";
    //private final InitiationController initiationController;
    //private final InitiationViewModel initiationViewModel;
    private final JButton initialize;


    public GameView(InitiationController initiationController, InitiationViewModel initiationViewModel) {
        this.initiationController = initiationController;
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
                            initiationController.execute();
                            //InitiationState currentState = InitationViewModel.getState();
                            //Game game = currentState.getGame();
                            String game = "game data";

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
        SignupState state = (SignupState) evt.getNewValue();
        if (state.getUsernameError() != null) {
            JOptionPane.showMessageDialog(this, state.getUsernameError());
        }
    }
}
