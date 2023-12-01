package use_case.GetCard;

public class GetCardInputData {

    String playerName;
    int botNumber;

    public GetCardInputData(String playerName) {
        this.playerName = playerName;
    }

    public GetCardInputData(int botNumber) {
        this.botNumber = botNumber;
    }

    public String getPlayerName() {return this.playerName;}

    public int getBotNumber() {return this.botNumber;}

}
