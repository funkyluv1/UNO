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

    ArrayList<JPanel> playernames = new ArrayList<JPanel>();

    JPanel PlayerPanel = new JPanel();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public PlayerPanel(PlayerPanelViewModel playerPanelViewModel){
        this.playerPanelViewModel = playerPanelViewModel;
        this.playerPanelViewModel.addPropertyChangeListener(this);

    }

    public void propertyChange(PropertyChangeEvent evt){
        ArrayList<String> players = playerPanelViewModel.getState().getPlayer();
        PlayerPanel.setLayout(new BorderLayout());
//        ArrayList<Color> colorList = new ArrayList<>();
//        colorList.add(new Color(173, 216, 230));
//        colorList.add(new Color(255, 255, 210));
//        colorList.add(new Color(144, 238, 144));
//        colorList.add(new Color(255, 224, 255));
        for (int i = 0; i <= 3; i++) {
            JPanel playerInfo = new JPanel();
            playerInfo.setBorder(BorderFactory.createLineBorder(Color.WHITE, 15));
            Dimension preferredSize = playerInfo.getPreferredSize();
            preferredSize.height = 120;
            playerInfo.setPreferredSize(preferredSize);
            JLabel usernameLabel = new JLabel(players.get(i));
            playerInfo.add(usernameLabel);
            PlayerPanel.add(playerInfo);
            playerInfo.setBackground(Color.WHITE);
            playernames.add(playerInfo);
        }

        int current = playerPanelViewModel.getState().getCurrent_player_index();  //get current player,用上面的方法给他set border
        for (int i = 0; i < 3; i++){
            if (i == current){
                playernames.get(i).setBorder(BorderFactory.createLineBorder(Color.BLACK, 15));
            }
            if (i != current){
                playernames.get(i).setBorder(BorderFactory.createLineBorder(Color.WHITE, 15));
            }
        }
        this.firePropertyChange();

    }
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    public void firePropertyChange(){support.firePropertyChange("PlayerPanel", null, this.PlayerPanel);}
}
