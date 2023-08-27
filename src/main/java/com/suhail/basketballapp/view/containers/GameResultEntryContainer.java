package com.suhail.basketballapp.view.containers;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import com.suhail.basketballapp.view.components.AddResultTableComponent;

public class GameResultEntryContainer extends JPanel {

    private JLabel vsLabel;
    private JLabel opponentScoreLabel;
    private JSpinner opponentScoreSpinner;
    private AddResultTableComponent resultTableComponent;
    private JButton saveButton;

    public GameResultEntryContainer(String teamName, String opponentTeamName, List<String> teamPlayers) {
        setLayout(new BorderLayout());
        JPanel panel = new JPanel(new GridBagLayout());
        JPanel bottomPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding
        gbc.insets = new Insets(5, 10, 5, 10); // Add spacing
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        vsLabel = new JLabel(teamName + " vs " + opponentTeamName, SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        bottomPanel.add(vsLabel, gbc);

        JPanel opponentScorePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        opponentScoreLabel = new JLabel("Opponent Score:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        opponentScorePanel.add(opponentScoreLabel);

        opponentScoreSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 999, 1));
        JFormattedTextField opponentScoreTextField = ((JSpinner.DefaultEditor) opponentScoreSpinner.getEditor())
                .getTextField();
        opponentScoreTextField.setColumns(15);
        gbc.anchor = GridBagConstraints.CENTER;
        opponentScorePanel.add(opponentScoreSpinner);
        bottomPanel.add(opponentScorePanel, gbc);

        resultTableComponent = new AddResultTableComponent(teamPlayers);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        bottomPanel.add(resultTableComponent, gbc);

        saveButton = new JButton("Save");
        JPanel saveButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        saveButton.setMaximumSize(new Dimension(200, 40));
        saveButtonPanel.add(saveButton);
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        bottomPanel.add(saveButtonPanel, gbc);

        panel.add(bottomPanel);

        add(panel, BorderLayout.CENTER);
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JSpinner getOpponentScoreSpinner() {
        return opponentScoreSpinner;
    }

    public AddResultTableComponent getResultTableComponent() {
        return resultTableComponent;
    }

    public JPanel getPanel() {
        return this;
    }

}
