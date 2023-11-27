package view;

import entities.NumberCardsDeck.NumberCardsDeck;
import entities.NumberCardsDeck.NumberCardsDeckCreator;
import interface_adapter.DrawCards.DrawCardsController;
import interface_adapter.DrawCards.DrawCardsViewModel;
import interface_adapter.Initialized.InitializedState;
import interface_adapter.Initialized.InitializedViewModel;
import interface_adapter.SelectCard.SelectCardController;
import interface_adapter.SelectCard.SelectCardState;
import interface_adapter.SelectCard.SelectCardViewModel;
import interface_adapter.Undo.UndoController;
import use_case.SelectCard.SelectCardInputData;
import use_case.Undo.UndoInputData;

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
public class InitializedView extends JPanel implements ActionListener, PropertyChangeListener{

    public final String viewName;
    private final InitializedViewModel initializedViewModel;
    private final UndoController undoController;
    private final DrawCardsController drawCardsController;
    private final DrawCardsViewModel drawCardsViewModel;
    private final SelectCardController selectCardController;
    private final SelectCardViewModel selectCardViewModel;


    public InitializedView(InitializedViewModel initializedViewModel, DrawCardsViewModel drawCardsViewModel, UndoController undoController, DrawCardsController drawCardsController, SelectCardController selectCardController, SelectCardViewModel selectCardViewModel) {
        JLabel title = new JLabel("Initialized Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.viewName = "InitializedView";
        this.initializedViewModel = initializedViewModel;
        this.drawCardsController = drawCardsController;
        this.undoController = undoController;
        this.drawCardsViewModel = drawCardsViewModel;
        this.selectCardController = selectCardController;
        this.selectCardViewModel = selectCardViewModel;
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
            JLabel usernameLabel = new JLabel("PLAYER " + i + " USERNAME");//这个需要根据viewmodel来
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


        JButton[] cardButtons = new JButton[3];

        for (int i = 0; i < 3; i++) {
            cardButtons[i] = new JButton("Card " + i);
            cardButtons[i].setPreferredSize(new Dimension(130, 200));
            cardButtons[i].setBorder(BorderFactory.createEmptyBorder());
            cardButtons[i].setBackground(Color.YELLOW);
            cardButtons[i].setOpaque(true);
            playpanel.add(cardButtons[i]);
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

        //TODO: figure out where to put the numberCardDeck and player
        drawCardsController.execute(initializedViewModel.getState().get_players(), numberCardsDeck,5);

        undoButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals("Undo")) {
                            UndoInputData inputData = new UndoInputData(selectCardViewModel.getSelectCardState().getSelectedCard());
                            SelectCardState state = new SelectCardState();
                            state.setSelectedCard(null);
                            selectCardViewModel.setSelectCardState(state);
                            undoController.execute(inputData);
                            undoButton.setEnabled(false);
                        }
                    }
                }
        );

        cardButtons[0].addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                         if (e.getSource().equals("Card 0")) {
                             undoButton.setEnabled(true);
                             cardButtons[0].setBackground(new Color(255, 255, 210));
                             SelectCardInputData inputData = new SelectCardInputData(player, drawCardsViewModel.getDrawCardsState().getNumberCard0())
                             selectCardController.execute(inputData);
                         }
                    }
                }
        );

        cardButtons[1].addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals("Card 1")) {
                            undoButton.setEnabled(true);
                            cardButtons[1].setBackground(new Color(255, 255, 210));
                            SelectCardInputData inputData = new SelectCardInputData(player, drawCardsViewModel.getDrawCardsState().getNumberCard0());
                            selectCardController.execute(inputData);
                        }
                    }
                }
        );

        cardButtons[2].addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals("Card 2")) {
                            undoButton.setEnabled(true);
                            cardButtons[2].setBackground(new Color(255, 255, 210));
                            SelectCardInputData inputData = new SelectCardInputData(player, drawCardsViewModel.getDrawCardsState().getNumberCard0())
                            selectCardController.execute(inputData);
                        }
                    }
                }
        );


    }
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click " + e.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        InitializedState state = (InitializedState) evt.getNewValue();
        // we don't know how many players there are, so we can't create player1, player2 ...
        // this is the best we can do, display all players in one string
        String output = "";
        ArrayList<String> players = state.get_players();
        for (int i = 0; i < players.size(); i++)
            output += (players.get(i) + "\n");
//        playername.setText(output);
    }

}