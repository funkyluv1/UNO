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

    private JPanel cardPanel;
    JPanel cardButtonPanel;
    JPanel bottomPanel;
    JPanel playerPanel;
    JPanel getCardPanel;
    JPanel funCardPanel;

    ArrayList<JLabel> usernames = new ArrayList<>();
    ArrayList<JButton> cardNames = new ArrayList<>();

    public InitializedView(InitializedViewModel initializedViewModel) {
        JLabel title = new JLabel("Initialized Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.viewName = "Initialized";
        this.initializedViewModel = initializedViewModel;
        this.initializedViewModel.addPropertyChangeListener(this);
        this.cardButtonPanel = initializedViewModel.getState().get_CardButtonPanel();
        this.bottomPanel = initializedViewModel.getState().getBottomPanel();
        this.playerPanel = initializedViewModel.getState().getPlayerPanel();
        this.getCardPanel = initializedViewModel.getState().getcardButtonPanel();
        this.funCardPanel = initializedViewModel.getState().getFunCardButtonPanel();

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

        this.cardPanel = new JPanel();
        cardPanel.setLayout(new BorderLayout());
        cardPanel.setOpaque(false);

        JPanel infopanel = new JPanel();

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
        cardPanel.add(infopanel, BorderLayout.NORTH);
        cardPanel.setOpaque(false);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        controlPanel.setOpaque(false);

        JButton nextTurnButton = new JButton("Next Turn");


        controlPanel.add(nextTurnButton);

        this.add(cardPanel, BorderLayout.CENTER);
        this.add(controlPanel, BorderLayout.SOUTH);

    }
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click " + e.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ArrayList<JPanel> panels = (ArrayList<JPanel>) evt.getNewValue();
        int i = 0;
        for (JPanel panel : panels){
            if (i == 0){this.cardButtonPanel = panel;}
            else if (i == 1){this.bottomPanel = panel;}
            i += 1;
        }

        this.cardButtonPanel.setOpaque(false);
        cardPanel.add(this.cardButtonPanel, BorderLayout.CENTER);
        this.add(this.cardButtonPanel, BorderLayout.SOUTH);

        this.bottomPanel.setOpaque(false);
        bottomPanel.add(this.bottomPanel, BorderLayout.CENTER);
        this.add(this.bottomPanel, BorderLayout.SOUTH);

    }

}