package com.suhail.basketballapp.controller;

import java.util.ArrayList;

import javax.swing.JPanel;

import com.suhail.basketballapp.model.GameModel;
import com.suhail.basketballapp.view.containers.GameListContainer;

public class GameResultsController {
    private GameListContainer gameResultsContainer;
    public GameResultsController() {
        ArrayList<GameModel> gameResults = GameModel.getAllPlayedGameModel();
        gameResultsContainer = new GameListContainer(gameResults);
    }

    public JPanel getPanel() {
        return gameResultsContainer;
    } 
}
