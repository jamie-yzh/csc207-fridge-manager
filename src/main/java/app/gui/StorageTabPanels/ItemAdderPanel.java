package app.gui.StorageTabPanels;

import app.gui.components.RoundPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class ItemAdderPanel extends RoundPanel {

    private static final Color BORDER_COLOR = new Color(210, 210, 210);

    private static final int FIELD_HEIGHT = 30;
    private static final int HORIZONTAL_GAP = 10;
    private static final String PLACEHOLDER_VISIBLE = "Placeholder present";

    private JTextField itemField;
    private JTextField quantityField;
    private JTextField expiryField;

    private JComboBox<String> locationComboBox;
    private JButton addButton;

    public ItemAdderPanel() {
        super(
                18,
                Color.WHITE,
                BORDER_COLOR
        );

        configurePanel();
        createComponents();
        arrangeComponents();
    }

    private void configurePanel() {
        setLayout(
                new GridBagLayout()
        );

        setBorder(
                BorderFactory.createEmptyBorder(
                        12,
                        16,
                        12,
                        16
                )
        );

        setPreferredSize(
                new Dimension(720, 110)
        );
    }

    private void createComponents() {
        itemField = createTextField("e.g. Broccoli");

        quantityField = createTextField("e.g. 500g");

        expiryField = createTextField("yyyy-mm-dd");

        locationComboBox = new JComboBox<>(
                new String[]{
                        "Refrigerator",
                        "Freezer",
                        "Pantry"
                }
        );

        setInputHeight(locationComboBox);

        addButton = new JButton("Add");
        addButton.setBackground(Color.BLUE);
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);

        setInputHeight(addButton);

    }

    private void arrangeComponents() {
        JLabel headingLabel = new JLabel("Add to fridge");

        headingLabel.setFont(
                headingLabel.getFont().deriveFont(
                        Font.BOLD,
                        17f
                )
        );

        GridBagConstraints headingConstraints = new GridBagConstraints();

        headingConstraints.gridx = 0;
        headingConstraints.gridy = 0;
        headingConstraints.gridwidth = 5;
        headingConstraints.weightx = 1.0;

        headingConstraints.fill = GridBagConstraints.HORIZONTAL;

        headingConstraints.anchor = GridBagConstraints.WEST;

        headingConstraints.insets = new Insets(0, 0, 8, 0);

        add(
                headingLabel,
                headingConstraints
        );

        addFieldLabel("Item", 0, 2.0);
        addFieldLabel("Quantity", 1, 1.0);
        addFieldLabel("Expiry", 2, 1.0);
        addFieldLabel("Location", 3, 0.9);

        addInput(itemField, 0, 2.0);
        addInput(quantityField, 1, 1.0);
        addInput(expiryField, 2, 1.0);
        addInput(locationComboBox, 3, 0.9);
        addInput(addButton, 4, 0.0);
    }

    private void addFieldLabel(
            String labelText,
            int gridX,
            double weightX
    ) {
        JLabel label = new JLabel(labelText);

        label.setForeground(
                Color.LIGHT_GRAY
        );

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = gridX;
        constraints.gridy = 1;
        constraints.weightx = weightX;

        constraints.fill = GridBagConstraints.HORIZONTAL;

        constraints.anchor = GridBagConstraints.WEST;

        constraints.insets =
                createColumnInsets(
                        gridX,
                        0,
                        4
                );

        add(label, constraints);
    }

    private void addInput(
            JComponent component,
            int gridX,
            double weightX
    ) {
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = gridX;
        constraints.gridy = 2;
        constraints.weightx = weightX;

        constraints.fill = GridBagConstraints.HORIZONTAL;

        constraints.anchor = GridBagConstraints.NORTHWEST;

        constraints.insets =
                createColumnInsets(
                        gridX,
                        0,
                        0
                );

        add(component, constraints);
    }

    private Insets createColumnInsets(
            int gridX,
            int top,
            int bottom
    ) {
        int halfGap = HORIZONTAL_GAP / 2;

        int left = gridX == 0 ? 0 : halfGap;

        int right = gridX == 4 ? 0 : halfGap;

        return new Insets(
                top,
                left,
                bottom,
                right
        );
    }

    private JTextField createTextField(
            String placeholder
    ) {
        JTextField field =
                new JTextField();

        setInputHeight(field);

        installPlaceholder(
                field,
                placeholder
        );

        return field;
    }

    private void setInputHeight(
            JComponent component
    ) {
        Dimension preferredSize =
                component.getPreferredSize();

        component.setPreferredSize(
                new Dimension(
                        preferredSize.width,
                        FIELD_HEIGHT
                )
        );
    }

    private void installPlaceholder(
            JTextField field,
            String placeholder
    ) {
        showPlaceholder(
                field,
                placeholder
        );

        field.addFocusListener(
                new FocusAdapter() {
                    @Override
                    public void focusGained(
                            FocusEvent event
                    ) {
                        if (isShowingPlaceholder(field)) {
                            field.putClientProperty(
                                    PLACEHOLDER_VISIBLE,
                                    false
                            );

                            field.setText("");
                            field.setForeground(Color.BLACK);
                        }
                    }

                    @Override
                    public void focusLost(
                            FocusEvent event
                    ) {
                        if (field.getText().isBlank()) {
                            showPlaceholder(
                                    field,
                                    placeholder
                            );
                        }
                    }
                }
        );
    }

    private void showPlaceholder(
            JTextField field,
            String placeholder
    ) {
        field.putClientProperty(
                PLACEHOLDER_VISIBLE,
                true
        );

        field.setForeground(
                Color.LIGHT_GRAY
        );

        field.setText(
                placeholder
        );
    }

    private boolean isShowingPlaceholder(
            JTextField field
    ) {
        return Boolean.TRUE.equals(
                field.getClientProperty(
                        PLACEHOLDER_VISIBLE
                )
        );
    }
}
