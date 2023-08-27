package com.suhail.basketballapp.view.containers;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import com.suhail.basketballapp.model.PlayerInfoModel;
import com.suhail.basketballapp.view.components.PhotoUploaderWithFixedSizeDisplay;
import com.suhail.basketballapp.view.components.PlayerFormComponent;

public class AddNewPlayerContainer extends JPanel {
    private JComboBox<String> teamComboBox;
    private PlayerFormComponent playerFormComponent;
    private PhotoUploaderWithFixedSizeDisplay photoUploaderWithFixedSizeDisplay;
    private JButton saveButton;

    public AddNewPlayerContainer(List<String> teamNames) {
        setLayout(new BorderLayout());
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding
        gbc.insets = new Insets(5, 10, 5, 10); // Add spacing
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        teamComboBox = new JComboBox<>(teamNames.toArray(new String[0]));
        teamComboBox.setPreferredSize(new Dimension(200, 30));
        playerFormComponent = new PlayerFormComponent();
        photoUploaderWithFixedSizeDisplay = new PhotoUploaderWithFixedSizeDisplay();
        saveButton = new JButton("Save");

        JLabel headingLabel = new JLabel("Add New Player");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headingLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel topPanel = new JPanel(new GridBagLayout());

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 2;
        topPanel.add(headingLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        topPanel.add(new JLabel("Select Team:"), gbc);
        gbc.gridx = 1;
        topPanel.add(teamComboBox, gbc);
        topPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, topPanel.getPreferredSize().height));

        JPanel bottomPanel = new JPanel(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        bottomPanel.add(topPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        bottomPanel.add(playerFormComponent.getPanel(), gbc);

        gbc.gridx = 1;

        bottomPanel.add(photoUploaderWithFixedSizeDisplay, gbc);

        JPanel buttonPanel = new JPanel();
        saveButton.setPreferredSize(new Dimension(100, 40));
        saveButton.setBackground(Color.GREEN);
        buttonPanel.add(saveButton);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 0, 0);
        bottomPanel.add(buttonPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(bottomPanel, gbc);

        add(panel, BorderLayout.CENTER);
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public PlayerInfoModel getPlayerInfoModel() {
        PlayerInfoModel playerInfoModel = playerFormComponent.getFormModel();
        playerInfoModel.setTeamName(teamComboBox.getSelectedItem().toString());
        playerInfoModel.setPhotouuid(photoUploaderWithFixedSizeDisplay.getPhotoUUID());
        return playerInfoModel;
    }

    public void saveImage() {
        photoUploaderWithFixedSizeDisplay.saveImage();
    }
}
