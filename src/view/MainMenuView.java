package view;

import interface_adapter.Start.StartController;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class MainMenuView extends JPanel implements ActionListener {
    public final String viewName =  "main menu";

    private final StartController startController;

    JPanel menuPanel = new JPanel();

    public MainMenuView(StartController startController) {
        this.startController = startController;

        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBackground(Color.RED);

        ImageIcon title = new ImageIcon("gameTitle.png");
        Image image = title.getImage(); // transform it
        Image newimg = image.getScaledInstance(500, 150,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        title = new ImageIcon(newimg);
        JLabel titleLabel = new JLabel(title);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setAlignmentY(Component.CENTER_ALIGNMENT);


        TextButton startButton = new TextButton("START");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.setAlignmentY(Component.CENTER_ALIGNMENT);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startController.execute();
            }
        });

        TextButton quitButton = new TextButton("EXIT");
        quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        quitButton.setAlignmentY(Component.CENTER_ALIGNMENT);

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add logic to handle the quit button click
                System.exit(0);
            }
        });

        // Add components to the menuPanel
        menuPanel.add(Box.createRigidArea(new Dimension(0, 200)));
        menuPanel.add(titleLabel);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 30))); // Add some spacing
        menuPanel.add(startButton);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add some spacing
        menuPanel.add(quitButton);
        menuPanel.setOpaque(false);
        add(menuPanel, BorderLayout.CENTER);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

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

}