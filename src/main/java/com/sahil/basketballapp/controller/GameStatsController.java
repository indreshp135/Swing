package com.sahil.basketballapp.controller;

import java.util.HashMap;
import java.util.List;

import com.sahil.basketballapp.model.GameModel;
import com.sahil.basketballapp.model.StatsModel;
import com.sahil.basketballapp.view.containers.GameStatsContainer;

public class GameStatsController {
    private GameStatsContainer gameStatsContainer;

    public GameStatsController(Integer gameID) {
        List<StatsModel> statsModelList = StatsModel.getGameStats(gameID);

        HashMap<String, Integer> playerScoreMap = new HashMap<>();
        for (StatsModel statsModel : statsModelList) {
            playerScoreMap.put(statsModel.getPlayerName() + ": " + statsModel.getPoints(), statsModel.getPoints());
        }

        GameModel game = GameModel.getGameWithScore(gameID);

        gameStatsContainer = new GameStatsContainer(game, playerScoreMap);

    }

    public GameStatsContainer getViewPanel() {
        return gameStatsContainer;
    }
}
