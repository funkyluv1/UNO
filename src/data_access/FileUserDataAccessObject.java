package data_access;

import entities.Game;
import entities.NumberCardsDeck.NumberCardsDeck;
import entities.NumberCardsDeck.NumberCardsDeckFactory;
import entities.card.*;
import entities.player.*;
import use_case.PostTurn.PostTurnDataAccessInterface;
import use_case.PreTurn.PreTurnDataAccessInterface;
import use_case.initiation.InitiationDataAccessInterface;
import use_case.initiation.InitiationInputData;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileUserDataAccessObject implements InitiationDataAccessInterface, PreTurnDataAccessInterface, PostTurnDataAccessInterface {
    private final File csvFile;
    private final AIPlayerFactory aiPlayerFactory;
    private final HumanPlayerFactory humanPlayerFactory;
    private final NumberCardsDeckFactory numberCardsDeckFactory;
    private CardFactory cardFactory;

    private final Map<String, Player> playerInfo = new LinkedHashMap<>();
    private final Map<Integer, NumberCardsDeck> cardsDeck = new HashMap<>();
    private final Map<String, Integer> numberCardDeckHeaders = new LinkedHashMap<>();
    private final Map<String, Integer> playerHeaders = new LinkedHashMap<>();
    private Game game;


    public FileUserDataAccessObject(String csvPath, AIPlayerFactory aiPlayerFactory,
                                    HumanPlayerFactory humanPlayerFactory,
                                    NumberCardsDeckFactory numberCardsDeckFactory) throws IOException {
        this.aiPlayerFactory = aiPlayerFactory;
        this.humanPlayerFactory = humanPlayerFactory;
        this.numberCardsDeckFactory = numberCardsDeckFactory;
        this.cardFactory = cardFactory;

        csvFile = new File(csvPath);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(csvPath));
        numberCardDeckHeaders.put("numberCardDeckID", 0);
        numberCardDeckHeaders.put("remainingCard", 1);
        playerHeaders.put("playerType", 0);
        playerHeaders.put("username", 1);
        playerHeaders.put("numberCardsInHand", 2);
        playerHeaders.put("functionalCardsInHand", 3);

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
                    Matcher matcher = pattern.matcher(i);
                    String type = matcher.group(1);
                    String numericPart = matcher.group(2);
                    String color = matcher.group(3);
                    cardFactory = new FunctionalCardFactory(Integer.parseInt(numericPart), color, type);
                    FunctionalCard functionalCard = (FunctionalCard) cardFactory.createCard();
                    functionalCards.add(functionalCard);
                }
                if (rowList1[playerHeaders.get("playerType")].equals("AI")){

                    playerInfo.put(username, aiPlayerFactory.create(username, numberCardsArrayList, functionalCards));
                } else {
                    playerInfo.put(username, humanPlayerFactory.create(username,numberCardsArrayList,functionalCards));
                }
            }
        }
    }

    @Override
    public void initiate(NumberCardsDeck numberCardsDeck, InitiationInputData initiationInputData) {
        cardsDeck.put(0, numberCardsDeck);
        for (String playerName : initiationInputData.getPlayerNames()){
            playerInfo.put(playerName, humanPlayerFactory.create(playerName, new ArrayList<NumberCard>(), new ArrayList<FunctionalCard>()));
        };
        int i = 0;
        while (i < initiationInputData.getBotNumber()){
//            playerFactory = new AIPlayerFactory();
            String username = aiPlayerFactory.create("", new ArrayList<NumberCard>(), new ArrayList<FunctionalCard>()).getPlayerName();
            playerInfo.put(username, aiPlayerFactory.create(username, new ArrayList<NumberCard>(), new ArrayList<FunctionalCard>()));
        }
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
                //TODO: need getString for FunctionalCards

                if (player instanceof AIPlayer){
                    playerType = "AI";
                }else{playerType = "Human";}

                String line = String.format("%s;%s;%s;%s", playerType, playerName, numberCards, funcCards);
                writer.write(line);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void savePlayerwithCards(String playerName, ArrayList<NumberCard> numberCards, ArrayList<FunctionalCard>functionalCards){
        playerInfo.get(playerName).setFuncCards(functionalCards);
        playerInfo.get(playerName).setNumCards(numberCards);
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
        // TODO: implement me
        return null;
    }

    @Override
    public Player getPlayer(int playerIndex) {
        // TODO: implement me
        return null;
    }
}
