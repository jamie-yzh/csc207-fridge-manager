package app.gui;

import app.gui.StorageTabPanels.ItemAdderPanel;
import app.gui.StorageTabPanels.StoragePanel;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class StorageTab extends JPanel {

    private static final int OUTER_PADDING = 16;
    private static final int PANEL_SPACING = 5;
    private static final int PANEL_WIDTH = 720;
    private static final Color BACKGROUND_COLOR = new Color(204, 204, 204);

    public StorageTab() {
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
        ItemAdderPanel itemAdderPanel = new ItemAdderPanel();

        StoragePanel storagePanel = new StoragePanel();

        JPanel contentColumn = createContentColumn();

        positionPanel(
                contentColumn,
                itemAdderPanel,
                0,
                0.0,
                new Insets(
                        0,
                        0,
                        PANEL_SPACING / 2,
                        0
                )
        );

        positionPanel(
                contentColumn,
                storagePanel,
                1,
                1.0,
                new Insets(
                        PANEL_SPACING / 2,
                        0,
                        0,
                        0
                )
        );

        positionContentColumn(contentColumn);
    }

    private JPanel createContentColumn() {
        JPanel contentColumn = new JPanel(new GridBagLayout());

        contentColumn.setOpaque(false);

        contentColumn.setPreferredSize(
                new Dimension(
                        PANEL_WIDTH,
                        600
                )
        );

        contentColumn.setMinimumSize(
                new Dimension(
                        500,
                        0
                )
        );

        return contentColumn;
    }

    private void positionPanel(
            JPanel contentColumn,
            JPanel panel,
            int gridY,
            double weightY,
            Insets insets
    ) {
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.gridy = gridY;

        constraints.weightx = 1.0;
        constraints.weighty = weightY;

        constraints.fill = GridBagConstraints.BOTH;

        constraints.anchor = GridBagConstraints.NORTHWEST;

        constraints.insets = insets;

        contentColumn.add(panel, constraints);
    }

    private void positionContentColumn(
            JPanel contentColumn
    ) {
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.gridy = 0;

        constraints.weightx = 1.0;
        constraints.weighty = 1.0;

        constraints.fill = GridBagConstraints.VERTICAL;

        constraints.anchor = GridBagConstraints.NORTH;

        add(contentColumn, constraints);
    }

}
