package com.sahil.basketballapp.view.containers;

import javax.swing.*;
import javax.swing.border.Border;

import com.sahil.basketballapp.model.StatsModel;
import com.sahil.basketballapp.view.components.ScoreComponent;

import java.awt.*;
import java.util.List;

public class GameResultEntryContainer extends JPanel {

    private JPanel vsPanel;
    private JLabel opponentScoreLabel;
    private JSpinner opponentScoreSpinner;
    private ScoreComponent resultTableComponent;
    private JButton saveButton;


    private static final Font TEAM_NAME_FONT = new Font("Arial", Font.BOLD, 16);
    private static final Color BLUE_COLOR = Color.BLUE;
    private static final Color RED_COLOR = Color.RED;
    private static final Color DIMMED_TEXT_COLOR = Color.GRAY;
    private static final Border TEAM_PANEL_BORDER = BorderFactory.createEmptyBorder(10, 10, 0, 10);

    private JPanel createTeamNamePanel(String teamName, String opponentTeamName) {
        JPanel teamNamePanel = new JPanel(new GridLayout(1, 3));
        teamNamePanel.setOpaque(false);
        teamNamePanel.setBorder(TEAM_PANEL_BORDER);

        JLabel teamNameLabel = new JLabel(teamName, SwingConstants.RIGHT);
        teamNameLabel.setFont(TEAM_NAME_FONT);
        teamNameLabel.setForeground(BLUE_COLOR);
        teamNamePanel.add(teamNameLabel);

        JLabel vsLabel = new JLabel("vs", SwingConstants.CENTER);
        vsLabel.setFont(TEAM_NAME_FONT);
        vsLabel.setForeground(DIMMED_TEXT_COLOR);
        teamNamePanel.add(vsLabel);

        JLabel opponentTeamNameLabel = new JLabel(opponentTeamName, SwingConstants.LEFT);
        opponentTeamNameLabel.setFont(TEAM_NAME_FONT);
        opponentTeamNameLabel.setForeground(RED_COLOR);
        teamNamePanel.add(opponentTeamNameLabel);

        return teamNamePanel;
    }

    public GameResultEntryContainer(Integer gameId, String teamName, String opponentTeamName,
            List<String> teamPlayers) {
        setLayout(new BorderLayout());
        JPanel panel = new JPanel(new GridBagLayout());
        JPanel bottomPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding
        gbc.insets = new Insets(5, 10, 5, 10); // Add spacing
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        vsPanel = createTeamNamePanel(teamName, opponentTeamName);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        bottomPanel.add(vsPanel, gbc);

        JPanel opponentScorePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        opponentScoreLabel = new JLabel("Opponent Score:", SwingConstants.CENTER);
        opponentScoreLabel.setForeground(RED_COLOR);
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

        resultTableComponent = new ScoreComponent(teamPlayers, gameId);
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
        saveButton.setPreferredSize(new Dimension(200, 40));
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

    public ScoreComponent getResultTableComponent() {
        return resultTableComponent;
    }

    public JPanel getPanel() {
        return this;
    }

    public List<StatsModel> getStats() {
        return resultTableComponent.getTableData();
    }

    public int getOpponentScore() {
        return (int) opponentScoreSpinner.getValue();
    }

}
