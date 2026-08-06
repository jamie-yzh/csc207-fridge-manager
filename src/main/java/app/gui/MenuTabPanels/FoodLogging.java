package app.gui.MenuTabPanels;

import app.gui.components.RoundPanel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class FoodLogging extends RoundPanel {

    private static final Color BORDER_COLOR = new Color(210, 210, 210);

    private static final String SEARCH_PLACEHOLDER = "Search for recipe...";

    private static final List<String> PLACEHOLDER_RECIPES =
            List.of(
                    "Chicken Salad",
                    "Chicken Pasta",
                    "Grilled chicken bowl",
                    "Steak",
                    "Fish soup"
            );

    private JTextField searchField;
    private JPopupMenu searchResultsPopup;
    private boolean showingPlaceholder;

    public FoodLogging() {
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
                new Dimension(360, 135)
        );

        setMinimumSize(
                new Dimension(240, 120)
        );
    }

    private void createComponents() {
        JLabel headingLabel = new JLabel("Log a recipe");

        headingLabel.setFont(
                headingLabel.getFont().deriveFont(
                        Font.BOLD,
                        17f
                )
        );

        JLabel descriptionLabel = new JLabel("Search for a recipe to log.");

        descriptionLabel.setForeground(
                Color.LIGHT_GRAY
        );

        descriptionLabel.setAlignmentX(
                LEFT_ALIGNMENT
        );

        searchField = new JTextField();

        searchField.setAlignmentX(
                LEFT_ALIGNMENT
        );

        searchField.setPreferredSize(
                new Dimension(300, 34)
        );

        searchField.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        34
                )
        );

        searchResultsPopup = new JPopupMenu();
        searchResultsPopup.setFocusable(false);

        configureSearchField();

        add(headingLabel);
        add(descriptionLabel);
        add(searchField);
    }

    private void configureSearchField() {
        showPlaceholder();

        searchField.addFocusListener(
                new FocusAdapter() {
                    @Override
                    public void focusGained(
                            FocusEvent event
                    ) {
                        if (showingPlaceholder) {
                            clearPlaceholder();
                        }
                    }

                    @Override
                    public void focusLost(
                            FocusEvent event
                    ) {
                        SwingUtilities.invokeLater(
                                () -> {
                                    if (searchField.getText().isBlank()) {
                                        showPlaceholder();
                                    }
                                }
                        );
                    }
                }
        );

        searchField.getDocument().addDocumentListener(
                new DocumentListener() {
                    @Override
                    public void insertUpdate(
                            DocumentEvent event
                    ) {
                        updateSearchResults();
                    }

                    @Override
                    public void removeUpdate(
                            DocumentEvent event
                    ) {
                        updateSearchResults();
                    }

                    @Override
                    public void changedUpdate(
                            DocumentEvent event
                    ) {
                        updateSearchResults();
                    }
                }
        );

    }

    private void updateSearchResults() {
        if (showingPlaceholder) {
            searchResultsPopup.setVisible(false);
            return;
        }

        String query = normalizeText(searchField.getText());

        searchResultsPopup.setVisible(false);
        searchResultsPopup.removeAll();

        if (query.isEmpty()) {
            searchResultsPopup.setVisible(false);
            return;
        }

        for (String recipeName : PLACEHOLDER_RECIPES) {
            if (recipeMatchesQuery(
                    recipeName,
                    query
            )) {
                addSearchResult(recipeName);
            }
        }

        if (searchResultsPopup.getComponentCount() == 0) {
            searchResultsPopup.setVisible(false);
            return;
        }

        searchResultsPopup.revalidate();

        Dimension popupSize = searchResultsPopup.getPreferredSize();

        popupSize.width = Math.max(
                popupSize.width,
                searchField.getWidth()
        );

        searchResultsPopup.setPopupSize(
                popupSize
        );

        searchResultsPopup.show(searchField, 0, searchField.getHeight());

        SwingUtilities.invokeLater(
                searchField::requestFocusInWindow
        );
    }

    private void addSearchResult(
            String recipeName
    ) {
        JMenuItem resultItem = new JMenuItem(recipeName);
        resultItem.setFocusable(false);

        resultItem.addActionListener(
                event -> {
                    searchResultsPopup.setVisible(false);
                    showPlaceholder();

                    // PLACEHOLDER
                    // TO BE IMPLEMENTED:
                    // 1. Send recipeName to a controller
                    // 2. Obtain nutrition information via use_case
                    // 3. Update EatenToday view model
                }
        );

        searchResultsPopup.add(resultItem);
    }

    private String normalizeText(
            String text
    ) {
        return text.toLowerCase(Locale.ROOT).replaceAll(
                "[^\\p{L}\\p{N}]+",
                " "
        ).trim();
    }

    private boolean recipeMatchesQuery(
            String recipeName,
            String query
    ) {
        String normalizedRecipeName = normalizeText(recipeName);

        // Matches beginning of a complete recipe name with query
        // Matches beginning of a later word in the recipe name
        return normalizedRecipeName.startsWith(query) || normalizedRecipeName.contains(
                " " + query
        );
    }

    private void showPlaceholder() {
        showingPlaceholder = true;
        searchField.setForeground(Color.LIGHT_GRAY);
        searchField.setText(SEARCH_PLACEHOLDER);
    }

    private void clearPlaceholder() {
        showingPlaceholder = false;
        searchField.setForeground(Color.BLACK);
        searchField.setText("");
    }
}
