package com.sahil.basketballapp.controller;

import com.sahil.basketballapp.model.GameModel;
import com.sahil.basketballapp.model.PlayerInfoModel;
import com.sahil.basketballapp.view.containers.GameResultEntryContainer;

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
