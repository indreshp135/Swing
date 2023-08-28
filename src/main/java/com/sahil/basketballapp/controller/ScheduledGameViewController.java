package com.sahil.basketballapp.controller;

import java.util.ArrayList;

import javax.swing.JPanel;

import com.sahil.basketballapp.model.GameModel;
import com.sahil.basketballapp.view.containers.GameListContainer;

public class ScheduledGameViewController {
    private GameListContainer gameResultsContainer;

    public ScheduledGameViewController() {
        ArrayList<GameModel> gameResults = GameModel.getAllScheduledGameModel();
        gameResultsContainer = new GameListContainer(gameResults, "Scheduled Games");
    }

    public JPanel getPanel() {
        return gameResultsContainer;
    }
}
