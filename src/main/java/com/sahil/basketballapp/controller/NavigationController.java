package com.sahil.basketballapp.controller;

public class NavigationController {

    public static void navigateToScheduleGame() {
        ScheduleGameController scheduleGameController = new ScheduleGameController();
        MainController.replaceMainPanel(scheduleGameController.getViewPanel());
    }

    public static void navigateToGameResults() {
        GameResultsController gameResultsController = new GameResultsController();
        MainController.replaceMainPanel(gameResultsController.getPanel());
    }

    public static void navigateToScheduledGames() {
        ScheduledGameViewController scheduledGameViewController = new ScheduledGameViewController();
        MainController.replaceMainPanel(scheduledGameViewController.getPanel());

    }

    public static void navigateToAddNewPlayer() {
        AddNewPlayerController addNewPlayerController = new AddNewPlayerController();
        MainController.replaceMainPanel(addNewPlayerController.getViewPanel());
    }

    public static void navigateToAddGameResult(Integer gameId) {
        AddGameResultController addGameResultController = new AddGameResultController(gameId);
        MainController.replaceMainPanel(addGameResultController.getViewPanel());
    }

    public static void navigateToPlayerStats(String playerName) {
        PlayerStatsController playerStatsController = new PlayerStatsController(playerName);
        MainController.replaceMainPanel(playerStatsController.getViewPanel());
    }

    public static void navigateToTeamStats(String teamName) {
        TeamStatsController teamStatsController = new TeamStatsController(teamName);
        MainController.replaceMainPanel(teamStatsController.getViewPanel());
    }

    public static void navigateToWelcome() {
        WelcomeController welcomeController = new WelcomeController();
        MainController.replaceMainPanel(welcomeController.getViewPanel(), false);
    }

    public static void navigateToGameStats(Integer gameID) {
        GameStatsController gameStatsController = new GameStatsController(gameID);
        MainController.replaceMainPanel(gameStatsController.getViewPanel());
    }
}
