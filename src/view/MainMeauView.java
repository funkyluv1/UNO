package view;

import Assets.BackGroundMusic;
import interface_adapter.Initiation.InitiationState;
import interface_adapter.MainMeau.MainMeauViewModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;

public class MainMeauView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "MainMeau";
    private MainMeauViewModel  mainMeauViewModel;
    private CardLayout cardLayout;
    private JPanel cardPanel;

    public MainMeauView(MainMeauViewModel mainMeauViewModel) {
        this.mainMeauViewModel = mainMeauViewModel;
        this.mainMeauViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("MainMeau Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton startButton = new JButton("Start");
        JButton settingsButton = new JButton("Settings");
        JButton quitButton = new JButton("Quit");

        // Create title label
        JLabel titleLabel = new JLabel("UNO");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        // Add buttons and title label to the frame
        add(titleLabel);
        add(startButton);
        add(settingsButton);
        add(quitButton);

        // Add action listeners to the buttons
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                // Add code to handle the Start button click
                System.out.println("Start button clicked");
                if (evt.getSource().equals(startButton)) {
                    mainMeauViewModel.firePropertyChanged();
                }

            }
        });

        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add code to handle the Settings button click
                System.out.println("Settings button clicked");
            }
        });

        quitButton.addActionListener(new ActionListener() {private JPanel cardPanel;
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add code to handle the Quit button click
                System.out.println("Quit button clicked");
                System.exit(0);
            }
        });
    }

//    public static void main(String[] args){
//        JFrame f = new JFrame("UNO");
//        f.setSize(1920,1080);
//        f.setResizable(false);
//        f.setLayout(null);
//        f.setVisible(true);
//        JPanel panel = new JPanel();
//        panel.setBounds(0,0,1920,1080);
//        panel.setBackground(Color.gray);
//        JButton b1= new JButton("Play Game");
//        b1.setBounds(400,300,700,200);
//        b1.setBackground(Color.yellow);
//
//        /*Create the header text*/
//        JLabel header = new JLabel("SpaceY's UNO Game");
//        header.setBounds(550,150,500,100);
//        header.setFont(new Font("Serif", Font.PLAIN, 50));
//        f.add(b1);
//        f.add(header);
//
//        BackGroundMusic bgm = new BackGroundMusic();
//        bgm.play("src/Assets/M2U - Body Talk.wav");
//        f.addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//                bgm.stop();
//                f.dispose();
//            }
//        });
//    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}