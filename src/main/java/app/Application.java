package app;

import app.gui.MainFrame;

import javax.swing.*;

public class Application {

    private Application() {
    }

    public static void main(String[] args) {
        // Swing screens must be built and shown on the event-dispatch thread.
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}
