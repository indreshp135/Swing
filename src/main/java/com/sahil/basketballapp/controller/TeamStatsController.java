package com.sahil.basketballapp.controller;

import java.util.List;

import com.sahil.basketballapp.model.StatsModel;
import com.sahil.basketballapp.model.TeamModel;
import com.sahil.basketballapp.model.StatsModel.PointsModel;
import com.sahil.basketballapp.view.containers.TeamStatsContainer;

public class TeamStatsController {
    private TeamStatsContainer teamStatsContainer;

    public TeamStatsController(String teamName) {
        TeamModel team = TeamModel.getTeamInfoByName(teamName);
        List<PointsModel> pointsModelList = new StatsModel().getTeamStats(teamName);

        teamStatsContainer = new TeamStatsContainer(team, pointsModelList);

    }

    public TeamStatsContainer getViewPanel() {
        return teamStatsContainer;
    }
}
