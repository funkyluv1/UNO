package view;

import entities.card.NumberCard;
import interface_adapter.Initialized.InitializedState;
import interface_adapter.Initialized.InitializedViewModel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class InitializedView extends JPanel implements ActionListener, PropertyChangeListener{

    public final String viewName;
    private final InitializedViewModel initializedViewModel;

    JPanel bigPanel;
    JPanel cardButtonPanel;
    JPanel bottomPanel;
    JPanel playerPanel;
    JPanel getCardPanel;
    JPanel funCardPanel;
    JLabel jLabel;
    ArrayList<Color> colorList;

    ArrayList<JLabel> usernames = new ArrayList<>();
    ArrayList<JButton> cardNames = new ArrayList<>();

    public InitializedView(InitializedViewModel initializedViewModel) {
        JLabel title = new JLabel("Initialized Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.viewName = "Initialized";
        this.initializedViewModel = initializedViewModel;
        this.initializedViewModel.addPropertyChangeListener(this);
        this.cardButtonPanel = new JPanel();
        this.bottomPanel = new JPanel();
        this.playerPanel = new JPanel();
        this.getCardPanel = new JPanel();
        this.funCardPanel = new JPanel();
        this.bigPanel = new JPanel();

        bigPanel.add(cardButtonPanel);
        add(bottomPanel);
        add(playerPanel);
        bigPanel.add(funCardPanel);
        bigPanel.add(getCardPanel);
        add(bigPanel);
    }
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click " + e.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ArrayList<JPanel> panels = (ArrayList<JPanel>) evt.getNewValue();
        int i = 0;
        for (JPanel panel : panels) {
            if (i == 0) {
                this.remove(playerPanel);
                playerPanel = panel;
            } else if (i == 1) {
                bigPanel.remove(getCardPanel);
                getCardPanel = panel;
            } else if (i == 2) {
                bigPanel.remove(cardButtonPanel);
                cardButtonPanel = panel;
            } else if (i == 3) {
                this.remove(funCardPanel);
                bigPanel.remove(funCardPanel);
                funCardPanel = panel;
            } else if (i == 4) {
                this.remove(bottomPanel);
                bottomPanel = panel;
            }
            i += 1;
        }
        setSize(1200, 1000);
        this.setLayout(new BorderLayout());
        Color darkRed = new Color(218, 40, 40);
//        this.setBackground(Color.CYAN);

        bigPanel.setLayout(new BorderLayout());
        playerPanel.setPreferredSize(new Dimension(1200, 130));
        playerPanel.setBounds(0,0,1200,130);
        playerPanel.setBackground(Color.WHITE);

        JPanel gap = new JPanel();
        gap.setPreferredSize(new Dimension(1100, 100));
        gap.setOpaque(false);

        bigPanel.setPreferredSize(new Dimension(1100, 500));
        bigPanel.setBounds(0, 100, 1100,800);
        bigPanel.setOpaque(false);
        getCardPanel.setPreferredSize(new Dimension(1200, 300));
//        getCardPanel.setBackground(Color.yellow);
        getCardPanel.setOpaque(false);
        cardButtonPanel.setPreferredSize(new Dimension(650, 200));
//        cardButtonPanel.setBackground(Color.black);
        cardButtonPanel.setOpaque(false);
        funCardPanel.setPreferredSize(new Dimension(650, 200));
//        funCardPanel.setBackground(Color.green);
        funCardPanel.setOpaque(false);
        bottomPanel.setPreferredSize(new Dimension(600, 100));
//        bottomPanel.setBackground(Color.BLUE);
        bottomPanel.setOpaque(false);

        this.add(gap, BorderLayout.NORTH);
        this.add(playerPanel, BorderLayout.NORTH);
        this.add(bottomPanel, BorderLayout.SOUTH);
        this.add(bigPanel, BorderLayout.CENTER);
        bigPanel.add(getCardPanel, BorderLayout.NORTH);
        bigPanel.add(cardButtonPanel, BorderLayout.WEST);
        bigPanel.add(funCardPanel, BorderLayout.EAST);

//        bigPanel.add(this.getCardPanel, BorderLayout.NORTH);
//        bigPanel.add(this.cardButtonPanel, BorderLayout.WEST);
//        bigPanel.add(this.funCardPanel, BorderLayout.EAST);
//        getCardPanel.setPreferredSize(new Dimension(1200, 300));
//        cardButtonPanel.setPreferredSize(new Dimension(600, 150));
//        funCardPanel.setPreferredSize(new Dimension(600, 150));
//        this.add(bigPanel, BorderLayout.CENTER);

//        bigPanel.setOpaque(false);
//        playerPanel.setOpaque(false);
//        getCardPanel.setOpaque(false);
//        funCardPanel.setOpaque(true);
//        bottomPanel.setOpaque(true);
//        cardButtonPanel.setOpaque(true);


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