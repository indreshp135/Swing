package com.suhail.basketballapp.controller;

import com.suhail.basketballapp.model.ScheduleGameModel;
import com.suhail.basketballapp.model.TeamModel;
import com.suhail.basketballapp.view.containers.ScheduleGameContainer;

import javax.swing.*;
import java.util.List;

public class ScheduleGameController {
    private ScheduleGameContainer container;

    public ScheduleGameController() {
        List<String> teamNames = TeamModel.getAllTeamNames();
        container = new ScheduleGameContainer(teamNames);

        container.getScheduleButton().addActionListener(e -> {
            ScheduleGameModel formData = container.getFormData();
            if (formDataIsValid(formData)) {
                insertGameData(formData);
            }
        });
    }

    public JPanel getViewPanel() {
        return container.getPanel();
    }

    private boolean formDataIsValid(ScheduleGameModel formData) {
        // Check any empty fields
        if (formData.getSelectedTeamName().isEmpty() || formData.getOpponentTeamName().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill all the fields");
            return false;
        }
        if (formData.getSelectedTeamName().equals(formData.getOpponentTeamName())) {
            JOptionPane.showMessageDialog(null, "Home team and away team cannot be the same");
            return false;
        }
        if (formData.getDate().before(new java.util.Date())) {
            JOptionPane.showMessageDialog(null, "Date cannot be in the past");
            return false;
        }
        return true;
    }

    private void insertGameData(ScheduleGameModel formData) {
        // Implement your database insertion logic here
        formData.postGameDetails();
        JOptionPane.showMessageDialog(null, "Game scheduled successfully");
    }
}
