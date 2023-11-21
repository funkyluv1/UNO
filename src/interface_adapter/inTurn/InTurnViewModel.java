package interface_adapter.inTurn;

import use_case.SelectCard.SelectCardOutputDataBoundary;
import interface_adapter.ViewModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class InTurnViewModel extends ViewModel {

    private SelectCardPresenter selectCardPresenter;
    private UndoPresenter undoPresenter;

    private InTurnState state = new InTurnState();

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public InTurnViewModel() {
        super("inTurn");
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public InTurnState getState() {
        return state;
    }

}
