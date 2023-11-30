package use_case.NextTurn;

public class NextTurnInputData {
    int player_index;
    public NextTurnInputData(int player_index){
        if (player_index == 3) {
            this.player_index = 0;
        }
        else{
            this.player_index = player_index + 1;
        }
    }
    int getPlayer_index(){
        return player_index;
    }
}
