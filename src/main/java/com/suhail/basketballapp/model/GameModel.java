package com.suhail.basketballapp.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.suhail.basketballapp.database.DatabaseConnection;

public class GameModel {
    private String teamName;
    private String opponentTeamName;
    private String date;
    private Integer opponentTeamScore;
    private Integer teamScore;
    private Integer gameId;

    public GameModel(int gameId, String teamName, String opponentTeamName, String date,
            Integer opponentTeamScore, Integer teamScore) {
        this.teamName = teamName;
        this.opponentTeamName = opponentTeamName;
        this.date = date;
        this.opponentTeamScore = opponentTeamScore;
        this.teamScore = teamScore;
        this.gameId = gameId;
    }

    public GameModel(String teamName, String opponentTeamName, String date,
            Integer opponentTeamScore, Integer teamScore) {
        this.teamName = teamName;
        this.opponentTeamName = opponentTeamName;
        this.date = date;
        this.opponentTeamScore = opponentTeamScore;
        this.teamScore = teamScore;
    }

    public GameModel() {
    }

    public GameModel(String teamName, String opponentTeamName, String date) {
        this.teamName = teamName;
        this.opponentTeamName = opponentTeamName;
        this.date = date;
    }

    public GameModel(Integer GameID, String teamName, String opponentTeamName, String date) {
        this.teamName = teamName;
        this.opponentTeamName = opponentTeamName;
        this.date = date;
        this.gameId = GameID;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getOpponentTeamName() {
        return opponentTeamName;
    }

    public String getDate() {
        return date;
    }

    public Integer getOpponentTeamScore() {
        return opponentTeamScore;
    }

    public Integer getTeamScore() {
        return teamScore;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setOpponentTeamName(String opponentTeamName) {
        this.opponentTeamName = opponentTeamName;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setOpponentTeamScore(Integer opponentTeamScore) {
        this.opponentTeamScore = opponentTeamScore;
    }

    public void setTeamScore(Integer teamScore) {
        this.teamScore = teamScore;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    @Override
    public String toString() {
        return "GameResultsModel{" +
                "teamName='" + teamName + '\'' +
                ", opponentTeamName='" + opponentTeamName + '\'' +
                ", date='" + date + '\'' +
                ", opponentTeamScore=" + opponentTeamScore +
                ", teamScore=" + teamScore +
                '}';
    }

    public static ArrayList<GameModel> getAllPlayedGameModel() {
        ArrayList<GameModel> gameResults = new ArrayList<>();

        String query = "SELECT " +
                "g.TeamName, " +
                "g.OpponentTeamName, " +
                "g.OpponentTeamScore, " +
                "g.Date, " +
                "g.TeamScore " +
                "FROM ( " +
                "SELECT " +
                "GameID, " +
                "TeamName, " +
                "Date, " +
                "OpponentTeamName, " +
                "OpponentTeamScore, " +
                "(SELECT SUM(points) FROM Stats WHERE GameID = g.GameID) AS TeamScore " +
                "FROM Game g " +
                "WHERE matchPlayed = TRUE " +
                ") g";

        try {
            Connection connection = new DatabaseConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String teamName = resultSet.getString("TeamName");
                String opponentTeam = resultSet.getString("OpponentTeamName");
                int opponentTeamScore = resultSet.getInt("OpponentTeamScore");
                String date = resultSet.getString("Date");
                int teamScore = resultSet.getInt("TeamScore");

                GameModel gameResult = new GameModel(teamName, opponentTeam, date, opponentTeamScore,
                        teamScore);
                gameResults.add(gameResult);
            }
            System.out.println(gameResults);

            System.out.println("Successfully fetched game results from database.");

            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace(); // Replace with proper logging
        }

        return gameResults;
    }

    public static ArrayList<GameModel> getAllScheduledGameModel() {
        ArrayList<GameModel> gameResults = new ArrayList<>();
        String query = "SELECT " +
                "g.GameID, " +
                "g.TeamName, " +
                "g.OpponentTeamName, " +
                "g.Date, " +
                "g.Time " +
                "FROM Game g " +
                "WHERE matchPlayed = FALSE";

        try {
            Connection connection = new DatabaseConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String teamName = resultSet.getString("TeamName");
                String opponentTeam = resultSet.getString("OpponentTeamName");
                String date = resultSet.getString("Date");
                Integer gameId = resultSet.getInt("GameID");

                GameModel gameResult = new GameModel(gameId, teamName, opponentTeam, date);
                gameResults.add(gameResult);
            }
            System.out.println(gameResults);

            System.out.println("Successfully fetched game results from database.");

            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace(); // Replace with proper logging
        }

        return gameResults;

    }

    public static GameModel getGame(Integer gameId) {
        String query = "SELECT * FROM Game WHERE gameID = ?";
        System.out.println(gameId);
        try {
            Connection connection = new DatabaseConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, gameId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String teamName = resultSet.getString("TeamName");
                String opponentTeamName = resultSet.getString("OpponentTeamName");
                String date = resultSet.getString("Date");

                return new GameModel(gameId, teamName, opponentTeamName, date);
            }
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching game");

        }
        return new GameModel();

    }

}
