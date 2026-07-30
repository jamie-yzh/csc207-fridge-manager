package app.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class RecipeTab extends JPanel {

    public RecipeTab() {
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel(
                "Recipe Menu"
        );

        // Temp
        add(titleLabel, BorderLayout.CENTER);
    }
}