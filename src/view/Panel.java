package view;

import javax.swing.*;

public class Panel extends JPanel {
    int id;
    public Panel(int ID){
        id = ID;
    };
    public int getId() {
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
}
