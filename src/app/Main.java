package app;

import Assets.BackGroundMusic;
import data_access.FileUserDataAccessObject;
import entities.Game;
import entities.NumberCardsDeck.NumberCardsDeckCreator;
import entities.player.AIPlayerFactory;
import entities.player.HumanPlayerFactory;
import interface_adapter.Initialized.BottomPanelViewModel;
import interface_adapter.Initialized.CardButtonPanelViewModel;
import interface_adapter.Initialized.InitializedViewModel;
import interface_adapter.Initiation.InitiationViewModel;
import interface_adapter.MainMeau.MainMeauViewModel;
import interface_adapter.ViewManagerModel;
import use_case.PreTurn.FindPlayableCards;
import use_case.PreTurn.FindPlayableCardsInterface;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;


public class Main {
    static Game game;

    public static void main(String[] args) {
        game = Game.getInstance();

        JFrame application = new JFrame("Initiation Example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.setSize(800, 600);

        // Center the window on the screen
        application.setLocationRelativeTo(null);

        CardLayout cardLayout = new CardLayout();

        BackGroundMusic bgm = new BackGroundMusic();
        bgm.play("src/Assets/M2U - Body Talk.wav");
        application.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                bgm.stop();
                application.dispose();
            }
        });

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        FindPlayableCardsInterface findPlayableCardsInterface = new FindPlayableCards();


        FileUserDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new FileUserDataAccessObject("./users.csv", new AIPlayerFactory(), new HumanPlayerFactory(), new NumberCardsDeckCreator());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        BottomPanelViewModel bottomPanelViewModel = new BottomPanelViewModel();
        BottomPanel bottomPanel = BottomPanelUseCaseFactory.create(viewManagerModel, bottomPanelViewModel);

        CardButtonPanelViewModel cardButtonPanelViewModel = new CardButtonPanelViewModel();
        CardButtonPanel cardButtonPanel = CardButtonPanelUseCaseFactory.create(viewManagerModel, cardButtonPanelViewModel, userDataAccessObject);

        InitiationViewModel initiationViewModel = new InitiationViewModel();
        InitializedViewModel initializedViewModel = new InitializedViewModel(cardButtonPanel, bottomPanel);

        InitiationViewModel initiationViewModel = new InitiationViewModel();
        InitializedViewModel initializedViewModel = new InitializedViewModel(cardButtonPanel, getCardPanel);

        InitiationView initiationView = InitiationUseCaseFactory.create(viewManagerModel, initiationViewModel, cardButtonPanelViewModel,initializedViewModel, userDataAccessObject, findPlayableCardsInterface);
        views.add(initiationView, initiationView.viewName);

        InitializedView initializedView = new InitializedView(initializedViewModel);
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
