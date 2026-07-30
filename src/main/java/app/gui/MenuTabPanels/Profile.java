package app.gui.MenuTabPanels;

import app.gui.components.RoundPanel;

import javax.swing.*;
import java.awt.*;

public class Profile extends RoundPanel {
    private static final Color BORDER_COLOR = new Color(210, 210, 210);

    private static final int FIELD_HEIGHT = 28;
    private static final int HORIZONTAL_GAP = 10;
    private static final int VERTICAL_GAP = 8;

    private JTextField weightField;
    private JTextField heightField;
    private JTextField ageField;

    JComboBox<String> activityComboBox;
    JComboBox<String> objectiveComboBox;

    private JTextField caloriesField;
    private JTextField proteinField;
    private JTextField carbsField;
    private JTextField fatField;

    private JButton calculateButton;

    public Profile() {
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
        setLayout(new GridBagLayout());

        setBorder(
                BorderFactory.createEmptyBorder(
                        16,
                        16,
                        16,
                        16
                )
        );

        setPreferredSize(
                new Dimension(370, 470)
        );

        setMinimumSize(
                new Dimension(320, 400)
        );
    }

    private void createComponents() {
        weightField = new JTextField();
        heightField = new JTextField();
        ageField = new JTextField();

        activityComboBox = new JComboBox<>(
                new String[]{
                        "Light",
                        "Moderate",
                        "Active"
                }
        );
        activityComboBox.setSelectedItem("Moderate");

        objectiveComboBox = new JComboBox<>(
                new String[]{
                        "Lose weight",
                        "Maintain",
                        "Gain weight"
                }
        );
        objectiveComboBox.setSelectedItem("Maintain");

        caloriesField = new JTextField();
        proteinField = new JTextField();
        carbsField = new JTextField();
        fatField = new JTextField();

        calculateButton = new JButton("Calculate Goal");
    }

    private void arrangeComponents() {
        JPanel weightInput = createLabeledPanels("Weight (kg)", weightField);
        JPanel heightInput = createLabeledPanels("Height (cm)", heightField);
        JPanel ageInput = createLabeledPanels("Age", ageField);
        JPanel activityInput = createLabeledPanels("Activity", activityComboBox);
        JPanel objectiveInput = createLabeledPanels("Objective", objectiveComboBox);
        JSeparator separator = new JSeparator();
        JPanel caloriesInput = createLabeledPanels("Calories (kcal)", caloriesField);
        JPanel proteinInput = createLabeledPanels("Protein (g)", proteinField);
        JPanel carbsInput = createLabeledPanels("Carbs (g)", carbsField);
        JPanel fatInput = createLabeledPanels("Fat (g)", fatField);

        JLabel fineTuneLabel = new JLabel("Fine-tune:");
        fineTuneLabel.setFont(
                fineTuneLabel.getFont().deriveFont(
                        Font.BOLD
                )
        );

        add(
                weightInput,
                createConstraints(
                        0,
                        0,
                        1,
                        0.5,
                        new Insets(
                                0,
                                0,
                                VERTICAL_GAP,
                                HORIZONTAL_GAP / 2
                        )
                )
        );

        add(
                heightInput,
                createConstraints(
                        1,
                        0,
                        1,
                        0.5,
                        new Insets(
                                0,
                                HORIZONTAL_GAP / 2,
                                VERTICAL_GAP,
                                0
                        )
                )
        );

        add(
                ageInput,
                createConstraints(
                        0,
                        1,
                        1,
                        0.5,
                        new Insets(
                                0,
                                0,
                                VERTICAL_GAP,
                                HORIZONTAL_GAP / 2
                        )
                )
        );

        add(
                activityInput,
                createConstraints(
                        1,
                        1,
                        1,
                        0.5,
                        new Insets(
                                0,
                                HORIZONTAL_GAP / 2,
                                VERTICAL_GAP,
                                0
                        )
                )
        );

        add(
                objectiveInput,
                createConstraints(
                        0,
                        2,
                        2,
                        1.0,
                        new Insets(
                                0,
                                0,
                                VERTICAL_GAP,
                                0
                        )
                )
        );

        add(
                calculateButton,
                createConstraints(
                        0,
                        3,
                        2,
                        1.0,
                        new Insets(
                                0,
                                0,
                                14,
                                0
                        )
                )
        );

        add(
                separator,
                createConstraints(
                        0,
                        4,
                        2,
                        1.0,
                        new Insets(
                                0,
                                0,
                                12,
                                0
                        )
                )
        );

        add(
                fineTuneLabel,
                createConstraints(
                        0,
                        5,
                        2,
                        1.0,
                        new Insets(
                                0,
                                0,
                                10,
                                0
                        )
                )
        );

        add(
                caloriesInput,
                createConstraints(
                        0,
                        6,
                        1,
                        0.5,
                        new Insets(
                                0,
                                0,
                                VERTICAL_GAP,
                                HORIZONTAL_GAP / 2
                        )
                )
        );

        add(
                proteinInput,
                createConstraints(
                        1,
                        6,
                        1,
                        0.5,
                        new Insets(
                                0,
                                HORIZONTAL_GAP / 2,
                                VERTICAL_GAP,
                                0
                        )
                )
        );

        add(
                carbsInput,
                createConstraints(
                        0,
                        7,
                        1,
                        0.5,
                        new Insets(
                                0,
                                0,
                                0,
                                HORIZONTAL_GAP / 2
                        )
                )
        );

        add(
                fatInput,
                createConstraints(
                        1,
                        7,
                        1,
                        0.5,
                        new Insets(
                                0,
                                HORIZONTAL_GAP / 2,
                                0,
                                0
                        )
                )
        );
    }

    private JPanel createLabeledPanels(
            String labelText,
            JComponent inputComponent
    ) {
        JPanel container = new JPanel(new GridBagLayout());

        container.setOpaque(false);

        GridBagConstraints labelConstraints = new GridBagConstraints();

        labelConstraints.gridx = 0;
        labelConstraints.gridx = 0;
        labelConstraints.weightx = 1.0;
        labelConstraints.fill = GridBagConstraints.HORIZONTAL;
        labelConstraints.anchor = GridBagConstraints.WEST;
        labelConstraints.insets = new Insets(0, 0, 4, 0);

        JLabel label = new JLabel(labelText);

        label.setForeground(Color.GRAY);

        container.add(
                label,
                labelConstraints
        );

        GridBagConstraints inputConstraints = new GridBagConstraints();

        inputConstraints.gridx = 0;
        inputConstraints.gridx = 1;
        inputConstraints.weightx = 1.0;
        inputConstraints.fill = GridBagConstraints.HORIZONTAL;

        inputComponent.setPreferredSize(
                new Dimension(
                        inputComponent.getPreferredSize().width,
                        FIELD_HEIGHT
                )
        );

        container.add(
                inputComponent,
                inputConstraints
        );

        return container;
    }

    private GridBagConstraints createConstraints(
            int gridX,
            int gridY,
            int gridWidth,
            double weightX,
            Insets insets
    ) {
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = gridX;
        constraints.gridy = gridY;
        constraints.gridwidth = gridWidth;
        constraints.weightx = weightX;
        constraints.weighty = 0.0;

        constraints.fill = GridBagConstraints.HORIZONTAL;

        constraints.anchor = GridBagConstraints.NORTHWEST;

        constraints.insets = insets;

        return constraints;
    }
}
