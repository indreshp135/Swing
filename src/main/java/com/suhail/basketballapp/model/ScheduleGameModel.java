package com.suhail.basketballapp.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;

import com.suhail.basketballapp.database.DatabaseConnection;

public class ScheduleGameModel {
    private String opponentTeamName;
    private String selectedTeamName; // For dropdown
    private Date date;
    private Date time;

    public ScheduleGameModel(String opponentTeamName, String selectedTeamName, Date date, Date time) {
        this.opponentTeamName = opponentTeamName;
        this.selectedTeamName = selectedTeamName;
        this.date = date;
        this.time = time;
    }

    public ScheduleGameModel() {
    }

    public String getOpponentTeamName() {
        return opponentTeamName;
    }

    public void setOpponentTeamName(String opponentTeamName) {
        this.opponentTeamName = opponentTeamName;
    }

    public String getSelectedTeamName() {
        return selectedTeamName;
    }

    public void setSelectedTeamName(String selectedTeamName) {
        this.selectedTeamName = selectedTeamName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public java.sql.Time getTime() {
        return new java.sql.Time(time.getTime());
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "ScheduleGameModel{" +
                "opponentTeamName='" + opponentTeamName + '\'' +
                ", selectedTeamName='" + selectedTeamName + '\'' +
                ", date=" + date +
                ", time=" + time +
                '}';
    }

    public String toInsertQuery() {
        return "INSERT INTO Game (teamName, date, time, opponentTeamName, matchPlayed) VALUES ('" +
                selectedTeamName + "', '" +
                new java.sql.Date(date.getTime()) + "', '" +
                new java.sql.Time(time.getTime()) + "', '" +
                opponentTeamName + "', " +
                "0" +
                ")";
    }

    public void postGameDetails() {
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(toInsertQuery());
            System.out.println(toInsertQuery());
            statement.execute();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
