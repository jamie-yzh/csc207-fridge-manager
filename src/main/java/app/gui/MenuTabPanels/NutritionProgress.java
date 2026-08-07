package app.gui.MenuTabPanels;

import app.gui.components.ProgressBar;
import app.gui.components.RoundPanel;
import interface_adapter.nutrition.NutrientGoalView;
import interface_adapter.nutrition.NutritionViewModel;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class NutritionProgress extends RoundPanel {

    private static final Color BORDER_COLOR = new Color(210, 210, 210);

    private static final Color CALORIE_COLOR = new Color(51, 153, 255);

    private static final Color PROTEIN_COLOR = new Color(0, 204, 0);

    private static final Color CARBS_COLOR = new Color(255, 204, 0);

    private static final Color FAT_COLOR = new Color(102, 0, 153);

    /** Shown until a goal has been calculated — better than a misleading 0 / 0. */
    private static final String NO_GOAL_YET = "— / —";

    /**
     * The two widgets that make up one row. They are kept in a field (rather than
     * being built and forgotten) so a new goal can update them in place.
     */
    private record Row(JLabel valueLabel, ProgressBar bar) {
    }

    private final NutritionViewModel viewModel;

    /** One row per nutrient, keyed by name so order never matters. */
    private final Map<String, Row> rows = new LinkedHashMap<>();

    public NutritionProgress(
            NutritionViewModel viewModel
    ) {
        super(
                18,
                Color.WHITE,
                BORDER_COLOR
        );

        this.viewModel = viewModel;

        configurePanel();
        createComponents();

        viewModel.addPropertyChangeListener(
                event -> showGoal()
        );
    }

    private void configurePanel() {
        setLayout(
                new BoxLayout(
                        this,
                        BoxLayout.Y_AXIS
                )
        );

        setBorder(
                BorderFactory.createEmptyBorder(
                        16,
                        16,
                        16,
                        16
                )
        );

        setPreferredSize(
                new Dimension(360, 180)
        );

        setMinimumSize(
                new Dimension(240, 150)
        );
    }

    private void createComponents() {
        JLabel header = new JLabel("Today's progress");

        header.setFont(
                header.getFont().deriveFont(
                        Font.BOLD,
                        17f
                )
        );

        header.setAlignmentX(LEFT_ALIGNMENT);

        add(header);
        add(Box.createVerticalStrut(14));

        addProgressBar(
                NutrientGoalView.CALORIES,
                "Kcal",
                CALORIE_COLOR
        );

        addProgressBar(
                NutrientGoalView.PROTEIN,
                "g",
                PROTEIN_COLOR
        );

        addProgressBar(
                NutrientGoalView.CARBS,
                "g",
                CARBS_COLOR
        );

        addProgressBar(
                NutrientGoalView.FAT,
                "g",
                FAT_COLOR
        );

        // The view model may already hold a goal (e.g. a saved profile).
        showGoal();
    }

    /**
     * Redraw every row from the view model. Rows the view model has nothing for
     * are left showing the "no goal yet" placeholder.
     */
    private void showGoal() {
        rows.forEach(
                (nutrientName, row) -> viewModel.getNutrient(nutrientName).ifPresent(
                        nutrient -> {
                            row.valueLabel().setText(nutrient.amount());
                            row.bar().setMax(nutrient.target());
                            row.bar().setValue(nutrient.current());
                        }
                )
        );
    }

    private void addProgressBar(
            String nutrientName,
            String unit,
            Color progressColor
    ) {
        JPanel sectionPanel = createProgressBar(
                nutrientName,
                unit,
                progressColor
        );

        sectionPanel.setAlignmentX(LEFT_ALIGNMENT);

        add(sectionPanel);
    }

    private JPanel createProgressBar(
            String nutrientName,
            String unit,
            Color progressColor
    ) {
        JPanel sectionPanel = new JPanel();

        sectionPanel.setLayout(
                new BoxLayout(
                        sectionPanel,
                        BoxLayout.Y_AXIS
                )
        );

        sectionPanel.setOpaque(false);

        JPanel labelPanel = new JPanel(
                new BorderLayout()
        );

        labelPanel.setOpaque(false);

        JLabel nameLabel = new JLabel(nutrientName);

        nameLabel.setFont(
                nameLabel.getFont().deriveFont(
                        Font.BOLD,
                        14f
                )
        );

        JLabel valueLabel = new JLabel(
                NO_GOAL_YET + " " + unit
        );

        labelPanel.add(nameLabel, BorderLayout.WEST);
        labelPanel.add(valueLabel, BorderLayout.EAST);

        // An empty bar to start; showGoal() fills it once a goal exists.
        ProgressBar progressBar =
                new ProgressBar(
                        0,
                        0,
                        progressColor
                );

        rows.put(
                nutrientName,
                new Row(valueLabel, progressBar)
        );

        labelPanel.setAlignmentX(LEFT_ALIGNMENT);
        progressBar.setAlignmentX(LEFT_ALIGNMENT);

        sectionPanel.add(labelPanel);
        sectionPanel.add(progressBar);

        sectionPanel.setMinimumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        sectionPanel.getPreferredSize().height
                )
        );

        return sectionPanel;
    }
}
