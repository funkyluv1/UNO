package use_case.Start;

import data_access.FileUserDataAccessObject;
import entities.NumberCardsDeck.NumberCardsDeck;
import entities.NumberCardsDeck.NumberCardsDeckFactory;
import entities.card.NumberCard;
import entities.player.AIPlayerFactory;
import entities.player.HumanPlayerFactory;
import org.junit.jupiter.api.Test;
import use_case.Undo.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


class StartInteractorTest {
    @Test
    void startSuccessTest() throws IOException {

        // NOTES: This creates a successPresenter that tests whether the test case is as we expect.
        StartOutputDataBoundary successPresenter = new StartOutputDataBoundary() {
            @Override
            public void prepareInitializeView() {
                // tests if the output data has the correct information

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                // tests if the dao (specifically the initiationDataAccessInterface) methods

            }
        };

        StartInputDataBoundary interactor = new StartInteractor(successPresenter);
        interactor.execute();
    }
}