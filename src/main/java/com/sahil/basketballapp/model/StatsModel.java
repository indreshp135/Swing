package com.sahil.basketballapp.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    public class PointsModel {
        private int points;
        private Date date;

        public PointsModel(Date date, int points) {
            this.points = points;
            this.date = date;
        }

        public int getScore() {
            return points;
        }

        public Date getDate() {
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

    @Override
    public String toString() {
        return "StatsModel [assists=" + assists + ", blocks=" + blocks + ", fga=" + fga + ", fgm=" + fgm + ", gameId="
                + gameId + ", playerName=" + playerName + ", points=" + points + ", rebounds=" + rebounds
                + ", steals=" + steals + ", turnovers=" + turnovers + "]";
    }

    // Add Stats to Database
    public void save() {
        Connection connection = null;
        try {
            connection = new DatabaseConnection().getConnection();
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
        } finally {
            try {
                if (connection != null) {
                    ;
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<PointsModel> getPlayerPointsModel(String playerName) {
        List<PointsModel> playerPointsModelList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = new DatabaseConnection().getConnection();
            String query = "SELECT s.points, g.date FROM Stats s INNER JOIN Game g ON s.gameId = g.gameId WHERE s.playerName = ? ORDER BY g.date ASC";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, playerName);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int points = resultSet.getInt("points");
                String date = resultSet.getString("date");
                LocalDate localDate = LocalDate.parse(date);
                Date dateFinal = Date.valueOf(localDate);
                PointsModel playerPointsModel = new PointsModel(dateFinal, points);
                playerPointsModelList.add(playerPointsModel);
            }

            preparedStatement.close();
            return playerPointsModelList;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                    ;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static List<StatsModel> getGameStats(int gameId) {
        List<StatsModel> gameStatsList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = new DatabaseConnection().getConnection();
            String query = "SELECT * FROM Stats WHERE gameId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, gameId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String playerName = resultSet.getString("playerName");
                int points = resultSet.getInt("points");
                int assists = resultSet.getInt("assists");
                int rebounds = resultSet.getInt("rebounds");
                int fgm = resultSet.getInt("fgm");
                int fga = resultSet.getInt("fga");
                int steals = resultSet.getInt("steals");
                int turnovers = resultSet.getInt("turnovers");
                int blocks = resultSet.getInt("blocks");
                StatsModel gameStats = new StatsModel(playerName, gameId, points, assists, rebounds, fgm, fga, steals,
                        turnovers, blocks);
                gameStatsList.add(gameStats);
            }

            preparedStatement.close();
            return gameStatsList;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                    ;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<PointsModel> getTeamStats(String teamName) {
        List<PointsModel> teamStatsList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = new DatabaseConnection().getConnection();
            String query = "SELECT SUM(s.points) as points, g.date FROM Stats s INNER JOIN Game g ON s.gameId = g.gameId INNER JOIN Player p ON s.playerName = p.playerName WHERE p.teamName = ? GROUP BY g.gameId ORDER BY g.date ASC";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, teamName);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                System.out.println(resultSet.getInt("points") + " " + resultSet.getString("date"));
                int points = resultSet.getInt("points");
                String date = resultSet.getString("date");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate localDate = LocalDate.parse(date, formatter);
                Date dateFinal = localDate != null ? Date.valueOf(localDate) : null;
                PointsModel teamPointsModel = new PointsModel(dateFinal, points);
                teamStatsList.add(teamPointsModel);
            }

            preparedStatement.close();
            return teamStatsList;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                    ;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
