package com.sahil.basketballapp.controller;

import com.sahil.basketballapp.view.containers.WelcomeContainer;

public class WelcomeController {

    private WelcomeContainer welcomeContainer;

    public WelcomeController() {
        welcomeContainer = new WelcomeContainer();
    }

    public WelcomeContainer getViewPanel() {

        return welcomeContainer;
    }
}
