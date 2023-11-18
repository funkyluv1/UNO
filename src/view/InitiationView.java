package view;

import interface_adapter.Initiation.InitiationController;
import interface_adapter.Initiation.InitiationState;
import interface_adapter.Initiation.InitiationViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

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
        this.initiationViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(InitiationViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel playernameInfo = new LabelTextPanel(
                new JLabel("Playername"), playernameInputField);

        JPanel buttons = new JPanel();
        initialize = new JButton(InitiationViewModel.INITIATION_BUTTON_LABEL);
        buttons.add(initialize);
        addPlayer = new JButton("Add player");
        buttons.add(addPlayer);


        initialize.addActionListener( //button set enable
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(initialize)) {
                            InitiationState currentState = initiationViewModel.getState();

                            initiationController.execute(currentState.get_players(), 0);
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        addPlayer.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent evt){
                        if (evt.getSource().equals(addPlayer)) {
                            InitiationState currentState = initiationViewModel.getState();
                            String input = playernameInputField.getText();
                            currentState.set_player(input);
                            playernameInputField.setText("");
                        }
                    }
                }

        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(playernameInfo);

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
        setFields(state);
    }

    private void setFields(InitiationState state) {
        playernameInputField.setText(state.get_players().get(0));
    }
}
