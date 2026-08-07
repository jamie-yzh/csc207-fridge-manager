package app.gui;

import app.Config;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Dimension;

public class MainFrame extends JFrame {

    /** Builds the wired-up tabs; the window itself stays free of plumbing. */
    private final Config config = new Config();

    public MainFrame() {
        configureWindow();
        pageLayout();
    }

    private void configureWindow() {
        // Set application title below
        setTitle("Fridge Manager");

        setDefaultCloseOperation(
                WindowConstants.EXIT_ON_CLOSE
        );

        // Set window size/dimension
        setSize(1000, 850);
        setMinimumSize(new Dimension(700, 500));
        // Window centering, on screen center by default (null)
        setLocationRelativeTo(null);
    }

    private void pageLayout() {
        // Initialize Layout manager, consists of 5 regions
        // (NORTH, EAST, SOUTH, WEST, CENTER)
        setLayout(new BorderLayout());
        
        JTabbedPane tabbedPane = createTabbedPane();
        
        add(tabbedPane, BorderLayout.CENTER);
        }

    private JTabbedPane createTabbedPane() {
        JTabbedPane tabbedPane = new JTabbedPane();

        // Tab 1
        tabbedPane.addTab(
                "Nutrition",
                config.createMenuTab()
        );

        // Tab 2
        tabbedPane.addTab(
                "Storage",
                new StorageTab()
        );

        // Tab 3
        tabbedPane.addTab(
                "Recipe",
                new RecipeTab()
        );

        return tabbedPane;
    }
}