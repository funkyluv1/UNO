package interface_adapter.Initialized;

import java.util.ArrayList;

public class PlayerPanelState {
    int current_player_index;

    ArrayList<String> player;
    public PlayerPanelState(PlayerPanelState copy) {

         current_player_index = copy.current_player_index;
         player = copy.player;

    }
    public PlayerPanelState() {}

    public int getCurrent_player_index(){return current_player_index;}

    public ArrayList<String> getPlayer() {
        return player;
    }
    public void setPlayer(ArrayList<String> player){
        this.player = player;
    }

    public void setCurrent_player_index(int another_index){current_player_index = another_index;}

}
