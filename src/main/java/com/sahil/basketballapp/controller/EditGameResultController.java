package com.sahil.basketballapp.controller;

import java.util.List;

import javax.swing.JOptionPane;

import com.sahil.basketballapp.model.GameModel;
import com.sahil.basketballapp.model.PlayerInfoModel;
import com.sahil.basketballapp.model.StatsModel;
import com.sahil.basketballapp.view.containers.GameResultEntryContainer;

public class EditGameResultController {
    private GameResultEntryContainer gameResultEntryContainer;

    public EditGameResultController(int gameId) {
        GameModel gameModel = GameModel.getGame(gameId);
        List<String> players = PlayerInfoModel.getPlayersByTeamName(gameModel.getTeamName());
        System.out.println("Opponent Score: " + gameModel.getOpponentTeamScore());
        gameResultEntryContainer = new GameResultEntryContainer(
                gameId,
                gameModel.getTeamName(),
                gameModel.getOpponentTeamName(),
                players,
                StatsModel.getGameStats(gameId),
                gameModel.getOpponentTeamScore());

        gameResultEntryContainer.getSaveButton().addActionListener(e -> {
            try {

                List<StatsModel> stats = gameResultEntryContainer.getStats();

                StatsModel.deleteStats(stats.get(0).getGameId());
                for (int i = 0; i < stats.size(); i++) {
                    StatsModel statsModel = stats.get(i);
                    statsModel.save();
                }

                int opponentScore = gameResultEntryContainer.getOpponentScore();

                updateScore(opponentScore, gameModel);

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Data Entry Error");
            }
            int option = JOptionPane.showConfirmDialog(null, "Would you like to add another game result?",
                    "Update Game Result", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                NavigationController.navigateToScheduledGames();
            } else {
                MainController.getDefaultView();
            }
        });

    }

    public void updateScore(int opponentScore, GameModel gameModel) {
        if (opponentScore >= 0) {
            gameModel.setOpponentTeamScore(opponentScore);
            gameModel.updateScore();
        } else {
            JOptionPane.showMessageDialog(null, "Please enter a valid score");
        }
    }

    public GameResultEntryContainer getViewPanel() {
        return gameResultEntryContainer;
    }
}
