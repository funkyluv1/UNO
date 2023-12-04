package view;

import interface_adapter.Initialized.PlayerPanelViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class PlayerPanel extends JPanel implements PropertyChangeListener {

    PlayerPanelViewModel playerPanelViewModel;

    int Current_player_index;

    ArrayList<String> players;

    ArrayList<PlayerInfoPanel> playernames = new ArrayList<PlayerInfoPanel>();

    Panel PlayerPanel = new Panel(1);
    private int ID = 1;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public PlayerPanel(PlayerPanelViewModel playerPanelViewModel){
        this.playerPanelViewModel = playerPanelViewModel;
        this.playerPanelViewModel.addPropertyChangeListener(this);

    }

    public void propertyChange(PropertyChangeEvent evt){
        ArrayList<String> players = playerPanelViewModel.getState().getPlayer();
        this.setLayout(new FlowLayout());
//        ArrayList<Color> colorList = new ArrayList<>();
//        colorList.add(new Color(173, 216, 230));
//        colorList.add(new Color(255, 255, 210));
//        colorList.add(new Color(144, 238, 144));
//        colorList.add(new Color(255, 224, 255));

        for (int i = 0; i <= 3; i++) {
            PlayerInfoPanel playerInfo = new PlayerInfoPanel();

            switch (i) {
                case 0:
                    playerInfo.setColor(Color.RED);
                    break;
                case 1:
                    playerInfo.setColor(Color.GREEN);
                    break;
                case 2:
                    playerInfo.setColor(Color.BLUE);
                    break;
                case 3:
                    playerInfo.setColor(Color.YELLOW);
                    break;
            }

            playerInfo.setBorder(BorderFactory.createLineBorder(Color.WHITE, 10));
            Dimension preferredSize = playerInfo.getPreferredSize();
            preferredSize.height = 120;
            preferredSize.width = 280;
            playerInfo.setPreferredSize(preferredSize);
            JLabel usernameLabel = new JLabel(players.get(i));
            usernameLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 26));
            playerInfo.add(usernameLabel);
            PlayerPanel.add(playerInfo);
//            playerInfo.setBackground(Color.WHITE);
            playernames.add(playerInfo);
        }

        int current = playerPanelViewModel.getState().getCurrent_player_index();  //get current player,用上面的方法给他set border
        for (int i = 0; i <= 3; i++){
            if (i == current){
                playernames.get(i).setBorderColor(Color.black);
            }
            if (i != current){
                playernames.get(i).setBorderColor(Color.WHITE);
            }
        }
        this.firePropertyChange();

    }
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    public void firePropertyChange(){support.firePropertyChange("PlayerPanel", null, this.PlayerPanel);}
    public int getID(){return this.ID;}

}
