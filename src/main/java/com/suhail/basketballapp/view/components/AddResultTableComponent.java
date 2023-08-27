package com.suhail.basketballapp.view.components;

import javax.swing.*;

import java.awt.Dimension;
import java.util.List;

public class AddResultTableComponent extends JPanel {

    private JTable table;
    private JScrollPane scrollPane;

    public AddResultTableComponent(List<String> playerNames) {
        String[] columnNames = { "Player Name", "Points", "Assists", "Rebounds", "Steals", "Blocks", "Turnovers", "FGM",
                "FGA" };

        Object[][] data = new Object[playerNames.size()][9];

        for (int i = 0; i < playerNames.size(); i++) {
            data[i][0] = playerNames.get(i);
            for (int j = 1; j < 9; j++) {
                data[i][j] = 0;
            }
        }

        table = new JTable(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0;
            }
        };

        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(
                new Dimension(table.getPreferredSize().width, table.getRowHeight() * (playerNames.size() + 2)));
        add(scrollPane);
    }

    public JTable getTable() {
        return table;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

}
