package app;

import app.gui.MainFrame;

import javax.swing.*;

public class Application {

    private Application() {
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {});
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
    }
}
