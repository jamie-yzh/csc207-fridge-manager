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
    private static final Color BACKGROUND_COLOR = new Color(204, 204, 204);

    public MenuTab() {
        configurePanel();
        createPanels();
    }

    private void configurePanel() {
        setLayout(new GridBagLayout());

        setBackground(BACKGROUND_COLOR);

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

        JPanel leftColumn = createColumnPanel();
        JPanel rightColumn = createColumnPanel();

        positionPanel(
                leftColumn,
                progressPanel,
                0,
                0.6
        );

        positionPanel(
                leftColumn,
                eatenTodayPanel,
                1,
                0.4
        );

        positionPanel(
                rightColumn,
                profilePanel,
                0,
                0.0
        );

        positionPanel(
                rightColumn,
                foodLoggingPanel,
                1,
                0.0
        );

        addVerticalSpacer(rightColumn, 2);

        positionColumn(leftColumn, 0, 0.62);
        positionColumn(rightColumn, 1, 0.38);
    }

    private JPanel createColumnPanel() {
        JPanel columnPanel = new JPanel(
                new GridBagLayout()
        );

        columnPanel.setOpaque(false);
        return columnPanel;
    }

    private void positionPanel(
            JPanel column,
            JPanel panel,
            int gridY,
            double weightY
    ) {
        GridBagConstraints constraints = new GridBagConstraints();

        // Panel starts at grid position (gridX, gridY)
        constraints.gridx = 0;
        constraints.gridy = gridY;

        // Panel receives weightX portion of expandable width relative to
        // weightY portion of expandable height when the window scales
        constraints.weightx = 1.0;
        constraints.weighty = weightY;

        constraints.fill = GridBagConstraints.BOTH;

        constraints.anchor = GridBagConstraints.NORTHWEST;

        constraints.insets = new Insets(
                PANEL_SPACING,
                PANEL_SPACING,
                PANEL_SPACING,
                PANEL_SPACING
        );

        column.add(panel, constraints);
    }

    private void addVerticalSpacer(
            JPanel column,
            int gridY
    ) {
        JPanel spacing = new JPanel();

        spacing.setOpaque(false);
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.gridy = gridY;
        constraints.weightx = 1.0;

        // Spacing receives all remaining unused vertical space
        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.BOTH;

        column.add(spacing, constraints);
    }

    private void positionColumn(
            JPanel column,
            int gridX,
            double weightX
    ) {
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = gridX;
        constraints.gridy = 0;

        constraints.weightx = weightX;
        constraints.weighty = 1.0;

        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.NORTHWEST;

        add(column, constraints);
    }
}
