package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class MainMenu implements ActionListener {

    public static void main(String[] args){
        JFrame f = new JFrame("UNO");
        f.setSize(1920,1080);
        f.setResizable(false);
        f.setLayout(null);
        f.setVisible(true);
        f.getContentPane().setBackground(Color.RED);

        /* i think this part is useless?*/
        JPanel panel = new JPanel();
        panel.setBounds(0,0,1920,1080);
        panel.setBackground(Color.gray);


        JButton b1 = new JButton("Play Game");
        b1.setBounds(400,300,700,200);
        b1.setBackground(Color.BLACK);

        b1.setFont(new Font("Helvetica", Font.PLAIN, 40));
        b1.setForeground(Color.WHITE);

        b1.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        /* PUT THE ACTION HERE WHEN THE PLAY BUTTON IS PRESSED */

                        System.out.println("The button has been pressed!");
                    }
                }
        );


        /*Create the header text*/
        JLabel header = new JLabel("SpaceY's UNO Game");
        header.setBounds(525,150,500,100);
        header.setFont(new Font("Helvetica", Font.BOLD, 50));
        header.setForeground(Color.BLACK);

        f.add(b1);
        f.add(header);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}