package com.sahil.basketballapp.model;

import java.sql.Connection;
import java.util.List;

import com.sahil.basketballapp.database.DatabaseConnection;

public class PlayerInfoModel {
    String name;
    int age;
    double height;
    int weight;
    String position;
    String teamName;
    String photouuid;

    public PlayerInfoModel(String name, int age, double height, int weight, String position) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.position = position;
    }

    public PlayerInfoModel(String name, int age, double height, int weight, String position, String photouuid) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.position = position;
        this.photouuid = photouuid;
    }

    public PlayerInfoModel(String name, int age, double height, int weight, String position, String photouuid,
            String teamName) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.position = position;
        this.teamName = teamName;
        this.photouuid = photouuid;
    }

    public PlayerInfoModel() {
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public String getPosition() {
        return position;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setPhotouuid(String photouuid) {
        this.photouuid = photouuid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }
        this.age = age;
    }

    public void setHeight(double height) {
        if (height < 0) {
            throw new IllegalArgumentException("Height cannot be negative");
        }
        this.height = height;
    }

    public void setWeight(int weight) {
        if (weight < 0) {
            throw new IllegalArgumentException("Weight cannot be negative");
        }
        this.weight = weight;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getPhotouuid() {
        return photouuid;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PlayerInfo{");
        sb.append("name='").append(name).append('\'');
        sb.append(", age=").append(age);
        sb.append(", height=").append(height);
        sb.append(", weight=").append(weight);
        sb.append(", position='").append(position).append('\'');
        sb.append(", teamName='").append(teamName).append('\'');
        sb.append(", photouuid='").append(photouuid).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public void add() {
        Connection connection = null;
        try {
            connection = new DatabaseConnection().getConnection();
            String query = "INSERT INTO Player (playerName, age, height, weight, position, teamName, photoUuid) VALUES (?, ?, ?, ?, ?, ?, ?)";
            var preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setDouble(3, height);
            preparedStatement.setInt(4, weight);
            preparedStatement.setString(5, position);
            preparedStatement.setString(6, teamName);
            preparedStatement.setString(7, photouuid);
            preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (Exception e) {
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
    }

    public static List<String> getPlayersByTeamName(String teamName) {
        List<String> playerNames = new java.util.ArrayList<>();
        Connection connection = null;

        try {
            connection = new DatabaseConnection().getConnection();
            String query = "SELECT playerName FROM Player WHERE teamName = ?";
            var preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, teamName);
            System.out.println(preparedStatement);
            var resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                playerNames.add(resultSet.getString("playerName"));
            }
            preparedStatement.close();
        } catch (Exception e) {
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

        return playerNames;
    }

    // get player info by name
    public static PlayerInfoModel getPlayerInfoByName(String playerName) {
        PlayerInfoModel playerInfoModel = null;
        Connection connection = null;

        try {
            connection = new DatabaseConnection().getConnection();
            String query = "SELECT * FROM Player WHERE playerName = ?";
            var preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, playerName);
            System.out.println(preparedStatement);
            var resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("playerName");
                int age = resultSet.getInt("age");
                double height = resultSet.getDouble("height");
                int weight = resultSet.getInt("weight");
                String position = resultSet.getString("position");
                String teamName = resultSet.getString("teamName");
                String photouuid = resultSet.getString("photoUuid");
                playerInfoModel = new PlayerInfoModel(name, age, height, weight, position, photouuid, teamName);
            }
            preparedStatement.close();
            return playerInfoModel;
        } catch (Exception e) {
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

        return playerInfoModel;
    }

    // Get all players names
    public static List<String> getAllPlayerNames(){
        Connection connection = null;
        List<String> playerNames = new java.util.ArrayList<>();

        try {
            connection = new DatabaseConnection().getConnection();
            String query = "SELECT playerName FROM Player";
            var preparedStatement = connection.prepareStatement(query);
            var resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                playerNames.add(resultSet.getString("playerName"));
            }
            preparedStatement.close();
            return playerNames;
        } catch (Exception e) {
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

        return playerNames;
    }
}
