package com.sahil.basketballapp.view.containers;

import javax.swing.*;

import com.sahil.basketballapp.model.PlayerInfoModel;
import com.sahil.basketballapp.model.StatsModel.PointsModel;
import com.sahil.basketballapp.view.components.ExtendedRegressionLineChart;
import com.sahil.basketballapp.view.components.InfoComponent;

import java.util.List;
import java.awt.*;

public class PlayerStatsContainer extends JPanel {
    private InfoComponent infoComponent;
    private ExtendedRegressionLineChart extendedRegressionLineChart;

    public PlayerStatsContainer(PlayerInfoModel player, List<PointsModel> pointsModelList) {
        setLayout(new BorderLayout());
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding
        gbc.insets = new Insets(5, 10, 5, 10); // Add spacing
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        infoComponent = new InfoComponent(player.getPhotouuid(), player.getName(), player.getAge(),
                player.getPosition(), player.getHeight(), player.getWeight());
        extendedRegressionLineChart = new ExtendedRegressionLineChart("Points Over Games", pointsModelList);

        JLabel headingLabel = new JLabel("Player Stats");

        headingLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headingLabel.setForeground(Color.BLACK);

        JPanel topPanel = new JPanel(new GridBagLayout());

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 2;
        topPanel.add(headingLabel, gbc);
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
        bottomPanel.add(extendedRegressionLineChart, gbc);

        gbc.gridx = 1;

        bottomPanel.add(infoComponent, gbc);

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
}
