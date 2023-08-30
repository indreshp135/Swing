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

            // Create two teams Spartans and Wolverines
            String insertTeams = "INSERT INTO Team (teamName, photoUuid) VALUES ('Spartans', 'spartans.png'), ('Wolverines', 'wolverines.png')";
            statement.executeUpdate(insertTeams);

            // "Point Guard", "Shooting Guard", "Small Forward", "Power Forward", "Center")

            // Insert players into Spartans - name actual players
            String insertSpartansPlayers = "INSERT INTO Player (playerName, age, height, weight, position, photoUuid, teamName) VALUES "
                    +
                    "('Cassius Winston', 22, 6.1, 185, 'Point Guard', 'nopic.png', 'Spartans'), " +
                    "('Joshua Langford', 22, 6.5, 210, 'Shooting Guard', 'nopic.png', 'Spartans'), " +
                    "('Aaron Henry', 20, 6.6, 210, 'Small Forward', 'nopic.png', 'Spartans'), " +
                    "('Xavier Tillman', 21, 6.8, 245, 'Power Forward', 'nopic.png', 'Spartans'), " +
                    "('Marcus Bingham Jr.', 20, 6.11, 225, 'Center', 'nopic.png', 'Spartans')";

            statement.executeUpdate(insertSpartansPlayers);

            // Insert players into Wolverines - name actual players
            String insertWolverinesPlayers = "INSERT INTO Player (playerName, age, height, weight, position, photoUuid, teamName) VALUES "
                    +
                    "('Zavier Simpson', 22, 6.0, 190, 'Point Guard', 'nopic.png', 'Wolverines'), " +
                    "('Eli Brooks', 21, 6.1, 185, 'Shooting Guard', 'nopic.png', 'Wolverines'), " +
                    "('Isaiah Livers', 21, 6.7, 230, 'Small Forward', 'nopic.png', 'Wolverines'), " +
                    "('Brandon Johns Jr.', 20, 6.8, 240, 'Power Forward', 'nopic.png', 'Wolverines'), " +
                    "('Jon Teske', 22, 7.1, 260, 'Center', 'nopic.png', 'Wolverines')";
            statement.executeUpdate(insertWolverinesPlayers);

            // Add 3 played games
            String insertPlayedGames = "INSERT INTO Game (teamName, date, time, opponentTeamName, matchPlayed, opponentTeamScore) VALUES "
                    +
                    "('Spartans', '2023-01-01', '12:00:00', 'Lakers', 1, 109), " +
                    "('Wolverines', '2023-01-02', '12:00:00', 'Clippers', 1, 96), " +
                    "('Spartans', '2023-01-03', '12:00:00', 'Warriors', 1, 87)";

            statement.executeUpdate(insertPlayedGames);

            // Schedule 3 games with Lakers, Clippers and Warriors
            String insertSpartansGames = "INSERT INTO Game (teamName, date, time, opponentTeamName, matchPlayed, opponentTeamScore) VALUES " +
                    "('Spartans', '2023-01-04', '12:00:00', 'Clippers', 0, 0), " +
                    "('Wolverines', '2023-01-05', '12:00:00', 'Warriors', 0, 0), " +
                    "('Wolverines', '2023-01-06', '12:00:00', 'Lakers', 0, 0)";

            statement.executeUpdate(insertSpartansGames);

            // Insert stats for Spartans vs Lakers
            String insertSpartansLakersStats = "INSERT INTO Stats (playerName, gameId, points, assists, rebounds, fgm, fga, steals, turnovers, blocks) VALUES " +
                    "('Cassius Winston', 1, 16, 10, 5, 8, 10, 2, 3, 1), " +
                    "('Joshua Langford', 1, 23, 10, 5, 8, 10, 2, 3, 1), " +
                    "('Aaron Henry', 1, 22, 10, 5, 8, 10, 2, 3, 1), " +
                    "('Xavier Tillman', 1, 19, 10, 5, 8, 10, 2, 3, 1), " +
                    "('Marcus Bingham Jr.', 1, 6, 10, 5, 8, 10, 2, 3, 1)";

            statement.executeUpdate(insertSpartansLakersStats);

            // Insert stats for Wolverines vs Clippers
            String insertWolverinesClippersStats = "INSERT INTO Stats (playerName, gameId, points, assists, rebounds, fgm, fga, steals, turnovers, blocks) VALUES " +
                    "('Zavier Simpson', 2, 18, 10, 5, 8, 10, 2, 3, 1), " +
                    "('Eli Brooks', 2, 21, 10, 5, 8, 10, 2, 3, 1), " +
                    "('Isaiah Livers', 2, 17, 10, 5, 8, 10, 2, 3, 1), " +
                    "('Brandon Johns Jr.', 2, 25, 10, 5, 8, 10, 2, 3, 1), " +
                    "('Jon Teske', 2, 16, 10, 5, 8, 10, 2, 3, 1)";

            statement.executeUpdate(insertWolverinesClippersStats);

            // Insert stats for Spartans vs Warriors
            String insertSpartansWarriorsStats = "INSERT INTO Stats (playerName, gameId, points, assists, rebounds, fgm, fga, steals, turnovers, blocks) VALUES " +
                    "('Cassius Winston', 3, 15, 10, 5, 8, 10, 2, 3, 1), " +
                    "('Joshua Langford', 3, 23, 10, 5, 8, 10, 2, 3, 1), " +
                    "('Aaron Henry', 3, 21, 10, 5, 8, 10, 2, 3, 1), " +
                    "('Xavier Tillman', 3, 27, 10, 5, 8, 10, 2, 3, 1), " +
                    "('Marcus Bingham Jr.', 3, 19, 10, 5, 8, 10, 2, 3, 1)";
            
            statement.executeUpdate(insertSpartansWarriorsStats);

            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}