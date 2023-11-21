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

public class InitializedView extends JPanel implements ActionListener, PropertyChangeListener{

    public final String viewName;
    private final InitializedViewModel initializedViewModel;

    ArrayList<JLabel> usernames = new ArrayList<>();

    public InitializedView(InitializedViewModel initializedViewModel) {
        JLabel title = new JLabel("Initialized Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.viewName = "Initialized";
        this.initializedViewModel = initializedViewModel;
        this.initializedViewModel.addPropertyChangeListener(this);

        setSize(1200, 1000);

        this.setLayout(new BorderLayout());
        Color darkRed = new Color(218, 40, 40);
        this.setBackground(darkRed);

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
            JLabel usernameLabel = new JLabel("Default player name");
            usernames.add(usernameLabel);
            JLabel scoreLabel = new JLabel("Score: 0");
            playerInfo.add(usernameLabel, BorderLayout.NORTH);
            playerInfo.add(scoreLabel, BorderLayout.SOUTH);
            playerPanel.add(playerInfo);
            playerInfo.setBackground(colorList.get(i - 1));
        }

        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new BorderLayout());
        cardPanel.setOpaque(false);

        JPanel infopanel = new JPanel();

        JPanel playpanel = new JPanel();


        for (int i = 0; i < 3; i++) {//根据viewmodel改
            JButton cardButton = new JButton("Card " + i);
            cardButton.setPreferredSize(new Dimension(130, 200));
            cardButton.setBorder(BorderFactory.createEmptyBorder());
            cardButton.setBackground(Color.YELLOW); // fill here for the card's color
            cardButton.setOpaque(true);
            playpanel.add(cardButton);
        }
        JButton getCardButton = new JButton("Get Card");
        getCardButton.setPreferredSize(new Dimension(130, 200));
        getCardButton.setBorder(BorderFactory.createEmptyBorder()); //!!!!!!这一行非常重要，如果button不去除边框就直接更改背景颜
        //色，将会出现只有边上一圈改变了颜色的现象

        getCardButton.setForeground(Color.WHITE);

        // 设置按钮背景颜色为黑色
        getCardButton.setBackground(Color.BLACK);

        // 设置按钮文本的字体为粗体
        getCardButton.setFont(new Font("Arial", Font.BOLD, 14));

        // 对于某些外观和感觉（如Mac OS的默认外观），需要这个设置才能使背景颜色生效
        getCardButton.setOpaque(true);


        JButton undoButton = new JButton("Undo");
        JButton whichcolorButton = new JButton();
        whichcolorButton.setPreferredSize(new Dimension(130, 200));
        whichcolorButton.setBorder(BorderFactory.createEmptyBorder());
        whichcolorButton.setOpaque(true);
        whichcolorButton.setBackground(Color.BLUE); //需要改这里！！！这个地方展示了正在play的颜色！！！
        infopanel.add(getCardButton);
        infopanel.add(whichcolorButton);
        infopanel.add(undoButton);
        infopanel.setOpaque(false);
        playpanel.setOpaque(false);
        cardPanel.add(infopanel, BorderLayout.NORTH);
        cardPanel.add(playpanel, BorderLayout.CENTER);
        cardPanel.setOpaque(false);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        controlPanel.setOpaque(false);

        JButton nextTurnButton = new JButton("Next Turn");


        controlPanel.add(nextTurnButton);

        this.add(playerPanel, BorderLayout.NORTH);
        this.add(cardPanel, BorderLayout.CENTER);
        this.add(controlPanel, BorderLayout.SOUTH);

    }
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click " + e.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        InitializedState state = (InitializedState) evt.getNewValue();
        ArrayList<String> players = state.get_players();

        for (int i = 0; i< players.size(); i++){  // usernames.size() == 4
            usernames.get(i).setText(players.get(i));
        }

    }

}