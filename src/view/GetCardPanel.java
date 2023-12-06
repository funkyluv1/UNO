package view;

import entities.card.Card;
import entities.card.NumberCard;
import interface_adapter.GetCard.GetCardController;
import interface_adapter.Initialized.*;
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
import java.util.ArrayList;

public class GetCardPanel extends JPanel implements PropertyChangeListener {
    Panel panel = new Panel(2);
    Game game = Game.getInstance();
    JButton getCardButton;
    JButton undoButton;
    CardButton topCard;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private final GetCardPanelViewModel getCardViewModel;
    private final UndoController undoController;
    private final GetCardController getCardController;
    private int id = 2;
    private BottomPanelViewModel bottomPanelViewModel;
    private CardButtonPanelViewModel cardButtonPanelViewModel;

    public GetCardPanel(GetCardPanelViewModel getCardPanelViewModel, UndoController undoController, GetCardController getCardController, BottomPanelViewModel bottomPanelViewModel, CardButtonPanelViewModel cardButtonPanelViewModel) {
        this.getCardViewModel = getCardPanelViewModel;
        this.getCardViewModel.addPropertyChangeListener(this);
        this.undoController = undoController;
        this.bottomPanelViewModel = bottomPanelViewModel;
        this.getCardController = getCardController;
        this.cardButtonPanelViewModel = cardButtonPanelViewModel;

        undoButton = new TextButton("Undo");
//        undoButton.setPreferredSize(new Dimension(100, 40));
//        undoButton.setBackground(Color.BLACK);
//        undoButton.setForeground(Color.WHITE);
//        undoButton.setOpaque(true);
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
                            getCardPanelViewModel.firePropertyChanged();
                        }
                    }
                }
        );

//        ImageIcon icon = new ImageIcon("");
//        topCard = new JLabel(icon);
//        topCard.setPreferredSize(new Dimension(100, 150));
        topCard = new CardButton();
        topCard.setEnabled(false);
        panel.add(topCard);

//        getCardButton = new JButton();
//        getCardButton.setPreferredSize(new Dimension(100, 150));
        getCardButton = new CardButton();
        getCardButton.setText("<html> Get<br>Card</html>");
//        getCardButton.setBorder(BorderFactory.createEmptyBorder());
//        getCardButton.setBackground(Color.BLACK);
//        getCardButton.setForeground(Color.WHITE);
        getCardButton.setFont(new Font("Arial", Font.BOLD, 22));
        getCardButton.setOpaque(false);
        panel.add(getCardButton);

        getCardButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(e.getSource() == getCardButton) {

                            getCardController.execute(game.getCurrentPlayerIndex());
                            getCardButton.setEnabled(false);

                            BottomPanelState bottomPanelState = bottomPanelViewModel.getState();
                            bottomPanelState.setNextButtonEnabled(true);
                            bottomPanelViewModel.setState(bottomPanelState);
                            bottomPanelViewModel.firePropertyChanged();

                            CardButtonPanelState cardButtonPanelState = cardButtonPanelViewModel.getState();
                            ArrayList<NumberCard> cards = cardButtonPanelState.getPlayerNumCards();
                            cards.add(getCardPanelViewModel.getState().getNumberCard());
                            cardButtonPanelState.setPlayerNumCards(cards);
                            cardButtonPanelViewModel.setState(cardButtonPanelState);
                            cardButtonPanelViewModel.firePropertyChanged();
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