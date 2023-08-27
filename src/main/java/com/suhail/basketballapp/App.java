package com.suhail.basketballapp;

import com.suhail.basketballapp.controller.MainController;
import com.suhail.basketballapp.database.DatabaseConnection;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DatabaseConnection.createTables();
            MainController mainController = new MainController();
            mainController.getDefaultView();
        });
    }
}
