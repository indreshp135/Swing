package com.sahil.basketballapp.view.components;

import javax.swing.*;

import com.sahil.basketballapp.model.PlayerInfoModel;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class PlayerFormComponent {
    private JTextField nameField;
    private JSpinner ageSpinner;
    private JSpinner heightSpinner;
    private JSpinner weightSpinner;
    private JComboBox<String> positionComboBox;

    private static final List<String> BASKETBALL_POSITIONS = Arrays.asList(
            "Point Guard", "Shooting Guard", "Small Forward", "Power Forward", "Center");

    public PlayerFormComponent() {
        nameField = new JTextField();
        ageSpinner = new JSpinner(new SpinnerNumberModel(10, 0, 99, 1));
        heightSpinner = new JSpinner(new SpinnerNumberModel(6.0, 0.0, 10.0, 0.1));
        weightSpinner = new JSpinner(new SpinnerNumberModel(70, 0, 500, 1));
        positionComboBox = new JComboBox<>(BASKETBALL_POSITIONS.toArray(new String[0]));
    }

    public JPanel getPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        gbc.insets = new Insets(5, 10, 5, 10);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Name:"), gbc);

        gbc.gridx = 1;
        nameField.setPreferredSize(new Dimension(200, 30));
        formPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Age:"), gbc);

        gbc.gridx = 1;
        JFormattedTextField ageTextField = ((JSpinner.DefaultEditor) ageSpinner.getEditor()).getTextField();
        ageTextField.setColumns(15);
        formPanel.add(ageSpinner, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Height (ft):"), gbc);

        gbc.gridx = 1;
        JFormattedTextField heightTextField = ((JSpinner.DefaultEditor) heightSpinner.getEditor()).getTextField();
        heightTextField.setColumns(15);
        formPanel.add(heightSpinner, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(new JLabel("Weight (kg):"), gbc);

        gbc.gridx = 1;
        JFormattedTextField weightTextField = ((JSpinner.DefaultEditor) weightSpinner.getEditor()).getTextField();
        weightTextField.setColumns(15);
        formPanel.add(weightSpinner, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(new JLabel("Position:"), gbc);

        gbc.gridx = 1;
        positionComboBox.setPreferredSize(new Dimension(200, 30));
        formPanel.add(positionComboBox, gbc);

        panel.add(formPanel, BorderLayout.CENTER);

        return panel;
    }

    public PlayerInfoModel getFormModel() {
        String name = nameField.getText();
        int age = (int) ageSpinner.getValue();
        double height = (double) heightSpinner.getValue();
        int weight = (int) weightSpinner.getValue();
        String position = (String) positionComboBox.getSelectedItem();

        return new PlayerInfoModel(name, age, height, weight, position);
    }
}
