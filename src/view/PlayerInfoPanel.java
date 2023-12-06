package view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class PlayerInfoPanel extends JPanel {
    private Color color;
    private Color borderColor = Color.WHITE;
    public PlayerInfoPanel(){super();}

    @Override
    protected void paintBorder(Graphics g){
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(borderColor);

        Stroke oldStroke = g2d.getStroke();
        g2d.setStroke(new BasicStroke(8.0f));

        g2d.draw(new RoundRectangle2D.Float(4, 4, getWidth() - 8, getHeight() - 8, 20, 20));

        g2d.setStroke(oldStroke);
        g2d.dispose();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        // Paint the rounded rectangle background
        g2d.setColor(color);
        g2d.fill(new RoundRectangle2D.Float(0, 0, getWidth() - 8, getHeight() - 8, 20, 20));
        setBackground(new Color(0,0,0,0));

        // Draw the rotated oval
        g2d.setColor(Color.WHITE);
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int width = 250; // Width of the oval
        int height = 130; // Height of the oval

        // Set the center of rotation
        g2d.rotate(Math.toRadians(-30), centerX, centerY);

        // Draw the tilted oval
        g2d.fillOval(centerX - width / 2, centerY - height / 2, width, height);

        g2d.setColor(color);
        g2d.fillOval((centerX - width / 2) + 8, (centerY - height / 2) + 8, 234, 114);

        g2d.dispose();
        super.paintComponent(g);

    }
    public void setColor(Color color){ this.color = color;}
    public void setBorderColor(Color borderColor){this.borderColor = borderColor;}
}
