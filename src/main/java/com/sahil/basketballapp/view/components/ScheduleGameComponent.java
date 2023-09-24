package com.sahil.basketballapp.view.components;

import com.sahil.basketballapp.model.ScheduleGameModel;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.List;

public class ScheduleGameComponent {
    private JTextField opponentTeamNameField;
    private JComboBox<String> teamComboBox;
    private JDateChooser dateChooser;
    private JSpinner timeSpinner;
    private JButton scheduleButton;

    public ScheduleGameComponent(List<String> teamNames) {
        opponentTeamNameField = new JTextField();
        teamComboBox = new JComboBox<>(
                teamNames.toArray(new String[0])
        );
        dateChooser = new JDateChooser();
        timeSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm:ss");
        timeSpinner.setEditor(timeEditor);

        scheduleButton = new JButton("Schedule Game");
    }

    public JPanel getPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding
        gbc.insets = new Insets(5, 10, 5, 10); // Add spacing

        JLabel headingLabel = new JLabel("Schedule New Game");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headingLabel.setHorizontalAlignment(SwingConstants.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(headingLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Opponent Team Name:"), gbc);

        gbc.gridx = 1;
        opponentTeamNameField.setPreferredSize(new Dimension(200, 30));
        formPanel.add(opponentTeamNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Team Name:"), gbc);

        gbc.gridx = 1;
        teamComboBox.setPreferredSize(new Dimension(200, 30));
        formPanel.add(teamComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Date:"), gbc);

        gbc.gridx = 1;
        dateChooser.setPreferredSize(new Dimension(200, 30));
        formPanel.add(dateChooser, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(new JLabel("Time:"), gbc);

        gbc.gridx = 1;
        timeSpinner.setPreferredSize(new Dimension(200, 30));
        formPanel.add(timeSpinner, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        scheduleButton.setPreferredSize(new Dimension(200, 40));
        // scheduleButton.setFont(new Font("Arial", Font.BOLD, 16));
        scheduleButton.setBackground(Color.BLUE);
        scheduleButton.setForeground(Color.BLACK);
        formPanel.add(scheduleButton, gbc);


        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.NORTH);

        return panel;
    }

    public JButton getScheduleButton() {
        return scheduleButton;
    }


    public ScheduleGameModel getFormData() {
        ScheduleGameModel model = new ScheduleGameModel();
        model.setOpponentTeamName(opponentTeamNameField.getText());
        model.setSelectedTeamName((String) teamComboBox.getSelectedItem());
        model.setDate(dateChooser.getDate());
        model.setTime((Date) timeSpinner.getValue());
        return model;
    }
}
