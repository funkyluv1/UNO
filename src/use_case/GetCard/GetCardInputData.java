package use_case.GetCard;

public class GetCardInputData {

    private final String playerName;

    public GetCardInputData(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {return this.playerName;}

}
