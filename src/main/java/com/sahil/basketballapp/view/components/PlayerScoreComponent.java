package com.sahil.basketballapp.view.components;

import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;

import com.sahil.basketballapp.model.StatsModel.FullPointsModel;

public class PlayerScoreComponent extends JPanel {
    private JTable table;
    private JScrollPane scrollPane;

    public PlayerScoreComponent(List<FullPointsModel> fullPointsModelList) {
        String[] columnNames = { "Game Date", "Points", "Assists", "Rebounds", "Steals", "Blocks", "Turnovers", "FGM",
                "FGA" };

        Object[][] data = new Object[fullPointsModelList.size()][9];

        for (int i = 0; i < fullPointsModelList.size(); i++) {
            data[i][0] = fullPointsModelList.get(i).date;
            data[i][1] = fullPointsModelList.get(i).points;
            data[i][2] = fullPointsModelList.get(i).assists;
            data[i][3] = fullPointsModelList.get(i).rebounds;
            data[i][4] = fullPointsModelList.get(i).steals;
            data[i][5] = fullPointsModelList.get(i).blocks;
            data[i][6] = fullPointsModelList.get(i).turnovers;
            data[i][7] = fullPointsModelList.get(i).fgm;
            data[i][8] = fullPointsModelList.get(i).fga;
        }

        table = new JTable(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(
                new Dimension(table.getPreferredSize().width, table.getRowHeight() * (fullPointsModelList.size() + 2)));
        add(scrollPane);
    }

    public JTable getTable() {
        return table;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

}
