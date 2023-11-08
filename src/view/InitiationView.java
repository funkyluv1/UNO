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
import java.util.ArrayList;

public class InitiationView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName =  "game view";
    private final InitiationViewModel initiationViewModel;

    final JTextField playernameInputField = new JTextField(15);

    private final JButton initialize;
    private final JButton addPlayer;


    private final InitiationController initiationController;

    public InitiationView(InitiationController initiationController, InitiationViewModel initiationViewModel) {
        this.initiationController = initiationController;
        this.initiationViewModel = initiationViewModel;
        initiationViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(InitiationViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();
        initialize = new JButton(InitiationViewModel.INITIATION_BUTTON_LABEL);
        buttons.add(initialize);
        addPlayer = new JButton("Add player");
        buttons.add(addPlayer);


        initialize.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(initialize)) {
                            ArrayList<String> players = new ArrayList<>();
                            initiationController.execute(players);
                            InitiationState currentState = initiationViewModel.getState();
                            JFrame initializedframe = new JFrame();
                            JLabel initializedlabel = new JLabel();
                            initializedframe.add(initializedlabel);
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
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        InitiationState state = (InitiationState) evt.getNewValue();
        setFields(state);}

    private void setFields(InitiationState state) {
        playernameInputField.setText(state.getPlayers().get(0));
    }
}
