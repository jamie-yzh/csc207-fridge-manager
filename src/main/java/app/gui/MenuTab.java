package app.gui;

import app.gui.MenuTabPanels.EatenToday;
import app.gui.MenuTabPanels.FoodLogging;
import app.gui.MenuTabPanels.NutritionProgress;
import app.gui.MenuTabPanels.Profile;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.*;

public class MenuTab extends JPanel {

    private static final int OUTER_PADDING = 16;
    private static final int PANEL_SPACING = 5;

    public MenuTab() {
        configurePanel();
        createPanels();
    }

    private void configurePanel() {
        setLayout(new GridBagLayout());

        setBackground(
                // Color Reference: https://teaching.csse.uwa.edu.au/units/CITS1001/colorinfo.html
                // Light grey "204-204-204"
                new Color(204, 204, 204)
        );

        setBorder(
                BorderFactory.createEmptyBorder(
                        OUTER_PADDING,
                        OUTER_PADDING,
                        OUTER_PADDING,
                        OUTER_PADDING
                )
        );
    }

    private void createPanels() {
        NutritionProgress progressPanel =
                new NutritionProgress();

        Profile profilePanel =
                new Profile();

        EatenToday eatenTodayPanel =
                new EatenToday();

        FoodLogging foodLoggingPanel =
                new FoodLogging();

        positionPanel(
                progressPanel,
                0,
                0,
                6,
                3,
                0.52,
                0.4
        );

        positionPanel(
                profilePanel,
                6,
                0,
                4,
                4,
                0.48,
                0.55
        );

        positionPanel(
                eatenTodayPanel,
                0,
                3,
                6,
                2,
                0.6,
                0.25
        );

        positionPanel(
                foodLoggingPanel,
                6,
                4,
                4,
                1,
                0.4,
                0.15
        );
    }

    private void positionPanel(
            JPanel panel,
            int gridX,
            int gridY,
            int gridWidth,
            int gridHeight,
            double weightX,
            double weightY
    ) {
        GridBagConstraints constraints = new GridBagConstraints();

        // Panel starts at grid position (gridX, gridY)
        constraints.gridx = gridX;
        constraints.gridy = gridY;

        // Panel occupies gridHeight amount of grid columns
        // and gridWidth amount of grid rows
        constraints.gridheight = gridHeight;
        constraints.gridwidth = gridWidth;

        // Panel receives weightX portion of expandable width relative to
        // weightY portion of expandable height when the window scales
        constraints.weightx = weightX;
        constraints.weighty = weightY;

        constraints.fill = GridBagConstraints.BOTH;

        constraints.anchor = GridBagConstraints.NORTHWEST;

        constraints.insets = new Insets(
                PANEL_SPACING,
                PANEL_SPACING,
                PANEL_SPACING,
                PANEL_SPACING
        );

        add(panel, constraints);
    }
}
