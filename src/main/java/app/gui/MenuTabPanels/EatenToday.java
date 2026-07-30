package app.gui.MenuTabPanels;

import app.gui.components.RoundPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class EatenToday extends RoundPanel {

    private static final Color BORDER_COLOR = new Color(210, 210, 210);

    // PLACEHOLDER FOR FUNCTIONALITY
    private static final String ITEM_COUNT = "3";

    private static final Color VALUE_COLOR = new Color(90, 90, 90);

    public EatenToday() {
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
                new Dimension(520, 190)
        );

        setMinimumSize(
                new Dimension(360, 150)
        );
    }

    private void createComponents() {
        JPanel headerPanel = createHeader();

        JScrollPane tableScrollPane = createTableScrollPane();

        add(headerPanel);
        add(tableScrollPane);
    }

    private JPanel createHeader() {

        JPanel headerPanel = new JPanel(new BorderLayout());

        headerPanel.setOpaque(false);

        JLabel titleLabel = new JLabel("Eaten today");

        titleLabel.setFont(
                titleLabel.getFont().deriveFont(
                        Font.BOLD,
                        17f
                )
        );

        JLabel totalItemLabel = new JLabel(ITEM_COUNT + " item");
        totalItemLabel.setForeground(VALUE_COLOR);

        headerPanel.add(
                titleLabel,
                BorderLayout.WEST
        );
        headerPanel.add(
                totalItemLabel,
                BorderLayout.EAST
        );

        headerPanel.setAlignmentX(LEFT_ALIGNMENT);

        headerPanel.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        headerPanel.getPreferredSize().height
                )
        );

        return headerPanel;
    }

    private JScrollPane createTableScrollPane() {
        JTable foodTable = createFoodTable();

        JScrollPane scrollPane = new JScrollPane(foodTable);

        scrollPane.setBorder(
                BorderFactory.createEmptyBorder()
        );

        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        scrollPane.setAlignmentX(LEFT_ALIGNMENT);

        return scrollPane;
    }

    private JTable createFoodTable() {
        String[] columnNames = {
                "Food",
                "Kcal",
                "P",
                "C",
                "F"
        };

        Object[][] tableData = {
                {
                        "Placeholder1",
                        1,
                        2,
                        3,
                        4
                },
                {
                        "Placeholder2",
                        1,
                        2,
                        3,
                        4
                },
                {
                        "Placeholder3",
                        1,
                        2,
                        3,
                        4
                }
        };

        DefaultTableModel tableModel = new DefaultTableModel(
                tableData,
                columnNames
        ) {
            @Override
            public boolean isCellEditable(
                    int row,
                    int column
            ) {
                return false;
            }
        };

        JTable table = new JTable(tableModel);

        table.setRowHeight(28);
        table.setFillsViewportHeight(true);

        table.setShowVerticalLines(false);
        table.setShowHorizontalLines(true);

        table.setGridColor(Color.LIGHT_GRAY);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setReorderingAllowed(false);

        table.getColumnModel().getColumn(0).setPreferredWidth(250);
        table.getColumnModel().getColumn(1).setPreferredWidth(70);
        table.getColumnModel().getColumn(2).setPreferredWidth(50);
        table.getColumnModel().getColumn(3).setPreferredWidth(50);
        table.getColumnModel().getColumn(4).setPreferredWidth(50);

        return table;
    }
}
