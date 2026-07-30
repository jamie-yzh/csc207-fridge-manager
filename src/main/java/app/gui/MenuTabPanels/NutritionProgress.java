package app.gui.MenuTabPanels;

import app.gui.components.ProgressBar;
import app.gui.components.RoundPanel;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;

public class NutritionProgress extends RoundPanel {

    private static final Color BORDER_COLOR = new Color(210, 210, 210);

    private static final Color CALORIE_COLOR = new Color(51, 153, 255);

    private static final Color PROTEIN_COLOR = new Color(0, 204, 0);

    private static final Color CARBS_COLOR = new Color(255, 204, 0);

    private static final Color FAT_COLOR = new Color(102, 0, 153);


    public NutritionProgress() {
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
                "Calories",
                1240,
                2000,
                "Kcal",
                CALORIE_COLOR
        );

        addProgressBar(
                "Protein",
                68,
                120,
                "g",
                PROTEIN_COLOR
        );

        addProgressBar(
                "Carbs",
                122,
                220,
                "Kcal",
                CARBS_COLOR
        );

        addProgressBar(
                "Fat",
                50,
                65,
                "g",
                FAT_COLOR
        );
    }

    private void addProgressBar(
            String nutrientName,
            int currentValue,
            int targetValue,
            String unit,
            Color progressColor
    ) {
        JPanel sectionPanel = createProgressBar(
                nutrientName,
                currentValue,
                targetValue,
                unit,
                progressColor
        );

        sectionPanel.setAlignmentX(LEFT_ALIGNMENT);

        add(sectionPanel);
    }

    private JPanel createProgressBar(
            String nutrientName,
            int currentValue,
            int targetValue,
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
                currentValue + " / " + targetValue + " " + unit
        );

        labelPanel.add(nameLabel, BorderLayout.WEST);
        labelPanel.add(valueLabel, BorderLayout.EAST);

        ProgressBar progressBar =
                new ProgressBar(
                        currentValue,
                        targetValue,
                        progressColor
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
