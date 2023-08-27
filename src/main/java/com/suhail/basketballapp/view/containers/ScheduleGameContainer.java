package com.suhail.basketballapp.view.containers;

import com.suhail.basketballapp.model.ScheduleGameModel;
import com.suhail.basketballapp.view.components.ScheduleGameComponent;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ScheduleGameContainer {
    private JPanel panel;
    private ScheduleGameComponent component;

    public ScheduleGameContainer(List<String> teamNames) {
        component = new ScheduleGameComponent(teamNames);

        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(component.getPanel(), BorderLayout.CENTER);
    }

    public JButton getScheduleButton() {
        return component.getScheduleButton();
    }

    public ScheduleGameModel getFormData() {
        return component.getFormData();
    }

    public JPanel getPanel() {
        return panel;
    }
}
