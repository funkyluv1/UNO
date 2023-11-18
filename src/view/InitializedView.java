package view;

import interface_adapter.Initialized.InitializedState;
import interface_adapter.Initialized.InitializedViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

//public class InitializedView extends JPanel implements ActionListener, PropertyChangeListener {
//    public final String viewName = "Initialized";
//    private final InitializedViewModel initializedViewModel;
//
//    JLabel playername;
//
//    public InitializedView(InitializedViewModel initializedViewModel) {
//        this.initializedViewModel = initializedViewModel;
//        this.initializedViewModel.addPropertyChangeListener(this);
//
//        JLabel title = new JLabel("Initialized Screen");
//        title.setAlignmentX(Component.CENTER_ALIGNMENT);
//
//        JLabel usernameInfo = new JLabel("Currently players: ");
//        playername = new JLabel();
//
//        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//
//        this.add(title);
//        this.add(usernameInfo);
//        this.add(playername);
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        System.out.println("Click " + e.getActionCommand());
//    }
//
//    @Override
//    public void propertyChange(PropertyChangeEvent evt) {
//        InitializedState state = (InitializedState) evt.getNewValue();
//        // we don't know how many players there are, so we can't create player1, player2 ...
//        // this is the best we can do, display all players in one string
//        String output = "";
//        ArrayList<String> players = state.get_players();
//        for (int i = 0; i < players.size(); i++)
//            output += (players.get(i) + "\n");
//        playername.setText(output);
//    }
//}
public class InitializedView extends JFrame {

    public final String viewName;

    public InitializedView() {
        this.viewName = "InitializedView";
        setTitle("UNO Game");
        setSize(1200, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initializeUI();
    }

    private void initializeUI() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        Color darkRed = new Color(218, 40, 40);
        mainPanel.setBackground(darkRed);

        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new GridLayout(1, 4, 10, 10));
        playerPanel.setOpaque(false);
        ArrayList<Color> colorList = new ArrayList<>();
        colorList.add(new Color(173, 216, 230));
        colorList.add(new Color(255, 255, 210));
        colorList.add(new Color(144, 238, 144));
        colorList.add(new Color(255, 224, 255));



        for (int i = 1; i <= 4; i++) {
            JPanel playerInfo = new JPanel();
            playerInfo.setLayout(new BorderLayout());
            playerInfo.setBorder(BorderFactory.createLineBorder(Color.WHITE, 15));
            Dimension preferredSize = playerInfo.getPreferredSize();
            preferredSize.height = 120;
            playerInfo.setPreferredSize(preferredSize);
            JLabel usernameLabel = new JLabel("PLAYER " + i + " USERNAME");//这个需要根据viewmodel来
            JLabel scoreLabel = new JLabel("Score: 0");
            playerInfo.add(usernameLabel, BorderLayout.NORTH);
            playerInfo.add(scoreLabel, BorderLayout.SOUTH);
            playerPanel.add(playerInfo);
            playerInfo.setBackground(colorList.get(i-1));
        }

        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new BorderLayout());
        cardPanel.setOpaque(false);

        JPanel infopanel = new JPanel();
        infopanel.setOpaque(false);
        JPanel playpanel = new JPanel();
        playpanel.setOpaque(false);
        for (int i = 0; i < 3; i++) {//根据viewmodel改
            JButton cardButton = new JButton("Card " + i);
            playpanel.add(cardButton);
        }
        JButton getCardButton = new JButton("Get Card");
        getCardButton.setPreferredSize(new Dimension(170, 250));


        JButton undoButton = new JButton("Undo");
        JButton whichcolorButton = new JButton();
        whichcolorButton.setPreferredSize(new Dimension(170, 250));
        whichcolorButton.setBackground(Color.BLUE);
        whichcolorButton.setBackground(colorList.get(1)); //需要改这里！！！这个地方展示了正在play的颜色！！！
        infopanel.add(getCardButton);
        infopanel.add(whichcolorButton);
        infopanel.add(undoButton);
        cardPanel.add(infopanel, BorderLayout.NORTH);
        cardPanel.add(playpanel, BorderLayout.CENTER);
        cardPanel.setOpaque(false);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        controlPanel.setOpaque(false);

        JButton nextTurnButton = new JButton("Next Turn");


        controlPanel.add(nextTurnButton);

        mainPanel.add(playerPanel, BorderLayout.NORTH);
        mainPanel.add(cardPanel, BorderLayout.CENTER);
        mainPanel.add(controlPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new InitializedView().setVisible(true);
            }
        });
    }
}