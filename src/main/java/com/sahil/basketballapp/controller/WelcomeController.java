package com.sahil.basketballapp.controller;

import java.util.List;

import javax.swing.JOptionPane;

import com.sahil.basketballapp.model.GameModel;
import com.sahil.basketballapp.model.PlayerInfoModel;
import com.sahil.basketballapp.model.TeamModel;
import com.sahil.basketballapp.view.containers.WelcomeContainer;

public class WelcomeController {

    private WelcomeContainer welcomeContainer;

    public WelcomeController() {
        List<GameModel> playedGames = GameModel.getAllPlayedGameModel();
        if (playedGames.size() > 2) {
            playedGames = playedGames.subList(0, 2);
        } else {
            playedGames = playedGames.subList(0, playedGames.size());
        }
        System.out.println(playedGames.size());
        GameModel nextScheduledGame;
        try {
            nextScheduledGame = GameModel.getAllScheduledGameModel().get(0);
        } catch (Exception e) {
            nextScheduledGame = new GameModel();
        }
        List<String> allTeamsNames = TeamModel.getAllTeamNames();
        List<String> allPlayersNames = PlayerInfoModel.getAllPlayerNames();

        welcomeContainer = new WelcomeContainer(playedGames, nextScheduledGame, allTeamsNames,
                allPlayersNames);

        welcomeContainer.getScheduleGameButton().addActionListener(e -> {
            NavigationController.navigateToScheduleGame();
        });

        welcomeContainer.getAddPlayerButton().addActionListener(e -> {
            NavigationController.navigateToAddNewPlayer();
        });

        welcomeContainer.getViewAllUpcomingGamesButton().addActionListener(e -> {
            NavigationController.navigateToScheduledGames();
        });

        welcomeContainer.getPreviousResultsButton().addActionListener(e -> {
            NavigationController.navigateToGameResults();
        });

        welcomeContainer.getSearchButton().addActionListener(e -> {
            String teamName = welcomeContainer.getTeamComboBox().getSelectedItem().toString();
            String playerName = welcomeContainer.getPlayerComboBox().getSelectedItem().toString();
            if (teamName != null && !teamName.isEmpty() && teamName != "Select Team" && playerName != null
                    && !playerName.isEmpty() && playerName != "Select Player") {
                JOptionPane.showMessageDialog(null, "Please select either a team or a player");
            } else if (teamName != null && !teamName.isEmpty() && teamName != "Select Team") {
                NavigationController.navigateToTeamStats(teamName);
            } else if (playerName != null && !playerName.isEmpty()
                    && playerName != "Select Player") {
                NavigationController.navigateToPlayerStats(playerName);
            } else {
                JOptionPane.showMessageDialog(null, "Please select a team or a player");
            }
        });
    }

    public WelcomeContainer getViewPanel() {

        return welcomeContainer;
    }
}
