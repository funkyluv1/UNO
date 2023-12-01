package use_case.NextTurn;

public class NextTurnInputData {
    int player_index;
    public NextTurnInputData(int player_index){
        this.player_index = player_index;
    }
    int getPlayer_index(){
        return player_index;
    }
}
