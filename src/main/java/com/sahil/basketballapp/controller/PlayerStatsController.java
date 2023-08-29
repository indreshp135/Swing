package com.sahil.basketballapp.controller;

import java.util.List;

import com.sahil.basketballapp.model.PlayerInfoModel;
import com.sahil.basketballapp.model.StatsModel;
import com.sahil.basketballapp.model.StatsModel.PointsModel;
import com.sahil.basketballapp.view.containers.PlayerStatsContainer;

public class PlayerStatsController {
    private PlayerStatsContainer playerStatsContainer;
    public PlayerStatsController(String playerName) {
        PlayerInfoModel player = PlayerInfoModel.getPlayerInfoByName(playerName);
        List<PointsModel> pointsModelList = new StatsModel().getPlayerPointsModel(playerName);

        playerStatsContainer = new PlayerStatsContainer(player, pointsModelList);

    }

    public PlayerStatsContainer getViewPanel() {
        return playerStatsContainer;
    }
}
