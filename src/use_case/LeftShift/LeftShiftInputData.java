package use_case.LeftShift;

import entities.card.NumberCard;

import java.util.ArrayList;

public class LeftShiftInputData {
        public int displayCardFirstIndex;
        public boolean flag_for_func;
        public LeftShiftInputData(int displayCardFirstIndex, boolean flag_for_func) {
            this.displayCardFirstIndex = displayCardFirstIndex;
            this.flag_for_func = flag_for_func;
        }

        public int getDisplayCardFirstIndex() {return displayCardFirstIndex;}
        public boolean getFlag_For_Func(){return flag_for_func;}
}
