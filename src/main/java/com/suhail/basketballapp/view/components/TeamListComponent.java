package com.suhail.basketballapp.view.components;

import javax.swing.*;
import javax.swing.border.Border;

import com.suhail.basketballapp.controller.MainController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;

public class TeamListComponent extends JPanel {

    private static final Color CARD_BACKGROUND_COLOR = Color.WHITE;
    private static final Border CARD_BORDER = BorderFactory.createLineBorder(Color.BLACK, 1);
    private static final Font TEAM_NAME_FONT = new Font("Arial", Font.BOLD, 16);
    private static final Font DATE_FONT = new Font("Arial", Font.PLAIN, 12);
    private static final Font SCORE_FONT = new Font("Arial", Font.BOLD, 30);
    private static final Color BLUE_COLOR = Color.BLUE;
    private static final Color RED_COLOR = Color.RED;
    private static final Color DIMMED_TEXT_COLOR = Color.GRAY;
    private static final Border TEAM_PANEL_BORDER = BorderFactory.createEmptyBorder(10, 10, 0, 10);
    private static final Border SCORE_PANEL_BORDER = BorderFactory.createEmptyBorder(10, 10, 10, 10);

    public String teamName;
    public String opponentTeamName;
    public String date;
    public Integer opponentTeamScore;
    public Integer teamScore;
    public Integer gameId;

    public TeamListComponent(String teamName, String opponentTeamName, String date,
            Integer opponentTeamScore, Integer teamScore, Integer gameId) {
        this.teamName = teamName;
        this.opponentTeamName = opponentTeamName;
        this.date = date;
        this.opponentTeamScore = opponentTeamScore;
        this.teamScore = teamScore;
        this.gameId = gameId;
        createPanel();
    }

    public void createPanel() {
        setLayout(new GridBagLayout());
        setMaximumSize(new Dimension(getMaximumSize().width, 120));
        setBackground(CARD_BACKGROUND_COLOR);
        setBorder(CARD_BORDER);
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setBackground(Color.LIGHT_GRAY);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                setBackground(CARD_BACKGROUND_COLOR);
            }

            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                setBackground(Color.GREEN);
            }
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 10, 5, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        add(createTeamNamePanel(), gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        add(createDatePanel(), gbc);

        
        if (teamScore != null && opponentTeamScore != null) {
            gbc.gridy = 2;
            add(createScorePanel(), gbc);
        } else {
            gbc.gridheight = 2;
            gbc.gridwidth = 3;
            gbc.gridy = 2;
            gbc.anchor = GridBagConstraints.CENTER;
            add(addResultsPanel(), gbc);
        }
    }

    private JPanel createTeamNamePanel() {
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

    private JPanel createDatePanel() {
        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        datePanel.setOpaque(false);
        setMaximumSize(getMaximumSize());

        JLabel dateLabel = new JLabel(date);
        dateLabel.setFont(DATE_FONT);
        dateLabel.setForeground(DIMMED_TEXT_COLOR);
        datePanel.add(dateLabel);

        return datePanel;
    }

    private JPanel createScorePanel() {
        JPanel scorePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        scorePanel.setOpaque(false);
        scorePanel.setBorder(SCORE_PANEL_BORDER);

        JLabel teamScoreLabel = new JLabel(teamScore.toString());
        teamScoreLabel.setFont(SCORE_FONT);
        teamScoreLabel.setForeground(BLUE_COLOR);
        scorePanel.add(teamScoreLabel);

        JLabel vsScoreLabel = new JLabel(" - ");
        vsScoreLabel.setFont(SCORE_FONT);
        vsScoreLabel.setForeground(DIMMED_TEXT_COLOR);
        scorePanel.add(vsScoreLabel);

        JLabel opponentTeamScoreLabel = new JLabel(opponentTeamScore.toString());
        opponentTeamScoreLabel.setFont(SCORE_FONT);
        opponentTeamScoreLabel.setForeground(RED_COLOR);
        scorePanel.add(opponentTeamScoreLabel);

        return scorePanel;
    }

    private JButton addResultsPanel() {

        JButton resultsButton = new JButton("Add Results");
        resultsButton.addActionListener((ActionEvent e) -> {
            MainController.navigateToAddGameResult(gameId);
        });
        resultsButton.setPreferredSize(new Dimension(200, 40));
        resultsButton.setBackground(Color.BLUE);
        resultsButton.setForeground(Color.BLACK);
        resultsButton.setFont(new Font("Arial", Font.BOLD, 16));
        return resultsButton;
    }
}