package com.sahil.basketballapp.database;

import java.io.File;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private Connection connection;

    public Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("org.sqlite.JDBC");
                String dbFilePath = "./basketballapp.db";
                File dbFile = new File(dbFilePath);

                if (!dbFile.exists()) {
                    File parentDir = dbFile.getParentFile();
                    if (!parentDir.exists()) {
                        parentDir.mkdirs();
                    }

                    // Create the database file
                    connection = DriverManager.getConnection("jdbc:sqlite:" + dbFilePath);
                    createTables(); // Call the createTables method to create tables
                } else {
                    connection = DriverManager.getConnection("jdbc:sqlite:" + dbFilePath);
                }
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return connection;
    }

    public void createTables() {
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();

            // Create Team Table
            String createTeamTable = "CREATE TABLE IF NOT EXISTS Team (" +
                    "teamName TEXT PRIMARY KEY," +
                    "photoUuid TEXT" +
                    ")";
            statement.executeUpdate(createTeamTable);

            // Create Game Table
            String createGameTable = "CREATE TABLE IF NOT EXISTS Game (" +
                    "gameId INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "teamName TEXT REFERENCES Team(teamName)," +
                    "date DATE," +
                    "time TIME," +
                    "opponentTeamName TEXT," +
                    "matchPlayed BOOLEAN," +
                    "opponentTeamScore INTEGER" +
                    ")";
            statement.executeUpdate(createGameTable);

            // Create Player Table
            String createPlayerTable = "CREATE TABLE IF NOT EXISTS Player (" +
                    "playerName TEXT PRIMARY KEY," +
                    "age INTEGER," +
                    "height REAL," +
                    "weight REAL," +
                    "position TEXT," +
                    "photoUuid TEXT," +
                    "teamName TEXT REFERENCES Team(teamName)" +
                    ")";
            statement.executeUpdate(createPlayerTable);

            // Create Stats Table
            String createStatsTable = "CREATE TABLE IF NOT EXISTS Stats (" +
                    "playerName TEXT REFERENCES Player(playerName)," +
                    "gameId INTEGER REFERENCES Game(gameId)," +
                    "points INTEGER," +
                    "assists INTEGER," +
                    "rebounds INTEGER," +
                    "fgm INTEGER," +
                    "fga INTEGER," +
                    "steals INTEGER," +
                    "turnovers INTEGER," +
                    "blocks INTEGER," +
                    "PRIMARY KEY (playerName, gameId)" +
                    ")";
            statement.executeUpdate(createStatsTable);

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}