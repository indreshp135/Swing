package com.sahil.basketballapp.controller;

import java.util.List;

import javax.swing.JOptionPane;

import com.sahil.basketballapp.model.PlayerInfoModel;
import com.sahil.basketballapp.model.TeamModel;
import com.sahil.basketballapp.view.containers.AddNewPlayerContainer;

public class AddNewPlayerController {
    private AddNewPlayerContainer addNewPlayerContainer;

    public AddNewPlayerController() {
        List<String> teamNames = TeamModel.getAllTeamNames();
        addNewPlayerContainer = new AddNewPlayerContainer(teamNames);

        addNewPlayerContainer.getSaveButton().addActionListener(e -> {
            PlayerInfoModel playerInfoModel = addNewPlayerContainer.getPlayerInfoModel();
            if (!validate(playerInfoModel)) {
                return;
            }
            try {
                playerInfoModel.add();
                addNewPlayerContainer.saveImage();
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, "Player already exists");
            }
            JOptionPane.showMessageDialog(null, "Player added successfully");
        });
    }

    private boolean validate(PlayerInfoModel playerInfoModel) {
        // check if the player name is empty
        if (playerInfoModel.getName().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Player name cannot be empty");
            return false;
        }
        return true;
    }

    public AddNewPlayerContainer getViewPanel() {
        return addNewPlayerContainer;
    }
}
