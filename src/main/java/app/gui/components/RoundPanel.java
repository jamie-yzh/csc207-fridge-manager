package app.gui.components;

import javax.swing.JPanel;
import java.awt.*;

public class RoundPanel extends JPanel {

    private int cornerRadius;
    private Color panelBackground;
    private Color borderColor;

    public RoundPanel(
            int cornerRadius,
            Color panelBackground,
            Color borderColor
    ) {
        this.cornerRadius = cornerRadius;
        this.panelBackground = panelBackground;
        this.borderColor = borderColor;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics.create();

        graphics2D.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        graphics2D.setColor(panelBackground);

        // Draws round rectangle
        graphics2D.fillRoundRect(
                0,
                0,
                getWidth() -1,
                getHeight() -1,
                cornerRadius,
                cornerRadius
        );

        // Draws border
        if (borderColor != null) {
            graphics2D.setColor(borderColor);

            graphics2D.drawRoundRect(
                    0,
                    0,
                    getWidth() -1,
                    getHeight() -1,
                    cornerRadius,
                    cornerRadius
            );
        }

        graphics2D.dispose();

        super.paintComponent(graphics);
    }

    public void setPanelBackground(Color panelBackground) {
        this.panelBackground = panelBackground;
        repaint();
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
        repaint();
    }
}
