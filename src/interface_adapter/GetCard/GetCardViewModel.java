package interface_adapter.GetCard;

import interface_adapter.Initialized.InitializedState;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GetCardViewModel extends ViewModel {

    private GetCardState state = new GetCardState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public GetCardViewModel() {super("get card");}

    public void setState(GetCardState state) {this.state = state;}

    // Used by GetCardPresenter
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public GetCardState getState() {return this.state;}

}
