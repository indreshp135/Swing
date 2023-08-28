package com.sahil.basketballapp.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sahil.basketballapp.database.DatabaseConnection;

public class TeamModel {
    private String teamName;
    private String photoUuid;

    public TeamModel(String teamName, String photoUuid) {
        this.teamName = teamName;
        this.photoUuid = photoUuid;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getPhotoUuid() {
        return photoUuid;
    }

    @Override
    public String toString() {
        return "TeamModel{" +
                "teamName='" + teamName + '\'' +
                ", photoUuid='" + photoUuid + '\'' +
                '}';
    }

    public String toInsertQuery() {
        return "INSERT INTO team (teamName, photoUuid) VALUES ('" + teamName + "', '" + photoUuid + "');";
    }

    public static String toSelectAllQuery() {
        return "SELECT * FROM team;";
    }

    public static String toSelectAllTeamNamesQuery() {
        return "SELECT teamName FROM team;";
    }

    public static List<String> getAllTeamNames() {
        List<String> teamNames = new ArrayList<>();
        Connection connection = null;
        try {
            connection = new DatabaseConnection().getConnection();
            PreparedStatement statement = connection.prepareStatement(toSelectAllTeamNamesQuery());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String teamName = resultSet.getString("teamName");
                teamNames.add(teamName);
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(teamNames);
        return teamNames;
    }

    // get team info by name
    public TeamModel getTeamInfoByName(String teamName) {
        TeamModel teamModel = null;
        Connection connection = null;

        try {
            connection = new DatabaseConnection().getConnection();
            String query = "SELECT * FROM team WHERE teamName = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, teamName);
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String teamName1 = resultSet.getString("teamName");
                String photoUuid = resultSet.getString("photoUuid");
                teamModel = new TeamModel(teamName1, photoUuid);
            }
            preparedStatement.close();
            return teamModel;
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
