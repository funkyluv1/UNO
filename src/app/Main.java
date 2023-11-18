package app;

import data_access.FileUserDataAccessObject;
import entities.Game;
import entities.NumberCardsDeck.NumberCardsDeckCreator;
import entities.player.AIPlayerFactory;
import entities.player.HumanPlayerFactory;
import interface_adapter.Initialized.InitializedViewModel;
import interface_adapter.Initiation.InitiationViewModel;
import interface_adapter.ViewManagerModel;
import view.InitializedView;
import view.ViewManager;
import view.InitiationView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;


public class Main {
    static Game game;

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

        FileUserDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new FileUserDataAccessObject("./users.csv", new AIPlayerFactory(), new HumanPlayerFactory(), new NumberCardsDeckCreator());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        InitiationView initiationView = InitiationUseCaseFactory.create(viewManagerModel, initiationViewModel, initializedViewModel,userDataAccessObject);
        views.add(initiationView, initiationView.viewName);

        InitializedView initializedView = new InitializedView();
        views.add(initializedView, initializedView.viewName);

        application.pack();
        application.setVisible(true);

//        players = new ArrayList<Player>();
//        // TODO: add Player objects to players, then shuffle players
//        // TODO: create game object, add a while loop that updates the game object in
//        //  each round (pre-turn, in-turn. etc.)

//        int round = 1;
//        while (true) { //TODO: replace true with some winning criteria
//            Player currPlayer = players.get(round);
//            round = round % players.size();
//
//            if (game.getSkipped()) {
//                //...
//            }
//            if (game.getDrawCard() > 0) {
//                //...
//            }
//
//            currPlayer.preTurn(game);
//            currPlayer.inTurn(game);
//            currPlayer.postTurn(game);
//
//        }
    }
}
