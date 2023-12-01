package use_case.LeftShift;

import entities.card.NumberCard;

import java.util.ArrayList;

public class LeftShiftInputData {
        public int displayCardFirstIndex;
        public LeftShiftInputData(int displayCardFirstIndex) {
            this.displayCardFirstIndex = displayCardFirstIndex;
        }

        public int getDisplayCardFirstIndex() {return displayCardFirstIndex;}
}
