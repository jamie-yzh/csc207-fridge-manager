package app.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class MenuPanel extends JPanel {

    public MenuPanel() {
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel(
                "Main Menu"
        );

        // Temp
        add(titleLabel, BorderLayout.CENTER);
    }
}
