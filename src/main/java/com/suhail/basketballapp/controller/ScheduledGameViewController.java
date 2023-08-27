package com.suhail.basketballapp.controller;

import java.util.ArrayList;

import javax.swing.JPanel;

import com.suhail.basketballapp.model.GameModel;
import com.suhail.basketballapp.view.containers.GameListContainer;

public class ScheduledGameViewController {
    private GameListContainer gameResultsContainer;

    public ScheduledGameViewController() {
        ArrayList<GameModel> gameResults = GameModel.getAllScheduledGameModel();
        gameResultsContainer = new GameListContainer(gameResults);
    }

    public JPanel getPanel() {
        return gameResultsContainer;
    }
}
