package com.sahil.basketballapp.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sahil.basketballapp.database.DatabaseConnection;

public class StatsModel {

    // String createStatsTable = "CREATE TABLE IF NOT EXISTS Stats (" +
    // "playerName TEXT REFERENCES Player(playerName)," +
    // "gameId INTEGER REFERENCES Game(gameId)," +
    // "points INTEGER," +
    // "assists INTEGER," +
    // "rebounds INTEGER," +
    // "fgm INTEGER," +
    // "fga INTEGER," +
    // "steals INTEGER," +
    // "turnovers INTEGER," +
    // "blocks INTEGER," +
    // "PRIMARY KEY (playerName, gameId)" +
    // ")";

    private String playerName;
    private int gameId;
    private int points;
    private int assists;
    private int rebounds;
    private int fgm;
    private int fga;
    private int steals;
    private int turnovers;
    private int blocks;

    public class PlayerPointsModel {
        private int points;
        private String date;

        public PlayerPointsModel(int points, String date) {
            this.points = points;
            this.date = date;
        }

        public int getPoints() {
            return points;
        }

        public String getDate() {
            return date;
        }
    }

    public StatsModel(String playerName, int gameId, int points, int assists, int rebounds, int fgm, int fga,
            int steals, int turnovers, int blocks) {
        this.playerName = playerName;
        this.gameId = gameId;
        this.points = points;
        this.assists = assists;
        this.rebounds = rebounds;
        this.fgm = fgm;
        this.fga = fga;
        this.steals = steals;
        this.turnovers = turnovers;
        this.blocks = blocks;
    }

    public StatsModel() {
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getGameId() {
        return gameId;
    }

    public int getPoints() {
        return points;
    }

    public int getAssists() {
        return assists;
    }

    public int getRebounds() {
        return rebounds;
    }

    public int getFgm() {
        return fgm;
    }

    public int getFga() {
        return fga;
    }

    public int getSteals() {
        return steals;
    }

    public int getTurnovers() {
        return turnovers;
    }

    public int getBlocks() {
        return blocks;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    // Add Stats to Database
    public void addStats() {
        try {
            Connection connection = new DatabaseConnection().getConnection();
            String query = "INSERT INTO Stats (playerName, gameId, points, assists, rebounds, fgm, fga, steals, turnovers, blocks) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, playerName);
            preparedStatement.setInt(2, gameId);
            preparedStatement.setInt(3, points);
            preparedStatement.setInt(4, assists);
            preparedStatement.setInt(5, rebounds);
            preparedStatement.setInt(6, fgm);
            preparedStatement.setInt(7, fga);
            preparedStatement.setInt(8, steals);
            preparedStatement.setInt(9, turnovers);
            preparedStatement.setInt(10, blocks);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<PlayerPointsModel> getPlayerPointsModel(String playerName) {
        List<PlayerPointsModel> playerPointsModelList = new ArrayList<>();
        try {
            Connection connection = new DatabaseConnection().getConnection();
            String query = "SELECT s.points, g.date FROM Stats s INNER JOIN Game g ON Stats.gameId = Game.gameId WHERE playerName = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, playerName);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int points = resultSet.getInt("points");
                String date = resultSet.getString("date");
                PlayerPointsModel playerPointsModel = new PlayerPointsModel(points, date);
                playerPointsModelList.add(playerPointsModel);
            }

            preparedStatement.close();

            return playerPointsModelList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
