package data_access;

import entities.Game;
import entities.NumberCardsDeck.NumberCardsDeck;
import entities.NumberCardsDeck.NumberCardsDeckFactory;
import entities.card.*;
import entities.player.*;
import use_case.Confirm.ConfirmDataAccessInterface;
import use_case.GetCard.GetCardDataAccessInterface;
import use_case.SelectCard.SelectCardDataAccessInterface;
import use_case.Undo.UndoDataAccessInterface;
import use_case.NextTurn.NextTurnDataAccessInterface;
import use_case.PostTurn.PostTurnDataAccessInterface;
import use_case.PreTurn.PreTurnDataAccessInterface;
import use_case.RightShift.RightShiftDataAccessInterface;
import use_case.initiation.InitiationDataAccessInterface;
import use_case.initiation.InitiationInputData;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileUserDataAccessObject implements InitiationDataAccessInterface,
        PreTurnDataAccessInterface, PostTurnDataAccessInterface, RightShiftDataAccessInterface,
        NextTurnDataAccessInterface, UndoDataAccessInterface, SelectCardDataAccessInterface, GetCardDataAccessInterface, ConfirmDataAccessInterface {
    private final File csvFile;
    private final AIPlayerFactory aiPlayerFactory;
    private final HumanPlayerFactory humanPlayerFactory;
    private final NumberCardsDeckFactory numberCardsDeckFactory;
    private CardFactory cardFactory;

    private final Map<String, Player> playerInfo = new LinkedHashMap<>();
    private final Map<Integer, NumberCardsDeck> cardsDeck = new HashMap<>();
    private final Map<String, Integer> numberCardDeckHeaders = new LinkedHashMap<>();
    private final Map<String, Integer> playerHeaders = new LinkedHashMap<>();
    private Game game = Game.getInstance();


    public FileUserDataAccessObject(String csvPath,AIPlayerFactory aiPlayerFactory,
                                    HumanPlayerFactory humanPlayerFactory,
                                    NumberCardsDeckFactory numberCardsDeckFactory) throws IOException {
        this.aiPlayerFactory = aiPlayerFactory;
        this.humanPlayerFactory = humanPlayerFactory;
        this.numberCardsDeckFactory = numberCardsDeckFactory;

        csvFile = new File(csvPath);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(csvPath));
        numberCardDeckHeaders.put("numberCardDeckID", 0);
        numberCardDeckHeaders.put("remainingCard", 1);
        playerHeaders.put("playerType", 0);
        playerHeaders.put("username", 1);
        playerHeaders.put("numberCardsInHand", 2);
        playerHeaders.put("functionalCardsInHand", 3);
        playerHeaders.put("displayFirstCardIndex", 4);

        String row;
        if ((row = bufferedReader.readLine()) != null){
            String[] rowList = row.split(";");
            cardsDeck.put(0, numberCardsDeckFactory.create(rowList[numberCardDeckHeaders.get("numberCardDeckID")],
                    Integer.parseInt(rowList[numberCardDeckHeaders.get("remainingCard")])));
            ArrayList<NumberCard> numberCardsArrayList = new ArrayList<NumberCard>();
            ArrayList<FunctionalCard> functionalCards = new ArrayList<FunctionalCard>();
            while ((row = bufferedReader.readLine()) != null){
                String[] rowList1 = row.split(";");
                String username = rowList1[playerHeaders.get("username")];
                for (String i : rowList1[playerHeaders.get("numberCardsInHand")].split(",")){
                    cardFactory = new NumberCardFactory(Integer.parseInt(i.substring(0,1)), Character.toString(i.charAt(1)));
                    NumberCard numberCard = (NumberCard) cardFactory.createCard();
                    numberCardsArrayList.add(numberCard);
                }
                for (String i : rowList1[playerHeaders.get("functionalCardsInHand")].split(",")){
                    Pattern pattern = Pattern.compile("([a-zA-Z]+)(\\d)([a-zA-Z])");
                    if (pattern.matcher(i).matches()) {
                        Matcher matcher = pattern.matcher(i);
                        String type = matcher.group(1);
                        String numericPart = matcher.group(2);
                        String color = matcher.group(3);
                        cardFactory = new FunctionalCardFactory(Integer.parseInt(numericPart), color, type);
                        FunctionalCard functionalCard = (FunctionalCard) cardFactory.createCard();
                        functionalCards.add(functionalCard);
                    }
                }
                int displayFirstCardIndex = Integer.parseInt(rowList1[playerHeaders.get("displayFirstCardIndex")]);

                playerInfo.put(username, humanPlayerFactory.create(username,numberCardsArrayList,functionalCards, displayFirstCardIndex));

            }
        }
    }

    @Override
    public void initiate(NumberCardsDeck numberCardsDeck,
                         InitiationInputData initiationInputData) {
        cardsDeck.put(0, numberCardsDeck);
        for (String playerName : initiationInputData.getPlayerNames()){
            playerInfo.put(playerName, humanPlayerFactory.create(playerName,
                    new ArrayList<NumberCard>(),
                    new ArrayList<FunctionalCard>(),2));
        };

        this.save();
    }

    private void save(){
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            for (NumberCardsDeck numberCardsDeck : cardsDeck.values()){
                String line = String.format("%s;%s",numberCardsDeck.getId(), numberCardsDeck.getRemainingCards());
                writer.write(line);
                writer.newLine();
            }
            for (Player player : playerInfo.values()) {
                String playerType;
                String playerName = player.getPlayerName();
                String numberCards = "";
                ArrayList<NumberCard> numberCardsList = player.getNumberCards();
                for (NumberCard numberCard : numberCardsList){
                    numberCards = numberCards.concat(numberCard.getString()).concat(",");
                }
                //numberCards = numberCards.substring(0, numberCards.length() - 1);
                String funcCards = "";
                ArrayList<FunctionalCard> functionalCards = player.getFuncCards();
                for (FunctionalCard functionalCard : functionalCards){
                    funcCards = funcCards.concat(functionalCard.getString()).concat(",");
                }
                // initialize index of the first displayed cards
                int firstCardIndex = 0;

                playerType = "Human";

                String line = String.format("%s;%s;%s;%s;%s", playerType, playerName, numberCards, funcCards, firstCardIndex);
                writer.write(line);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void savePlayerwithCards(String playerName, ArrayList<NumberCard> numberCards, ArrayList<FunctionalCard>functionalCards, int displayFirstCardIndex){
        playerInfo.get(playerName).setFuncCards(functionalCards);
        playerInfo.get(playerName).setNumCards(numberCards);
        playerInfo.get(playerName).setDisplayFirstCardIndex(displayFirstCardIndex);
        save();
    }
//TODO: probably need to combine these two into one public function of the DAO itself (not by overriding)
    @Override
    public void recordPostTurnChange(ArrayList<FunctionalCard> functionalCards, ArrayList<NumberCard> numberCards, String currentPlayer) {
        playerInfo.get(currentPlayer).setFuncCards(functionalCards);
        playerInfo.get(currentPlayer).setNumCards(numberCards);
        save();
    }

    @Override
    public void recordRoundChange(String currentPlayer, FunctionalCard reward) {
        ArrayList<FunctionalCard> hand = playerInfo.get(currentPlayer).getFuncCards();
        hand.add(reward);
        playerInfo.get(currentPlayer).setFuncCards(hand);
        save();
    }


    @Override
    public ArrayList<NumberCard> getNumberCards(String player) {
        return playerInfo.get(player).getNumberCards();
    }

    @Override
    public void recordPreTurnChange(ArrayList<NumberCard> numberCards, String currentPlayer) {
        playerInfo.get(currentPlayer).setNumCards(numberCards);
        save();
    }

    @Override
    public ArrayList<FunctionalCard> getFunctionalCards(String player) {
        return playerInfo.get(player).getFuncCards();
    }

    @Override
    public Player getPlayer(int playerIndex) {
        int i = 0;
        int currplayerIndex = playerIndex % 4;
        for (String userName : playerInfo.keySet()){
            if (i == currplayerIndex){
                return playerInfo.get(userName);}
            i += 1;
        }
        return null;
    }

    public int getPlayerDisplayFirstCardIndex(String playerName){
        return playerInfo.get(playerName).getDisplayFirstCardIndex();
    }

    @Override
    public void incrementCurrentPlayerFirstCardIndex(String playerName) {
        playerInfo.get(playerName).setDisplayFirstCardIndex(playerInfo.get(playerName).getDisplayFirstCardIndex() + 1);
    }

    @Override
    public void recordSelectCard(Card card) {
        
    }

    @Override
    public void recordUnselectCard(Card card) {

    }

    @Override
    public String get_specific_player_with_index(int player_index) {
        ArrayList<String> usernames = new ArrayList<>(playerInfo.keySet());
        return playerInfo.get(usernames.get(player_index % 4)).playerName;
    }

    @Override
    public void play_Card_and_update_DAO(String playerName, NumberCard numberCard, ArrayList<FunctionalCard> functionalCards) {
        ArrayList<NumberCard> numberCards = playerInfo.get(playerName).getNumberCards();
        ArrayList<FunctionalCard> currPlayerFuncCard = getFunctionalCards(playerName);

        for (NumberCard numberCard1 : numberCards){
            if (numberCard1.getString().equals(numberCard.getString())){
                numberCards.remove(numberCard1);
                break;
            }
        }
        ArrayList<Integer> selectedFuncCards = new ArrayList<>();
        for (int i = 0; i < functionalCards.size(); i++){
            for (int j = 0; j < currPlayerFuncCard.size(); j++){
                if (functionalCards.get(i).getString().equals(currPlayerFuncCard.get(j).getString()) && !selectedFuncCards.contains(j)){
                    selectedFuncCards.add(j);
                }
            }
        }
        Collections.sort(selectedFuncCards, Collections.reverseOrder());
        for (int index : selectedFuncCards) {
            if (index >= 0 && index < currPlayerFuncCard.size()) {
                cardsDeck.remove(index);
            }
        }
        recordPostTurnChange(currPlayerFuncCard, numberCards, playerName);
    }

    @Override
    public void recordGetCard(int index, NumberCard numberCard) {
        String player = get_specific_player_with_index(index);
        ArrayList<NumberCard> hand = playerInfo.get(player).getNumberCards();
        hand.add(numberCard);
        playerInfo.get(player).setNumCards(hand);
        save();
    }

    @Override
    public NumberCardsDeck getNumberCardsDeck() {
        return game.getNumberCardDeck();
    }
}
