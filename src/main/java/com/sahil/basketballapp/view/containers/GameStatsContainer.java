package com.sahil.basketballapp.view.containers;

import java.awt.BorderLayout;
import java.util.HashMap;

import javax.swing.JPanel;

import com.sahil.basketballapp.model.GameModel;
import com.sahil.basketballapp.view.components.PieChartPanel;
import com.sahil.basketballapp.view.components.TeamListComponent;

public class GameStatsContainer extends JPanel {

    public GameStatsContainer(GameModel gameModel, HashMap<String, Integer> playerScoreMap) {
        setLayout(new BorderLayout());
        TeamListComponent teamListComponent = new TeamListComponent(
                gameModel.getTeamName(),
                gameModel.getOpponentTeamName(),
                gameModel.getDate(),
                gameModel.getTeamScore(),
                gameModel.getOpponentTeamScore(),
                gameModel.getGameId(),
                false);
        add(teamListComponent, BorderLayout.NORTH);

        PieChartPanel pieChartPanel = new PieChartPanel(playerScoreMap, gameModel.getOpponentTeamScore());

        add(pieChartPanel, BorderLayout.CENTER);
    }

    public JPanel getPanel() {
        return this;
    }

}
