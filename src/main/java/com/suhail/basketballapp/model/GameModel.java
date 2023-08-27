package com.suhail.basketballapp.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.suhail.basketballapp.database.DatabaseConnection;

public class GameModel {
    private String teamName;
    private String opponentTeamName;
    private String date;
    private Integer opponentTeamScore;
    private Integer teamScore;

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

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()) {

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

        } catch (SQLException e) {
            e.printStackTrace(); // Replace with proper logging
        }

        return gameResults;
    }

    public static ArrayList<GameModel> getAllScheduledGameModel() {
        ArrayList<GameModel> gameResults = new ArrayList<>();
        String query = "SELECT " +
                "g.TeamName, " +
                "g.OpponentTeamName, " +
                "g.Date, " +
                "g.Time " +
                "FROM Game g " +
                "WHERE matchPlayed = FALSE";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String teamName = resultSet.getString("TeamName");
                String opponentTeam = resultSet.getString("OpponentTeamName");
                String date = resultSet.getString("Date");

                GameModel gameResult = new GameModel(teamName, opponentTeam, date);
                gameResults.add(gameResult);
            }
            System.out.println(gameResults);

            System.out.println("Successfully fetched game results from database.");

        } catch (SQLException e) {
            e.printStackTrace(); // Replace with proper logging
        }

        return gameResults;

    }
}
