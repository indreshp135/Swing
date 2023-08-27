package com.suhail.basketballapp.controller;

import java.util.List;

import javax.swing.JOptionPane;

import com.suhail.basketballapp.model.PlayerInfoModel;
import com.suhail.basketballapp.model.TeamModel;
import com.suhail.basketballapp.view.containers.AddNewPlayerContainer;

public class AddNewPlayerController {
    private AddNewPlayerContainer addNewPlayerContainer;

    public AddNewPlayerController() {
        List<String> teamNames = TeamModel.getAllTeamNames();
        addNewPlayerContainer = new AddNewPlayerContainer(teamNames);

        addNewPlayerContainer.getSaveButton().addActionListener(e -> {
            PlayerInfoModel playerInfoModel = addNewPlayerContainer.getPlayerInfoModel();
            playerInfoModel.add();
            addNewPlayerContainer.saveImage();
            // show success message
            JOptionPane.showMessageDialog(null, "Player added successfully");
        });
    }

    public AddNewPlayerContainer getViewPanel() {
        return addNewPlayerContainer;
    }
}
