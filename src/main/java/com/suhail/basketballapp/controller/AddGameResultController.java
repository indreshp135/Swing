package com.suhail.basketballapp.controller;

import com.suhail.basketballapp.model.GameModel;
import com.suhail.basketballapp.model.PlayerInfoModel;
import com.suhail.basketballapp.view.containers.GameResultEntryContainer;

public class AddGameResultController {
    private GameResultEntryContainer gameResultEntryContainer;

    public AddGameResultController(int gameId) {
        GameModel gameModel = GameModel.getGame(gameId);
        System.out.println(gameModel.getTeamName());
        gameResultEntryContainer = new GameResultEntryContainer(
                gameModel.getTeamName(),
                gameModel.getOpponentTeamName(),
                PlayerInfoModel.getPlayersByTeamName(gameModel.getTeamName()));

    }

    public GameResultEntryContainer getViewPanel() {
        return gameResultEntryContainer;
    }
}
