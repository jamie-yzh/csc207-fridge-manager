package app.gui.components;

import javax.swing.JComponent;
import java.awt.*;

public class ProgressBar extends JComponent {

    private int value;
    private int max;

    private Color trackColor;
    private Color progressColor;

    private int cornerRadius;

    public ProgressBar(
            int value,
            int max,
            Color progressColor
    ) {
        this.value = value;
        this.max = max;
        this.progressColor = progressColor;

        this.trackColor = new Color(153, 153, 153);
        this.cornerRadius = 14;

        setPreferredSize(new Dimension(400, 14));
        setMinimumSize(new Dimension(100, 14));
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics.create();

        graphics2D.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        int width = getWidth();
        int height = getHeight();

        graphics2D.setColor(trackColor);

        // Renders bar
        graphics.fillRoundRect(
                0,
                0,
                width,
                height,
                cornerRadius,
                cornerRadius
        );

        double percentage =
                max == 0 ? 0.0 : (double) value / max;

        percentage = Math.max(
                0.0,
                Math.min(1.0, percentage)
        );

        int progressWidth =
                (int) Math.round(
                        width * percentage
                );

        graphics2D.setColor(progressColor);

        // Renders progress
        graphics2D.fillRoundRect(
                0,
                0,
                progressWidth,
                height,
                cornerRadius,
                cornerRadius
        );

        graphics2D.dispose();
    }

    //PLACEHOLDERS FOR FUNCTIONALITY
    public void setValue(int value) {
        this.value = value;
        repaint();
    }

    public int getValue() {
        return value;
    }

    public void setMax(int maximum) {
        this.max = maximum;
        repaint();
    }

    public  int getMax() {
        return max;
    }
}
