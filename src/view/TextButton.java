package view;

import javax.swing.*;
import java.awt.*;

public class TextButton extends JButton {
    public TextButton(String text) {
        super(text);

        Font comicSansFont = new Font("Comic Sans MS", Font.PLAIN, 32);
        setFont(comicSansFont);

        setBackground(Color.RED);
        setForeground(Color.WHITE);
        setBorderPainted(false);

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setForeground(Color.YELLOW);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                setForeground(Color.WHITE);
            }
        });

    }
}