package com.sahil.basketballapp;

import javax.swing.*;

import com.sahil.basketballapp.controller.MainController;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainController.initController();
            MainController.getDefaultView();
        });
    }
}
