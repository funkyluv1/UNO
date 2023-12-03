package app;

import Assets.BackGroundMusic;
import data_access.FileUserDataAccessObject;
import entities.Game;
import entities.NumberCardsDeck.NumberCardsDeckCreator;
import entities.player.AIPlayerFactory;
import entities.player.HumanPlayerFactory;
import interface_adapter.Initialized.*;
import interface_adapter.Initiation.InitiationViewModel;
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
        application.setPreferredSize(new Dimension(1200, 750));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (screenSize.width - application.getWidth()) / 6;
        int centerY = (screenSize.height - application.getHeight()) / 6;
        application.setLocation(centerX, centerY);
        application.setResizable(false);

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

        // viewmodels
        CardButtonPanelViewModel cardButtonPanelViewModel = new CardButtonPanelViewModel();
        FunCardButtonPanelViewModel funCardButtonPanelViewModel = new FunCardButtonPanelViewModel();
        BottomPanelViewModel bottomPanelViewModel = new BottomPanelViewModel();
        PlayerPanelViewModel playerPanelViewModel = new PlayerPanelViewModel();
        GetCardPanelViewModel getCardPanelViewModel = new GetCardPanelViewModel();

        // panels (views)
        BottomPanel bottomPanel = BottomPanelUseCaseFactory.create(viewManagerModel, bottomPanelViewModel, cardButtonPanelViewModel, funCardButtonPanelViewModel, userDataAccessObject);
        PlayerPanel playerPanel = PlayerPanelUseCaseFactory.create(viewManagerModel, playerPanelViewModel);
        GetCardPanel getCardPanel = GetCardPanelUseCaseFactory.create(viewManagerModel, getCardPanelViewModel, cardButtonPanelViewModel, userDataAccessObject);
        FunCardButtonPanel funCardButtonPanel = FunCardButtonPanelUseCaseFactory.create(viewManagerModel, cardButtonPanelViewModel, funCardButtonPanelViewModel, userDataAccessObject);
        CardButtonPanel cardButtonPanel = CardButtonPanelUseCaseFactory.create(viewManagerModel, funCardButtonPanelViewModel, cardButtonPanelViewModel, getCardPanelViewModel,bottomPanelViewModel,userDataAccessObject);



        InitiationViewModel initiationViewModel = new InitiationViewModel();
        InitializedViewModel initializedViewModel = new InitializedViewModel(cardButtonPanel, bottomPanel, playerPanel, getCardPanel, funCardButtonPanel);

        InitiationView initiationView = InitiationUseCaseFactory.create(viewManagerModel, initiationViewModel, cardButtonPanelViewModel,initializedViewModel, userDataAccessObject,  funCardButtonPanelViewModel,
                findPlayableCardsInterface, getCardPanelViewModel, bottomPanelViewModel, playerPanelViewModel);
        views.add(initiationView, initiationView.viewName);

        InitializedView initializedView = new InitializedView(initializedViewModel);
        views.add(initializedView, initializedView.viewName);

        MainMenuView mainMenuView = MainMenuUseCaseFactory.create(viewManagerModel, initiationViewModel);
        views.add(mainMenuView, mainMenuView.viewName);

        viewManagerModel.setActiveView(mainMenuView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
