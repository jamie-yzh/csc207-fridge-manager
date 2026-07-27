package app.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class RecipePanel extends JPanel {

    public RecipePanel() {
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel(
                "Recipe Menu"
        );

        // Temp
        add(titleLabel, BorderLayout.CENTER);
    }
}