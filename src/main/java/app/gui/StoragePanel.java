package app.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class StoragePanel extends JPanel {

    public StoragePanel() {
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel(
                "Storage Menu"
        );

        // Temp
        add(titleLabel, BorderLayout.CENTER);
    }
}
