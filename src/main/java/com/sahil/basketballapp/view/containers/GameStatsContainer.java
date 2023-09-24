package com.sahil.basketballapp.view.containers;

import java.awt.BorderLayout;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.util.List;
import java.util.Set;

import com.sahil.basketballapp.controller.NavigationController;
import com.sahil.basketballapp.model.GameModel;
import com.sahil.basketballapp.model.StatsModel;
import com.sahil.basketballapp.view.components.GameScoreComponent;
import com.sahil.basketballapp.view.components.PieChartPanel;
import com.sahil.basketballapp.view.components.TeamListComponent;

public class GameStatsContainer extends JPanel {

    public GameStatsContainer(GameModel gameModel, HashMap<String, Integer> playerScoreMap,
            List<StatsModel> statsModelList) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JPanel panel = new JPanel(new BorderLayout());
        TeamListComponent teamListComponent = new TeamListComponent(
                gameModel.getTeamName(),
                gameModel.getOpponentTeamName(),
                gameModel.getDate(),
                gameModel.getTeamScore(),
                gameModel.getOpponentTeamScore(),
                gameModel.getGameId(),
                false);

        panel.add(teamListComponent, BorderLayout.SOUTH);

        JButton editButton = new JButton("Edit");
        editButton.addActionListener(e -> {
            NavigationController.navigateToEditGameResult(gameModel.getGameId());
        });
        panel.add(editButton, BorderLayout.EAST);

        add(panel, BorderLayout.NORTH);

        PieChartPanel pieChartPanel = new PieChartPanel(playerScoreMap, gameModel.getOpponentTeamScore());

        add(pieChartPanel, BorderLayout.CENTER);

        Set<String> playerNames = playerScoreMap.keySet();
        // Convert to list
        List<String> playerNamesArray = List.copyOf(playerNames);

        GameScoreComponent gameScoreComponent = new GameScoreComponent(playerNamesArray, gameModel.getGameId(),
                statsModelList);
        add(gameScoreComponent, BorderLayout.SOUTH);
    }

    public JPanel getPanel() {
        return this;
    }

}
