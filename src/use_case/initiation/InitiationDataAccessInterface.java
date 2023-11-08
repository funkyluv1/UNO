package use_case.initiation;

import entities.NumberCardsDeck.NumberCardsDeck;

public interface InitiationDataAccessInterface {
    void initiate(NumberCardsDeck numberCardsDeck, InitiationInputData initiationInputData);
}
