package view;

import Assets.BackGroundMusic;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;


public class MainMenu {

    public static void main(String[] args){
        JFrame f = new JFrame("UNO");
        f.setSize(1920,1080);
        f.setResizable(false);
        f.setLayout(null);
        f.setVisible(true);
        JPanel panel = new JPanel();
        panel.setBounds(0,0,1920,1080);
        panel.setBackground(Color.gray);
        JButton b1= new JButton("Play Game");
        b1.setBounds(400,300,700,200);
        b1.setBackground(Color.yellow);

        /*Create the header text*/
        JLabel header = new JLabel("SpaceY's UNO Game");
        header.setBounds(550,150,500,100);
        header.setFont(new Font("Serif", Font.PLAIN, 50));
        f.add(b1);
        f.add(header);

        BackGroundMusic bgm = new BackGroundMusic();
        bgm.play("src/Assets/M2U - Body Talk.wav");
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                bgm.stop();
                f.dispose();
            }
        });
    }
}