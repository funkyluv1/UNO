package app;

import interface_adapter.Initiation.InitiationViewModel;
import interface_adapter.ViewManagerModel;
import view.ViewManager;
import view.InitiationView;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame application = new JFrame("Login Example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        InitiationViewModel initiationViewModel = new InitiationViewModel();

        InitiationView signupView = InitiationUseCaseFactory.create(viewManagerModel, initiationViewModel);
        views.add(signupView, signupView.viewName);
    }
}
