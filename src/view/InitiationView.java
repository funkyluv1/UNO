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
import java.util.ArrayList;

public class InitiationView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName =  "initiation";
    private final InitiationViewModel initiationViewModel;

    private final JTextField playernameInputField = new JTextField(15);
    private final JTextArea displayArea = new JTextArea(4, 15);

    private final TextButton initialize;
    private final TextButton addPlayer;

    private final InitiationController initiationController;


    public InitiationView(InitiationController initiationController, InitiationViewModel initiationViewModel) {

        this.initiationController = initiationController;
        this.initiationViewModel = initiationViewModel;
        this.initiationViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Initiate the Game!");
        Font comicSansFont = new Font("Comic Sans MS", Font.PLAIN, 32);
        title.setFont(comicSansFont);
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        playernameInputField.setOpaque(false);
        playernameInputField.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        playernameInputField.setForeground(Color.WHITE);

        JLabel nameLabel = new JLabel("Playername");
        nameLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        nameLabel.setForeground(Color.WHITE);
        LabelTextPanel playernameInfo = new LabelTextPanel(nameLabel, playernameInputField);
        playernameInfo.setFont(comicSansFont);
        playernameInfo.setForeground(Color.WHITE);
        playernameInfo.setOpaque(false);

        JPanel buttons = new JPanel();
        buttons.setPreferredSize(new Dimension(400, 200));


        initialize = new TextButton(InitiationViewModel.INITIATION_BUTTON_LABEL);
        initialize.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));

        initialize.setAlignmentX(Component.CENTER_ALIGNMENT);
        initialize.setAlignmentY(Component.CENTER_ALIGNMENT);
        initialize.setBorderPainted(true);

        buttons.add(initialize);

        addPlayer = new TextButton("Add player");
        addPlayer.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
        addPlayer.setAlignmentX(Component.CENTER_ALIGNMENT);
        addPlayer.setAlignmentY(Component.CENTER_ALIGNMENT);
        addPlayer.setBorderPainted(true);

        buttons.add(addPlayer);
        buttons.setOpaque(false);

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

        displayArea.setEditable(false);
        displayArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        displayArea.setAlignmentY(Component.CENTER_ALIGNMENT);
        displayArea.setForeground(Color.WHITE);
        displayArea.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
        displayArea.setOpaque(false);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        addPlayer.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent evt){
                        if (evt.getSource().equals(addPlayer)) {
                            InitiationState currentState = initiationViewModel.getState();
                            String input = playernameInputField.getText();
                            currentState.set_player(input);
                            addPlayer();
                            playernameInputField.setText("");
                        }
                    }
                }

        );

        this.add(Box.createRigidArea(new Dimension(0, 150)));
        this.add(title);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(playernameInfo);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(displayArea);
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


    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.setColor(Color.WHITE);
        setBackground(Color.RED);
        Graphics2D g2d = (Graphics2D) g.create();

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int width = 1100; // Width of the oval
        int height = 600; // Height of the oval

        // Set the center of rotation
        g2d.rotate(Math.toRadians(-30), centerX, centerY);

        // Draw the tilted oval
        g2d.fillOval(centerX - width / 2, centerY - height / 2, width, height);

        g2d.setColor(Color.RED);
        g2d.fillOval((centerX - width / 2) + 30, (centerY - height / 2) + 30, 1040, 540);

        g2d.dispose();
    }

    private void addPlayer() {
        ArrayList<String> users = initiationViewModel.getState().get_players();
        String username = users.get(users.size() - 1);

        if (!username.isEmpty()) {
            displayArea.append("                                                              " + "Player " + users.size() + ": " + username + "\n");
        }
        if (users.size() >= 4) {
            playernameInputField.setEnabled(false);
        }
    }
}
