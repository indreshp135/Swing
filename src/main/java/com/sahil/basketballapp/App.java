package com.sahil.basketballapp;

import com.sahil.basketballapp.controller.MainController;
import com.sahil.basketballapp.database.DatabaseConnection;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new DatabaseConnection().createTables();
            MainController.initController();
            MainController.getDefaultView();
        });
    }
}
