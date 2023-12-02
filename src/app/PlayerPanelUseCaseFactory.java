package app;

import interface_adapter.Initialized.PlayerPanelViewModel;
import interface_adapter.ViewManagerModel;
import view.PlayerPanel;

import javax.swing.*;

public class PlayerPanelUseCaseFactory {

    private PlayerPanelUseCaseFactory () {}

    public static PlayerPanel create(PlayerPanelViewModel playerPanelViewModel) {
        try {
            return new PlayerPanel(playerPanelViewModel);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Unable to create player panel.");
        }

        return null;
    }

}
