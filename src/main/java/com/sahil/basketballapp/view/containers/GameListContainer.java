package com.sahil.basketballapp.view.containers;

import javax.swing.*;

import com.sahil.basketballapp.model.GameModel;
import com.sahil.basketballapp.view.components.TeamListComponent;

import java.awt.*;
import java.util.ArrayList;

public class GameListContainer extends JPanel {

    public GameListContainer(ArrayList<GameModel> gameResults) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Game Results");
        title.setForeground(Color.BLACK);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(title);
        add(Box.createRigidArea(new Dimension(0, 10)));

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        if (width > 1000)
            width = 1000; // Limit width to 1000px (for testing on large screens
        if (width < 600)
            width = 100; // Limit width to 600px (for testing on small screens

        setBorder(BorderFactory.createEmptyBorder(10, width / 8, 10, width / 8));

        for (GameModel result : gameResults) {
            TeamListComponent resultPanel = new TeamListComponent(
                    result.getTeamName(),
                    result.getOpponentTeamName(),
                    result.getDate(),
                    result.getOpponentTeamScore(),
                    result.getTeamScore(),
                    result.getGameId());

            add(resultPanel);
            add(Box.createRigidArea(new Dimension(0, 10))); // Add spacing between panels
        }
    }
}