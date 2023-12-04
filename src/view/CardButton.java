package view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class CardButton extends JButton {

    private Color color = Color.gray;
    public CardButton(){
        super();

        setForeground(Color.BLACK);
        setPreferredSize(new Dimension(100, 150));
        setBorderPainted(false);
        setOpaque(false);
        setBackground(Color.GRAY);
        setFont(new Font("Arial", Font.BOLD, 24));

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setForeground(Color.ORANGE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                setForeground(Color.BLACK);
            }
        });
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(Color.white);

        // Set a thicker border stroke
        Stroke oldStroke = g2d.getStroke();
        g2d.setStroke(new BasicStroke(8.0f)); // Adjust the width as needed

        g2d.draw(new RoundRectangle2D.Float(2, 2, getWidth() - 8, getHeight() - 8, 20, 20));

        // Restore the original stroke
        g2d.setStroke(oldStroke);
        g2d.dispose();
    }

    @Override
    public void setText(String text) {
        char c = text.charAt(1);

        switch (c) {
            case 'R':
                color = (Color.RED);
                break;
            case 'G':
                color = (Color.GREEN);
                break;
            case 'B':
                color = (Color.BLUE);
                break;
            case 'Y':
                color = (Color.YELLOW);
                break;
        }

        super.setText(text);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(color);
        g2d.fill(new RoundRectangle2D.Float(0, 0, getWidth()-8, getHeight()-8, 20, 20));
        super.paintComponent(g);
        g2d.dispose();

    }


}
