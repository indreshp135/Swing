package com.sahil.basketballapp.view.components;

import javax.swing.*;

import com.sahil.basketballapp.model.StatsModel;

import java.awt.Dimension;
import java.util.List;

public class ScoreComponent extends JPanel {

    private JTable table;
    private JScrollPane scrollPane;
    private int gameID;

    public ScoreComponent(List<String> playerNames, int gameID) {
        this(playerNames, gameID, true);
    }

    public ScoreComponent(List<String> playerNames, int gameID, boolean isEditable) {
        String[] columnNames = { "Player Name", "Points", "Assists", "Rebounds", "Steals", "Blocks", "Turnovers", "FGM",
                "FGA" };

        this.gameID = gameID;

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
                return isEditable && column != 0;
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

    // Final variables in the table
    public List<StatsModel> getTableData() {
        List<StatsModel> data = new java.util.ArrayList<>();
        for (int i = 0; i < table.getRowCount(); i++) {
            String playerName = (String) table.getValueAt(i, 0);
            int points = Integer.parseInt(table.getValueAt(i, 1).toString());
            int assists = Integer.parseInt(table.getValueAt(i, 2).toString());
            int rebounds = Integer.parseInt(table.getValueAt(i, 3).toString());
            int steals = Integer.parseInt(table.getValueAt(i, 4).toString());
            int blocks = Integer.parseInt(table.getValueAt(i, 5).toString());
            int turnovers = Integer.parseInt(table.getValueAt(i, 6).toString());
            int fgm = Integer.parseInt(table.getValueAt(i, 7).toString());
            int fga = Integer.parseInt(table.getValueAt(i, 8).toString());

            data.add(
                    new StatsModel(playerName, gameID, points, assists, rebounds, steals, blocks, turnovers, fgm, fga));
        }
        return data;
    }

}
