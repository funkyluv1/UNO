package use_case.initiation;

import entities.NumberCardsDeck.NumberCardsDeck;

public interface InitiationDataAccessInterface {
    void create(NumberCardsDeck numberCardsDeck, InitiationInputData initiationInputData, PlayerFactory playerFactory);
}
