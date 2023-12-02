package view;

import entities.card.NumberCard;
import interface_adapter.Initialized.InitializedState;
import interface_adapter.Initialized.InitializedViewModel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class InitializedView extends JPanel implements ActionListener, PropertyChangeListener{

    public final String viewName;
    private final InitializedViewModel initializedViewModel;

    JPanel bigPanel;
    JPanel cardButtonPanel;
    JPanel bottomPanel;
    JPanel playerPanel;
    JPanel getCardPanel;
    JPanel funCardPanel;

    JLabel label;

    public InitializedView(InitializedViewModel initializedViewModel) {
        JLabel title = new JLabel("Initialized Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.viewName = "Initialized";
        this.initializedViewModel = initializedViewModel;
        this.initializedViewModel.addPropertyChangeListener(this);

        this.cardButtonPanel = new JPanel();
        this.bottomPanel = new JPanel();
        this.playerPanel = new JPanel();
        this.getCardPanel = new JPanel();
        this.funCardPanel = new JPanel();

        cardButtonPanel.setBackground(Color.BLUE);
        bottomPanel.setBackground(Color.RED);
        playerPanel.setBackground(Color.YELLOW);
        getCardPanel.setBackground(Color.GREEN);
        funCardPanel.setBackground(Color.BLACK);


        setSize(1000, 1000);

        this.setLayout(new BorderLayout());

//        JPanel playerPanel = new JPanel();
  //      playerPanel.setLayout(new GridLayout(1, 4, 10, 10));
   //     playerPanel.setOpaque(false);

        bigPanel = new JPanel();
        bigPanel.setLayout(new BorderLayout());

        label = new JLabel("Testing");
        bigPanel.add(label);
        bigPanel.add(label, BorderLayout.CENTER);

        bigPanel.add(this.getCardPanel, BorderLayout.NORTH);
        bigPanel.add(this.cardButtonPanel, BorderLayout.WEST);
        bigPanel.add(this.funCardPanel, BorderLayout.EAST);
        this.add(bigPanel, BorderLayout.CENTER);

        this.add(this.playerPanel, BorderLayout.NORTH);
        playerPanel.add(label, BorderLayout.CENTER);
        this.add(this.bottomPanel, BorderLayout.SOUTH);

    }
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click " + e.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ArrayList<JPanel> panels = (ArrayList<JPanel>) evt.getNewValue();
        int i = 0;
        for (JPanel panel : panels){
            if (i == 0){playerPanel = panel;}
            else if (i == 1){getCardPanel = panel;}
            else if (i == 2){cardButtonPanel = panel;}
            else if (i == 3){funCardPanel = panel;}
            else if (i == 4){bottomPanel = panel;}
            i += 1;
        }

        // set text changes the jlabel content
        label.setText("Changed");
        // whereas assigning our jlabel object to reference another jlable object does not change nything
        label = new JLabel("not changed");

        bigPanel.setOpaque(false);
        playerPanel.setOpaque(false);
        getCardPanel.setOpaque(false);
        funCardPanel.setOpaque(false);
        bottomPanel.setOpaque(false);
        cardButtonPanel.setOpaque(false);
    }

}