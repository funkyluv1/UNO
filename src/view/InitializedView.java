package view;

import interface_adapter.Initialized.InitializedState;
import interface_adapter.Initialized.InitializedViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class InitializedView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "Initialized";
    private final InitializedViewModel initializedViewModel;

    JLabel playername;

    public InitializedView(InitializedViewModel initializedViewModel) {
        this.initializedViewModel = initializedViewModel;
        this.initializedViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Initialized Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel usernameInfo = new JLabel("Currently players: ");
        playername = new JLabel();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(usernameInfo);
        this.add(playername);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click " + e.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        InitializedState state = (InitializedState) evt.getNewValue();
        playername.setText(state.get_players().get(0));
    }
}