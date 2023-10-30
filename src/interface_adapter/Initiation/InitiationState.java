package interface_adapter.Initiation;

public class InitiationState {
    private ArrayList<Player> playerlist = null;
    private final int playerLimit = 0;

    private boolean isPlaying;
    public InitiationState() {}

    public void setPlayerlist(ArrayList<Player> playerlist) {
        this.playerlist = playerlist;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    public ArrayList<Player> getPlayerlist() {
        return playerlist;
    }

    public int getPlayerLimit() {
        return playerLimit;
    }
}
