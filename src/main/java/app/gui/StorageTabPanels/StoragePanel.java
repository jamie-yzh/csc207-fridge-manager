package app.gui.StorageTabPanels;

import app.gui.components.RoundPanel;

import javax.swing.*;
import java.awt.*;

public class StoragePanel extends RoundPanel {

    private static final Color BORDER_COLOR = new Color(210, 210, 210);

    private static final String PLACEHOLDER_ITEM_COUNT = "3 items";

    private static final int ENTRY_HEIGHT = 40;

    public StoragePanel() {
        super(
                18,
                Color.WHITE,
                BORDER_COLOR
        );

        configurePanel();
        createComponents();
    }

    private void configurePanel() {
        setLayout(
                new BorderLayout()
        );

        setPreferredSize(
                new Dimension(720, 460)
        );

        setMinimumSize(
                new Dimension(500, 300)
        );
    }

    private void createComponents() {
        add(
                createHeaderPanel(),
                BorderLayout.NORTH
        );

        JPanel sectionsPanel = new JPanel(
                new GridLayout(
                        3,
                        1,
                        0,
                        0
                )
        );

        sectionsPanel.setOpaque(false);

        sectionsPanel.add(
                createStorageSection(
                        "REFRIGERATOR",
                        "Chicken breast",
                        "2 fillets",
                        "2026-08-10"
                )
        );

        sectionsPanel.add(
                createStorageSection(
                        "FREEZER",
                        "Frozen peas",
                        "500 g",
                        "2026-11-10"
                )
        );

        sectionsPanel.add(
                createStorageSection(
                        "PANTRY",
                        "Rice",
                        "1.2 kg",
                        "2027-01-10"
                )
        );

        add(
                sectionsPanel,
                BorderLayout.CENTER
        );
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());

        headerPanel.setOpaque(false);

        headerPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        14,
                        16,
                        12,
                        16
                )
        );

        JLabel headingLabel =
                new JLabel("In my kitchen");

        headingLabel.setFont(
                headingLabel.getFont().deriveFont(
                        Font.BOLD,
                        17f
                )
        );

        JLabel itemCountLabel =
                new JLabel(
                        PLACEHOLDER_ITEM_COUNT
                );

        itemCountLabel.setForeground(
                Color.LIGHT_GRAY
        );

        headerPanel.add(
                headingLabel,
                BorderLayout.WEST
        );

        headerPanel.add(
                itemCountLabel,
                BorderLayout.EAST
        );

        return headerPanel;
    }

    private JPanel createStorageSection(
            String sectionName,
            String itemName,
            String quantity,
            String expiryDate
    ) {
        JPanel sectionPanel = new JPanel(new BorderLayout());

        sectionPanel.setOpaque(false);

        sectionPanel.setBorder(
                BorderFactory.createMatteBorder(
                        1,
                        0,
                        0,
                        0,
                        BORDER_COLOR
                )
        );

        sectionPanel.add(
                createSectionHeading(
                        sectionName
                ),
                BorderLayout.NORTH
        );

        JPanel entriesPanel = new JPanel();

        entriesPanel.setLayout(
                new BoxLayout(
                        entriesPanel,
                        BoxLayout.Y_AXIS
                )
        );

        entriesPanel.setOpaque(false);

        entriesPanel.add(
                createEntryPanel(
                        itemName,
                        quantity,
                        expiryDate
                )
        );

        JScrollPane scrollPane =
                new JScrollPane(
                        entriesPanel,
                        ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
                );

        scrollPane.setBorder(
                BorderFactory.createEmptyBorder()
        );

        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        scrollPane
                .getVerticalScrollBar()
                .setUnitIncrement(16);

        sectionPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        return sectionPanel;
    }

    private JPanel createSectionHeading(
            String sectionName
    ) {
        JPanel headingPanel = new JPanel(new BorderLayout());

        headingPanel.setBackground(
                Color.GRAY
        );

        headingPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        6,
                        16,
                        6,
                        16
                )
        );

        JLabel sectionLabel = new JLabel(sectionName);

        sectionLabel.setForeground(
                Color.WHITE
        );

        sectionLabel.setFont(
                sectionLabel.getFont().deriveFont(
                        Font.BOLD,
                        12f
                )
        );

        headingPanel.add(
                sectionLabel,
                BorderLayout.WEST
        );

        return headingPanel;
    }

    private JPanel createEntryPanel(
            String itemName,
            String quantity,
            String expiryDate
    ) {
        JPanel entryPanel =
                new JPanel(
                        new GridBagLayout()
                );

        entryPanel.setOpaque(false);

        entryPanel.setBorder(
                BorderFactory.createMatteBorder(
                        0,
                        0,
                        1,
                        0,
                        new Color(235, 235, 235)
                )
        );

        entryPanel.setPreferredSize(
                new Dimension(
                        680,
                        ENTRY_HEIGHT
                )
        );

        // BoxLayout may expand the row horizontally,
        // but it should not expand it vertically.
        entryPanel.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        ENTRY_HEIGHT
                )
        );

        JLabel itemLabel = new JLabel(itemName);

        itemLabel.setFont(
                itemLabel.getFont().deriveFont(
                        Font.BOLD
                )
        );

        JLabel quantityLabel = new JLabel(quantity);

        quantityLabel.setForeground(
                Color.BLACK
        );

        JLabel expiryLabel = new JLabel(expiryDate);

        JButton removeButton = new JButton("Remove");

        removeButton.setFocusable(false);

        addEntryComponent(
                entryPanel,
                itemLabel,
                0,
                2.0
        );

        addEntryComponent(
                entryPanel,
                quantityLabel,
                1,
                1.0
        );

        addEntryComponent(
                entryPanel,
                expiryLabel,
                2,
                1.0
        );

        addEntryComponent(
                entryPanel,
                removeButton,
                3,
                0.0
        );

        return entryPanel;
    }

    private void addEntryComponent(
            JPanel entryPanel,
            Component component,
            int gridX,
            double weightX
    ) {
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = gridX;
        constraints.gridy = 0;
        constraints.weightx = weightX;

        constraints.fill = gridX == 3 ? GridBagConstraints.NONE
                : GridBagConstraints.HORIZONTAL;

        constraints.anchor = GridBagConstraints.WEST;

        constraints.insets = createEntryInsets(gridX);

        entryPanel.add(
                component,
                constraints
        );
    }

    private Insets createEntryInsets(
            int gridX
    ) {
        int left = gridX == 0 ? 16 : 6;

        int right = gridX == 3 ? 16 : 6;

        return new Insets(
                6,
                left,
                6,
                right
        );
    }
}
