package com.sahil.basketballapp.view.components;

import javax.swing.*;

import com.sahil.basketballapp.model.StatsModel;

import java.awt.Dimension;
import java.util.List;

public class GameScoreComponent extends JPanel {

    private JTable table;
    private JScrollPane scrollPane;
    private int gameID;

    public GameScoreComponent(List<String> playerNames, int gameID) {
        this(playerNames, gameID, true, null);
    }

    public GameScoreComponent(List<String> playerNames, int gameID, List<StatsModel> scoreData) {
        this(playerNames, gameID, true, scoreData);

    }

    public GameScoreComponent(List<String> playerNames, int gameID, boolean isEditable, List<StatsModel> scoreData) {
        String[] columnNames = { "Player Name", "Points", "Assists", "Rebounds", "Steals", "Blocks", "Turnovers", "FGM",
                "FGA" };

        this.gameID = gameID;

        Object[][] data = new Object[playerNames.size()][9];

        for (int i = 0; i < playerNames.size(); i++) {
            data[i][0] = playerNames.get(i);
            if (scoreData != null) {
                for (StatsModel statsModel : scoreData) {
                    if (statsModel.getPlayerName().equals(playerNames.get(i))) {
                        data[i][1] = statsModel.getPoints();
                        data[i][2] = statsModel.getAssists();
                        data[i][3] = statsModel.getRebounds();
                        data[i][4] = statsModel.getSteals();
                        data[i][5] = statsModel.getBlocks();
                        data[i][6] = statsModel.getTurnovers();
                        data[i][7] = statsModel.getFgm();
                        data[i][8] = statsModel.getFga();
                    }
                }
            } else {
                data[i][1] = 0;
                data[i][2] = 0;
                data[i][3] = 0;
                data[i][4] = 0;
                data[i][5] = 0;
                data[i][6] = 0;
                data[i][7] = 0;
                data[i][8] = 0;
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
