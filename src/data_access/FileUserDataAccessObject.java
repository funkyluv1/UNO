package data_access;

import entities.NumberCardsDeck.NumberCardsDeck;
import entities.NumberCardsDeck.NumberCardsDeckFactory;
import entities.card.Card;
import entities.card.CardBuilder;
import entities.card.FunctionalCard;
import entities.card.NumberCard;
import entities.player.*;
import use_case.PostTurn.PostTurnDataAccessInterface;
import use_case.initiation.InitiationDataAccessInterface;
import use_case.initiation.InitiationInputData;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class FileUserDataAccessObject implements InitiationDataAccessInterface, PostTurnDataAccessInterface {
    private final File csvFile;
    private AIPlayerFactory aiPlayerFactory;
    private HumanPlayerFactory humanPlayerFactory;
    private NumberCardsDeckFactory numberCardsDeckFactory;
//    private CardBuilder cardBuilder;
    private final Map<String, Player> playerInfo = new LinkedHashMap<>();
    private final Map<Integer, NumberCardsDeck> cardsDeck = new HashMap<>();
    private final Map<String, Integer> numberCardDeckHeaders = new LinkedHashMap<>();
    private final Map<String, Integer> playerHeaders = new LinkedHashMap<>();


    public FileUserDataAccessObject(String csvPath, AIPlayerFactory aiPlayerFactory, HumanPlayerFactory humanPlayerFactory, NumberCardsDeckFactory numberCardsDeckFactory, CardBuilder cardBuilder) throws IOException {
        this.aiPlayerFactory = aiPlayerFactory;
        this.humanPlayerFactory = humanPlayerFactory;
        this.numberCardsDeckFactory = numberCardsDeckFactory;
//        this.cardBuilder = cardBuilder;
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
                    NumberCard numberCard = null;
                    //TODO: Need NumberCardFactory;
                    numberCardsArrayList.add(numberCard);
                }
                for (String i : rowList1[playerHeaders.get("functionalCardsInHand")].split(",")){
                    FunctionalCard functionalCard = null;
                    //TODO: Need FunctionalCardFactory;
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
                numberCards = numberCards.substring(0, numberCards.length() - 1);
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

    //TODO: implement this method
    @Override
    public void recordPostTurnChange() {
        save();
    }
}
