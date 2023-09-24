package com.sahil.basketballapp.view.containers;

import javax.swing.*;

import com.sahil.basketballapp.model.ScheduleGameModel;
import com.sahil.basketballapp.view.components.ScheduleGameComponent;

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
