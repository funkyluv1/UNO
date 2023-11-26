package app;

import data_access.FileUserDataAccessObject;
import entities.Game;
import entities.NumberCardsDeck.NumberCardsDeckCreator;
import entities.card.*;
import entities.player.AIPlayerFactory;
import entities.player.HumanPlayerFactory;
import entities.player.Player;
import interface_adapter.Initialized.InitializedViewModel;
import interface_adapter.Initiation.InitiationViewModel;
import interface_adapter.ViewManagerModel;
import use_case.PreTurn.FindPlayableCards;
import use_case.PreTurn.FindPlayableCardsInterface;
import view.InitializedView;
import view.ViewManager;
import view.InitiationView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;


public class Main {
    static Game game;
    static Player[] players;

    public static ArrayList<NumberCard> createNumberHand(int size) {
        ArrayList<NumberCard> hand = new ArrayList<>();
        for (int i=0; i<size; i++) {
            String[] randColor = {"red", "blue", "green", "yellow"};
            int randColorIndex = (int) Math.floor(Math.random()*4);
            int randValue = (int) Math.floor(Math.random()*9);
            NumberCard card = new NumberCard(randValue, randColor[randColorIndex]);
            hand.add(card);
        }
        return hand;
    }

    public static void main(String[] args) {
        game = Game.getInstance();

        JFrame application = new JFrame("Initiation Example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        InitiationViewModel initiationViewModel = new InitiationViewModel();
        InitializedViewModel initializedViewModel = new InitializedViewModel();

        FindPlayableCardsInterface findPlayableCardsInterface = new FindPlayableCards();

        FileUserDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new FileUserDataAccessObject("./users.csv", new AIPlayerFactory(), new HumanPlayerFactory(), new NumberCardsDeckCreator());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        InitiationView initiationView = InitiationUseCaseFactory.create(viewManagerModel, initiationViewModel, initializedViewModel,userDataAccessObject, findPlayableCardsInterface);
        views.add(initiationView, initiationView.viewName);

        InitializedView initializedView = new InitializedView(initializedViewModel);
        views.add(initializedView, initializedView.viewName);

        application.pack();
        application.setVisible(true);


        players = new Player[4];
        for (int i=0; i<players.length; i++) {
            players[i] = new Player(); // TODO: complete this
            }
    }
}
