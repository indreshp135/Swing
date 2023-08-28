package com.sahil.basketballapp.controller;

import java.util.List;

import javax.swing.JOptionPane;

import com.sahil.basketballapp.model.GameModel;
import com.sahil.basketballapp.model.PlayerInfoModel;
import com.sahil.basketballapp.model.StatsModel;
import com.sahil.basketballapp.view.containers.GameResultEntryContainer;

public class AddGameResultController {
    private GameResultEntryContainer gameResultEntryContainer;

    public AddGameResultController(int gameId) {
        GameModel gameModel = GameModel.getGame(gameId);
        List<String> players = PlayerInfoModel.getPlayersByTeamName(gameModel.getTeamName());
        gameResultEntryContainer = new GameResultEntryContainer(
                gameId,
                gameModel.getTeamName(),
                gameModel.getOpponentTeamName(),
                players);

        gameResultEntryContainer.getSaveButton().addActionListener(e -> {
            try {

                List<StatsModel> stats = gameResultEntryContainer.getStats();
                for (int i = 0; i < stats.size(); i++) {
                    System.out.println("Updating stats: " + stats.get(i).toString());
                    StatsModel statsModel = stats.get(i);
                    statsModel.save();
                    System.out.println("Saved stats: " + statsModel.toString());
                }

                int opponentScore = gameResultEntryContainer.getOpponentScore();

                updateScore(opponentScore, gameModel);

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Data Entry Error");
            }
            int option = JOptionPane.showConfirmDialog(null, "Would you like to add another game result?", "Add Game Result", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                MainController.navigateToScheduleGame();
            } else {
                MainController.getDefaultView();
            }
        });

    }

    public void updateScore(int opponentScore, GameModel gameModel) {
        if (opponentScore >= 0) {
            System.out.println("Updating game score: " + opponentScore);
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
