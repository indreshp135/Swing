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
        List<String> teamNames = new ArrayList<>(

        );
        // teamNames.add("Team 1");
        try {
            Connection connection = new DatabaseConnection().getConnection();
            PreparedStatement statement = connection.prepareStatement(toSelectAllTeamNamesQuery());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String teamName = resultSet.getString("teamName");
                teamNames.add(teamName);
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(teamNames);
        return teamNames;
    }
}
