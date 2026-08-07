package app.gui.MenuTabPanels;

import app.gui.components.RoundPanel;
import interface_adapter.nutrition.NutrientGoalView;
import interface_adapter.nutrition.NutritionController;
import interface_adapter.nutrition.NutritionViewModel;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

public class Profile extends RoundPanel {
    private static final Color BORDER_COLOR = new Color(210, 210, 210);

    private static final int FIELD_HEIGHT = 28;
    private static final int HORIZONTAL_GAP = 10;
    private static final int VERTICAL_GAP = 8;

    private final NutritionController controller;
    private final NutritionViewModel viewModel;

    private JTextField weightField;
    private JTextField heightField;
    private JTextField ageField;

    private JComboBox<String> sexComboBox;

    JComboBox<String> activityComboBox;
    JComboBox<String> objectiveComboBox;

    private JTextField caloriesField;
    private JTextField proteinField;
    private JTextField carbsField;
    private JTextField fatField;

    private JButton calculateButton;

    public Profile(
            NutritionController controller,
            NutritionViewModel viewModel
    ) {
        super(
                18,
                Color.WHITE,
                BORDER_COLOR
        );

        this.controller = controller;
        this.viewModel = viewModel;

        configurePanel();
        createComponents();
        arrangeComponents();
        connectToUseCase();
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

        sexComboBox = new JComboBox<>(
                new String[]{
                        "Male",
                        "Female"
                }
        );

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

        // These four show the calculated goal — they are output, not input,
        // so nothing can be typed into them.
        makeReadOnly(caloriesField);
        makeReadOnly(proteinField);
        makeReadOnly(carbsField);
        makeReadOnly(fatField);

        calculateButton = new JButton("Calculate Goal");
    }

    private void makeReadOnly(
            JTextField field
    ) {
        field.setEditable(false);
        field.setFocusable(false);
        field.setBackground(new Color(245, 245, 245));
    }

    private void arrangeComponents() {
        JPanel weightInput = createLabeledPanels("Weight (kg)", weightField);
        JPanel heightInput = createLabeledPanels("Height (cm)", heightField);
        JPanel ageInput = createLabeledPanels("Age", ageField);
        JPanel sexInput = createLabeledPanels("Sex", sexComboBox);
        JPanel activityInput = createLabeledPanels("Activity", activityComboBox);
        JPanel objectiveInput = createLabeledPanels("Objective", objectiveComboBox);
        JSeparator separator = new JSeparator();
        JPanel caloriesInput = createLabeledPanels("Calories (kcal)", caloriesField);
        JPanel proteinInput = createLabeledPanels("Protein (g)", proteinField);
        JPanel carbsInput = createLabeledPanels("Carbs (g)", carbsField);
        JPanel fatInput = createLabeledPanels("Fat (g)", fatField);

        JLabel fineTuneLabel = new JLabel("Your daily target:");
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
                sexInput,
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
                activityInput,
                createConstraints(
                        0,
                        2,
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
                objectiveInput,
                createConstraints(
                        1,
                        2,
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
        inputConstraints.gridy = 1;
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

    /**
     * Connect the form to the use case. The button sends the profile in through
     * the controller; the view model tells us when a goal comes back out. This
     * panel never touches the interactor or an entity — only those two.
     */
    private void connectToUseCase() {
        calculateButton.addActionListener(
                event -> submitProfile()
        );

        viewModel.addPropertyChangeListener(
                event -> showGoal()
        );
    }

    private void submitProfile() {
        try {
            controller.calculateGoal(
                    ageField.getText(),
                    String.valueOf(sexComboBox.getSelectedItem()),
                    weightField.getText(),
                    heightField.getText(),
                    String.valueOf(activityComboBox.getSelectedItem()),
                    String.valueOf(objectiveComboBox.getSelectedItem())
            );
        }
        catch (IllegalArgumentException exception) {
            // The controller rejects anything it cannot parse; show the user why.
            JOptionPane.showMessageDialog(
                    this,
                    exception.getMessage(),
                    "Check your profile",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    /** Fill the four target fields from whatever the view model now holds. */
    private void showGoal() {
        showTarget(caloriesField, NutrientGoalView.CALORIES);
        showTarget(proteinField, NutrientGoalView.PROTEIN);
        showTarget(carbsField, NutrientGoalView.CARBS);
        showTarget(fatField, NutrientGoalView.FAT);
    }

    private void showTarget(
            JTextField field,
            String nutrientName
    ) {
        Optional<NutrientGoalView> nutrient =
                viewModel.getNutrient(nutrientName);

        field.setText(
                nutrient.map(row -> String.valueOf(row.target()))
                        .orElse("")
        );
    }
}
