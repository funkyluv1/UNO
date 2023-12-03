package view;

import entities.card.Card;
import interface_adapter.Initialized.GetCardPanelState;
import interface_adapter.Initialized.GetCardPanelViewModel;
import interface_adapter.Undo.UndoController;
import entities.Game;
import use_case.Undo.UndoInputData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GetCardPanel extends JPanel implements PropertyChangeListener {
    Panel panel = new Panel(2);
    Game game;
    JButton getCardButton;
    JButton undoButton;
    JLabel topCard;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private final GetCardPanelViewModel getCardViewModel;
    private final UndoController undoController;
    private int id = 2;

    public GetCardPanel(GetCardPanelViewModel getCardPanelViewModel, UndoController undoController) {
        this.getCardViewModel = getCardPanelViewModel;
        this.getCardViewModel.addPropertyChangeListener(this);
        this.undoController = undoController;

        undoButton = new JButton("Undo");
        undoButton.setPreferredSize(new Dimension(100, 40));
        undoButton.setBackground(Color.BLACK);
        undoButton.setForeground(Color.WHITE);
        undoButton.setOpaque(true);
        panel.add(undoButton);

        undoButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if(evt.getSource().equals(undoButton)) {
                            UndoInputData inputData = new UndoInputData((Card) game.getCurrSelectedNumberCard());
                            undoController.execute(inputData);
                            GetCardPanelState state = new GetCardPanelState();
                            state.setUndoEnabled(false);
                            getCardPanelViewModel.setState(state);
                        }
                    }
                }
        );

        ImageIcon icon = new ImageIcon("");
        topCard = new JLabel(icon);
        topCard.setPreferredSize(new Dimension(100, 150));
        panel.add(topCard);

        getCardButton = new JButton();
        getCardButton.setPreferredSize(new Dimension(100, 150));
        getCardButton.setText("Get Card");
        getCardButton.setBorder(BorderFactory.createEmptyBorder());
        getCardButton.setBackground(Color.BLACK);
        getCardButton.setForeground(Color.WHITE);
        getCardButton.setFont(new Font("Arial", Font.BOLD, 15));
        getCardButton.setOpaque(true);
        panel.add(getCardButton);

        getCardButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(e.getSource() == getCardButton) {
                            //TODO: Need getCard use case
//                            getCardController.execute();
                        }
                    }
                }
        );
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        getCardButton.setEnabled(this.getCardViewModel.getState().isGetCardEnabled());
        undoButton.setEnabled(this.getCardViewModel.getState().isUndoEnabled());
        String name = getCardViewModel.getState().getTopCard().getString();

        if (name.charAt(1) == 'B'){topCard.setBackground(Color.BLUE);}
        else if (name.charAt(1) == 'R'){topCard.setBackground(Color.RED);}
        else if (name.charAt(1) == 'G'){topCard.setBackground(Color.GREEN);}
        topCard.setText(name);

        this.firePropertyChange();
    }
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    public void firePropertyChange(){support.firePropertyChange("panel", null, this.panel);}

    public int getID(){return this.id;}

}