package data_access;

import entities.NumberCardsDeck.NumberCardsDeck;
import entities.NumberCardsDeck.NumberCardsDeckFactory;
import entities.card.Card;
import entities.player.Player;
import entities.player.PlayerFactory;
import use_case.initiation.InitiationDataAccessInterface;
import use_case.initiation.InitiationInputData;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class FileUserDataAccessObject implements InitiationDataAccessInterface {
    private final File csvFile;
    private PlayerFactory playerFactory;
    private NumberCardsDeckFactory numberCardsDeckFactory;
    private final Map<String, Player> playerInfo = new LinkedHashMap<>();


    private final Map<Integer, NumberCardsDeck> cardsDeck = new HashMap<>();


    public FileUserDataAccessObject(String csvPath, PlayerFactory playerFactory, NumberCardsDeckFactory numberCardsDeckFactory) throws IOException {
        this.playerFactory = playerFactory;
        this.numberCardsDeckFactory = numberCardsDeckFactory;
        csvFile = new File(csvPath);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(csvPath));

        String row;
        if ((row = bufferedReader.readLine()) != null){
            String[] rowList = bufferedReader.readLine().split(",");
            cardsDeck.put(0, numberCardsDeckFactory.create(rowList[0], Integer.parseInt(rowList[1])));
            while ((row = bufferedReader.readLine()) != null){
                rowList = row.split(",");
                ArrayList<Card> cardsArrayList = new ArrayList<Card>();
                //TODO: need CardFactory; transform String information to Card and add it to the List;
                playerInfo.put(rowList[1], playerFactory.create(Integer.parseInt(rowList[0]), rowList[2], cardsArrayList));
            }
        }

    }

    @Override
    public void create(NumberCardsDeck numberCardsDeck, InitiationInputData initiationInputData) {
        cardsDeck.put(0, numberCardsDeck);
        for (String playerName : initiationInputData.getPlayerNames()){
            //TODO: PlayerID necessity? Need Initiation for Player's ID; Player Instatiation needs an ArrayList of FuncCards;
        };
    }

    private void save(){
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            for (NumberCardsDeck numberCardsDeck : cardsDeck.values()){
                String line = String.format("%s,%s",numberCardsDeck.getId(), numberCardsDeck.getRemainingCards());
                writer.write(line);
                writer.newLine();
            }
            for (Player player : playerInfo.values()) {
                String line = String.format("%s,%s,%s",
                        player.getUserID(), player.playerName, player.getNumberCards());
                //TODO: cannot write player.getNumberCards();// need a new method that return String format of the Cards information
                writer.write(line);
                writer.newLine();
            }
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
