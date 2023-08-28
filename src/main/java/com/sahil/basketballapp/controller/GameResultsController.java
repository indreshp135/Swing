package com.sahil.basketballapp.controller;

import java.util.ArrayList;

import javax.swing.JPanel;

import com.sahil.basketballapp.model.GameModel;
import com.sahil.basketballapp.view.containers.GameListContainer;

public class GameResultsController {
    private GameListContainer gameResultsContainer;
    public GameResultsController() {
        ArrayList<GameModel> gameResults = GameModel.getAllPlayedGameModel();
        gameResultsContainer = new GameListContainer(gameResults, "Game Results");
    }

    public JPanel getPanel() {
        return gameResultsContainer;
    }
}
