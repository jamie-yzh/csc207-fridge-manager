package app.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class StorageTab extends JPanel {

    public StorageTab() {
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel(
                "Storage Menu"
        );

        // Temp
        add(titleLabel, BorderLayout.CENTER);
    }
}
